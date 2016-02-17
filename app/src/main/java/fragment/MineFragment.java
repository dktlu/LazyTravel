package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ld.hui.lazytravel.AttentionFansActivity;
import com.ld.hui.lazytravel.LoginActivity;
import com.ld.hui.lazytravel.PersonalCenterActivity;
import com.ld.hui.lazytravel.R;
import com.ld.hui.lazytravel.SettingActivity;
import com.squareup.otto.Subscribe;

import base.BaseApplication;
import utils.BusProvider;
import utils.Constant;

/**
 * 我的页面
 * Created by tao on 2015/12/14.
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private View view;
    private LinearLayout llLogin;
    private ImageView ivLeft;
    private TextView tvTitle;
    private TextView tvUserName;
    private RelativeLayout rlSetting;
    private RelativeLayout rlPersonalCenter;
    private TextView tvAttention;
    private TextView tvFans;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        initViews();
        bindViews();
        initData();
        return view;
    }

    private void initViews() {
        llLogin = (LinearLayout) view.findViewById(R.id.ll_login);
        ivLeft = (ImageView) view.findViewById(R.id.iv_left);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvUserName = (TextView) view.findViewById(R.id.tv_user_name);
        rlSetting = (RelativeLayout) view.findViewById(R.id.rl_setting);
        tvAttention = (TextView) view.findViewById(R.id.tv_attention);
        tvFans = (TextView) view.findViewById(R.id.tv_fans);
        rlPersonalCenter = (RelativeLayout) view.findViewById(R.id.rl_personal_center);
    }

    private void bindViews() {
        llLogin.setOnClickListener(this);
        rlSetting.setOnClickListener(this);
        tvAttention.setOnClickListener(this);
        tvFans.setOnClickListener(this);
        rlPersonalCenter.setOnClickListener(this);
    }

    private void initData() {
        BusProvider.getUIBusInstance().register(this);
        ivLeft.setVisibility(View.GONE);
        tvTitle.setText("我的");
        initUserInfo();
    }

    private void initUserInfo() {
        tvUserName.setText(BaseApplication.get(Constant.USER_NAME, "点击登录"));
    }

    @Subscribe
    public void refresh(String str) {
        if ("exit".equals(str)) {
            initUserInfo();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getUIBusInstance().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_login:
                if (!BaseApplication.get(Constant.LOGIN_STATUS, false)) {
                    Intent loginIntent = new Intent();
                    loginIntent.setClass(MineFragment.this.getActivity(), LoginActivity.class);
                    startActivity(loginIntent);
                }
                break;
            case R.id.rl_setting:
                Intent settingIntent = new Intent(MineFragment.this.getActivity(),
                        SettingActivity.class);
                startActivity(settingIntent);
                break;
            case R.id.tv_attention:
                Intent attentionIntent = new Intent(MineFragment.this.getActivity(),
                        AttentionFansActivity.class);
                startActivity(attentionIntent);
                break;
            case R.id.tv_fans:
                Intent fansIntent = new Intent(MineFragment.this.getActivity(),
                        AttentionFansActivity.class);
                startActivity(fansIntent);
                break;
            case R.id.rl_personal_center:
                Intent centerIntent = new Intent(MineFragment.this.getActivity(),
                        PersonalCenterActivity.class);
                startActivity(centerIntent);
                break;
            default:

                break;
        }
    }
}
