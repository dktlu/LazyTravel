package com.ld.hui.lazytravel;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import fragment.LocationFragment;
import fragment.MineFragment;
import fragment.TravelFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    //定义fragment对象
    private TravelFragment travelFragment;
    private LocationFragment locationFragment;
    private MineFragment mineFragment;

    //用于存放fragment的容器
    private FrameLayout flyout;

    //定义底部导航栏的三个布局
    private RelativeLayout rlTravel;
    private RelativeLayout rlLocation;
    private RelativeLayout rlMine;

    //定义底部导航栏中的ImageView和TextView
    private ImageView ivTravel;
    private ImageView ivLocation;
    private ImageView ivMine;
    private TextView tvTravel;
    private TextView tvLocation;
    private TextView tvMine;

    //定义要用的颜色值
    private int whirt = 0xFFFFFFFF;
    private int mColor = 0xFFFF6200;
    private int gray = 0xFF7597B3;

    //定义FragmentManager对象
    FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager = getSupportFragmentManager();
        initViews();
        bindViews();
        initData();
    }

    private void initData() {
        setChioceItem(0);
    }

    private void bindViews() {
        rlTravel.setOnClickListener(this);
        rlLocation.setOnClickListener(this);
        rlMine.setOnClickListener(this);
    }

    private void initViews() {
        rlTravel = (RelativeLayout) findViewById(R.id.rl_travel);
        rlLocation = (RelativeLayout) findViewById(R.id.rl_location);
        rlMine = (RelativeLayout) findViewById(R.id.rl_mine);
        ivTravel = (ImageView) findViewById(R.id.iv_travel);
        ivLocation = (ImageView) findViewById(R.id.iv_location);
        ivMine = (ImageView) findViewById(R.id.iv_mine);
        tvTravel = (TextView) findViewById(R.id.tv_travel);
        tvLocation = (TextView) findViewById(R.id.tv_location);
        tvMine = (TextView) findViewById(R.id.tv_mine);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_travel:
                setChioceItem(0);
                break;
            case R.id.rl_location:
                setChioceItem(1);
                break;
            case R.id.rl_mine:
                setChioceItem(2);
                break;
        }
    }

    /**
     * 选中item之后的一些处理逻辑
     * @param index
     */
    private void setChioceItem(int index) {
        FragmentTransaction transaction = fManager.beginTransaction();
        clearChioce();
        hideFragments(transaction);
        switch (index) {
            case 0:
                ivTravel.setImageResource(R.drawable.icon_index_travel_selected);
                tvTravel.setTextColor(mColor);
                if (null == travelFragment) {
                    //如果travelFragment为空，则创建一个并添加到界面上
                    travelFragment = new TravelFragment();
                    transaction.add(R.id.content, travelFragment);
                } else {
                    //如果fragment不为空，则直接显示到界面上
                    transaction.show(travelFragment);
                }
                break;
            case 1:
                ivLocation.setImageResource(R.drawable.icon_index_location_selected);
                tvLocation.setTextColor(mColor);
                if (null == locationFragment) {
                    //如果travelFragment为空，则创建一个并添加到界面上
                    locationFragment = new LocationFragment();
                    transaction.add(R.id.content, locationFragment);
                } else {
                    //如果fragment不为空，则直接显示到界面上
                    transaction.show(locationFragment);
                }
                break;
            case 2:
                ivMine.setImageResource(R.drawable.icon_index_mine_selected);
                tvMine.setTextColor(mColor);
                if (null == mineFragment) {
                    //如果travelFragment为空，则创建一个并添加到界面上
                    mineFragment = new MineFragment();
                    transaction.add(R.id.content, mineFragment);
                } else {
                    //如果fragment不为空，则直接显示到界面上
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏所有的fragment，避免出现fragment混乱
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (null != travelFragment) {
            transaction.hide(travelFragment);
        }
        if (null != locationFragment) {
            transaction.hide(locationFragment);
        }
        if (null != mineFragment) {
            transaction.hide(mineFragment);
        }
    }

    /**
     * 定义一个重置所有设置的方法
     */
    private void clearChioce() {
        ivTravel.setImageResource(R.drawable.icon_index_travel_normal);
        tvTravel.setTextColor(gray);
        ivLocation.setImageResource(R.drawable.icon_index_location_normal);
        tvLocation.setTextColor(gray);
        ivMine.setImageResource(R.drawable.icon_index_mine_normal);
        tvMine.setTextColor(gray);
    }
}
