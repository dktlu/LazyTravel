package com.ld.hui.lazytravel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by tao on 2015/12/14.
 */
public class SearchActivity extends Activity implements View.OnClickListener {

    private TextView tvLocation;
    private TextView tvCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        bindViews();
        initData();
    }

    private void initViews() {
        tvLocation = (TextView) findViewById(R.id.tv_location);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
    }

    private void bindViews() {
        tvCancel.setOnClickListener(this);
    }

    private void initData() {
        Intent intent = getIntent();
        if (null != intent) {
            String city = getIntent().getStringExtra("city");
            tvLocation.setText(city);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                SearchActivity.this.finish();
                break;
            default:
                break;
        }
    }
}
