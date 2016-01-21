package com.ld.hui.lazytravel;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import async.BaseHttpAsyncTask;
import base.BaseApplication;
import http.HttpRequestUtil;
import result.BaseResult;
import result.ScenicResult;
import utils.LogUtil;
import utils.Pinyin4jUtil;
import utils.StringUtil;


/**
 * Created by tao on 2015/12/17.
 */
public class ScenicDetailActivity extends Activity {
    
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic_detail);
        initViews();
    }

    private void initViews() {
        webview = (WebView) findViewById(R.id.webview);

        Intent intent = getIntent();
        String city = null != intent ? intent.getStringExtra("city") : "";

        getScenicInfo(Pinyin4jUtil.getPinYin("hangzhou"));



    }

    private void getScenicInfo(final String address) {
        new BaseHttpAsyncTask<Void, Void, ScenicResult>(this, false) {
            @Override
            protected void onCompleteTask(final ScenicResult result) {
                if (result.getCode() == BaseResult.SUCCESS) {
                    if (null != result.getResult()) {
                        WebSettings webSettings = webview.getSettings();
                        webSettings.setJavaScriptEnabled(true);     //设置支持javaScript脚本

                        webview.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                if( url.startsWith("http:") || url.startsWith("https:") ) {
                                    return false;
                                }
                                return true;
                            }
                        });

                        webview.loadUrl(result.getResult().getUrl());
                    }
                } else {
                    if (StringUtil.isEmpty(result.getMsg())) {

                    } else {
                        BaseApplication.showToast(result.getMsg());
                    }
                }
            }

            @Override
            protected ScenicResult run(Void... params) {
                return HttpRequestUtil.getInstance().getScenicInfo(address);
            }

        }.execute();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            if (webview.canGoBack()) {
                webview.goBack();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
