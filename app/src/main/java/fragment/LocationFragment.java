package fragment;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.ld.hui.lazytravel.BookTravelActivity;
import com.ld.hui.lazytravel.BookTrvelDetailActivity;
import com.ld.hui.lazytravel.CityListActivity;
import com.ld.hui.lazytravel.R;
import com.ld.hui.lazytravel.ScenicDetailActivity;
import com.ld.hui.lazytravel.SearchActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import adapter.BaseRecyclerAdapter;
import adapter.BookTravelAdapter;
import adapter.PersonAdapter;
import async.BaseHttpAsyncTask;
import base.BaseApplication;
import http.HttpRequestUtil;
import model.BookTravelInfo;
import model.PersonInfo;
import result.BookTravelResult;
import service.LocationService;
import utils.FullyGridLayoutManager;
import utils.LogUtil;
import utils.Pinyin4jUtil;
import utils.StringUtil;
import utils.SyLinearLayoutManager;
import view.DividerItemDecoration;

/**
 * 当地页面
 * Created by tao on 2015/12/14.
 */
public class LocationFragment extends Fragment implements View.OnClickListener {

    private View view;
    private TextView tvSearch;
    private TextView tvLocation;
    private float latx = 30.963175f;
    private float laty = 120.400244f;
    private String lableName = "天堂软件园";
    private SyLinearLayoutManager layoutManager;
    private RecyclerView rvBookTravel;
    private BookTravelAdapter bookTravelAdapter;
    private List<BookTravelInfo> bookTravelInfos;
    private FullyGridLayoutManager gridLayoutManager;
    private List<PersonInfo> personInfoList;
    private PersonAdapter personAdapter;
    public MyLocationListener myListener = new MyLocationListener();
    private LocationService locationService;
    public Vibrator mVibrator;
    private TextView tvScenicMore;      //进入目的地
    private TextView tvBookTravelMore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SDKInitializer.initialize(getActivity().getApplicationContext());
        view = inflater.inflate(R.layout.fragment_location, container, false);
        locationService = new LocationService(getActivity().getApplicationContext());
        mVibrator =(Vibrator)getActivity().getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getActivity().getApplicationContext());
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(myListener);
        //注册监听
        int type = getActivity().getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }
        locationService.start();// 定位SDK
        initViews();
        bindViews();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        locationService.registerListener(myListener);
//        locationService.setLocationOption(locationService.getOption());
//        locationService.start();
//    }

    private void initViews() {
        tvSearch = (TextView) view.findViewById(R.id.tv_search);
//        rvPeople = (RecyclerView) view .findViewById(R.id.rv_people);
        tvLocation = (TextView) view.findViewById(R.id.tv_location);
        tvScenicMore = (TextView) view.findViewById(R.id.tv_scenic_more);
        tvBookTravelMore = (TextView) view.findViewById(R.id.tv_book_travel_more);
        rvBookTravel = (RecyclerView) view.findViewById(R.id.rv_book_travel);
    }

    private void bindViews() {
        tvSearch.setOnClickListener(this);
        tvLocation.setOnClickListener(this);
        tvScenicMore.setOnClickListener(this);
        tvBookTravelMore.setOnClickListener(this);
    }

    private void initData() {
        bookTravelInfos = new ArrayList<>();
        layoutManager = new SyLinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvBookTravel.setLayoutManager(layoutManager);
        bookTravelAdapter = new BookTravelAdapter(bookTravelInfos, getActivity());
        rvBookTravel.setAdapter(bookTravelAdapter);
        rvBookTravel.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));

        bookTravelAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, long id) {
                Intent intent = new Intent(getActivity(),
                        BookTrvelDetailActivity.class);
                intent.putExtra("url", bookTravelInfos.get(position).getBookUrl());
                startActivity(intent);
            }
        });
        getBookTravel(Pinyin4jUtil.getPinYin(getCityName()), true);
    }

    /**
     * 获取热么游记信息
     * @param address
     */
    private void getBookTravel(final String address, boolean isShow) {
        new BaseHttpAsyncTask<Void, Void, BookTravelResult>(getActivity(), isShow) {
            @Override
            protected void onCompleteTask(final BookTravelResult result) {
                if (result.getErrcode() == result.SUCCESS) {
                    if (null != result.getData() && null != result.getData().getBooks()) {
                        if (null != bookTravelInfos) {
                            if (result.getData().getBooks().size() >= 2) {
                                bookTravelInfos.addAll(result.getData().getBooks().subList(0, 2));
                            } else {
                                bookTravelInfos.addAll(result.getData().getBooks());
                            }
                        }
                        if (null != bookTravelAdapter) {
                            bookTravelAdapter.notifyDataSetChanged();
                        }
                    }
                } else {
                    if (StringUtil.isEmpty(result.getErrmsg())) {

                    } else {
//                        BaseApplication.showToast(result.getErrmsg());
                    }
                }
            }

            @Override
            protected BookTravelResult run(Void... params) {
                return HttpRequestUtil.getInstance().getBookTravel(address, 4 + "");
            }

        }.execute();
    }

    /**
     * 显示定位后的位置
     * @param location
     */
    private void showLocation(BDLocation location) {
        if (null != tvLocation) {
            tvLocation.setText(location.getCity());
        }
    }

    @Override
    public void onStop() {
        locationService.unregisterListener(myListener);
        locationService.stop();
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                Intent searchIntent = new Intent(getActivity(), SearchActivity.class);
                searchIntent.putExtra("city", tvLocation.getText().toString());
                startActivity(searchIntent);
                break;
            case R.id.tv_location:
                Intent choiceIntent = new Intent(getActivity(), CityListActivity.class);
                startActivityForResult(choiceIntent, 1);
                break;
            case R.id.tv_scenic_more:
                Intent secMoreIntent = new Intent(getActivity(), ScenicDetailActivity.class);
                secMoreIntent.putExtra("city", getCityName());
                startActivity(secMoreIntent);
//                String result = Pinyin4jUtil.getPinYin("周杭州");
//                Log.e("LocationFragment", "start()" + result + "/...");
//                new Thread(networkTask).start();

                break;
            case R.id.tv_book_travel_more:
                Intent travelIntent = new Intent(getActivity(), BookTravelActivity.class);
                travelIntent.putExtra("city", getCityName());
                startActivity(travelIntent);
                break;
        }
    }

    /**
     * 截取掉市的城市名
     * @return
     */
    private String getCityName() {
        String city2 = tvLocation.getText().toString();
        if (city2.endsWith("市")) {
            city2 = city2.substring(0, city2.length() - 1);
        }
        return city2;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (null != data) {
                tvLocation.setText(data.getStringExtra("city"));
            }
        }
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            sb.append("\ncity : ");
            sb.append(location.getCity());
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("LocationInfo", "info = " + sb.toString());
            showLocation(location);
            initData();
            BaseApplication.set("located_city", location.getCity());
        }

    }
}
