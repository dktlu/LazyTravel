package com.ld.hui.lazytravel;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalCenterActivity extends Activity implements View.OnClickListener {

    private ImageView ivLeft;
    private TextView tvTitle;
    private TextView tvAttention;
    private TextView tvFans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        initViews();
        bindViews();
        initData();
    }

    private void initViews() {
        ivLeft = (ImageView) findViewById(R.id.iv_left);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvAttention = (TextView) findViewById(R.id.tv_attention);
        tvFans = (TextView) findViewById(R.id.tv_fans);
    }

    private void bindViews() {
        ivLeft.setOnClickListener(this);
        tvAttention.setOnClickListener(this);
        tvFans.setOnClickListener(this);
    }

    private void initData() {
        tvTitle.setText("个人主页");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                PersonalCenterActivity.this.finish();
                break;
            case R.id.tv_attention:
                Intent attentionIntent = new Intent(PersonalCenterActivity.this,
                        AttentionFansActivity.class);
                startActivity(attentionIntent);
                break;
            case R.id.tv_fans:
                Intent fansIntent = new Intent(PersonalCenterActivity.this,
                        AttentionFansActivity.class);
                startActivity(fansIntent);
                break;
            default:

                break;
        }
    }
}
