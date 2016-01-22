package com.ld.hui.lazytravel;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AttentionFansActivity extends Activity implements View.OnClickListener {

    private ImageView ivLeft;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_fans);
        initViews();
        bindViews();
        initData();
    }

    private void initViews() {
        ivLeft = (ImageView) findViewById(R.id.iv_left);
        tvTitle = (TextView) findViewById(R.id.tv_title);
    }

    private void bindViews() {
        ivLeft.setOnClickListener(this);
    }

    private void initData() {
        tvTitle.setText("关注/粉丝");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                AttentionFansActivity.this.finish();
                break;
            default:
                break;
        }
    }
}
