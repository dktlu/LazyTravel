package com.ld.hui.lazytravel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by tao on 2015/12/18.
 */
public class BookTrvelDetailActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_travel_detail);
        initViews();
        initData();
    }

    private void initViews() {
        webView = (WebView) findViewById(R.id.webview);
    }

    private void initData() {
        Intent intent = getIntent();
        String url = null != intent ? intent.getStringExtra("url") : "";
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    return false;
                }
                return true;
            }
        });
        webView.loadUrl(url);
    }
}
