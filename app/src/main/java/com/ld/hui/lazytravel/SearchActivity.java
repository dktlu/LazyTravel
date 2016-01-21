package com.ld.hui.lazytravel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by tao on 2015/12/14.
 */
public class SearchActivity extends Activity {

    private TextView tvLocation;

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
    }

    private void bindViews() {

    }

    private void initData() {
        Intent intent = getIntent();
        if (null != intent) {
            String city = getIntent().getStringExtra("city");
            tvLocation.setText(city);
        }
    }
}
