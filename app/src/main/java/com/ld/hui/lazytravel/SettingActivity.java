package com.ld.hui.lazytravel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import base.BaseApplication;
import utils.BusProvider;
import utils.Constant;

public class SettingActivity extends Activity implements View.OnClickListener {

    private ImageView ivLeft;
    private TextView tvTitle;
    private TextView tvExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initViews();
        bindViews();
        initData();
    }

    private void initViews() {
        ivLeft = (ImageView) findViewById(R.id.iv_left);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvExit = (TextView) findViewById(R.id.tv_exit);
    }

    private void bindViews() {
        ivLeft.setOnClickListener(this);
        tvExit.setOnClickListener(this);
    }

    private void initData() {
        tvTitle.setText("设置");
        if (!BaseApplication.get(Constant.LOGIN_STATUS, false)) {
            tvExit.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                SettingActivity.this.finish();
                break;
            case R.id.tv_exit:
                BaseApplication.deleteAllPreference("common.pref");
                BusProvider.getUIBusInstance().post("exit");
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getUIBusInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getUIBusInstance().unregister(this);
    }
}
