package com.ld.hui.lazytravel;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import base.BaseApplication;
import utils.StringUtil;

public class RegisterActivity extends Activity implements View.OnClickListener {

    private ImageView ivLeft;
    private TextView tvTitle;
    private TextView tvRegister;
    private EditText etPhone;
    private EditText etPassWord;
    private EditText etPassWord2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        bindViews();
        initData();
    }

    private void initViews() {
        ivLeft = (ImageView) findViewById(R.id.iv_left);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etPassWord = (EditText) findViewById(R.id.et_password);
        etPassWord2 = (EditText) findViewById(R.id.et_password2);
    }

    private void bindViews() {
        ivLeft.setOnClickListener(this);
        tvTitle.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    private void initData() {
        tvTitle.setText("注册");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                RegisterActivity.this.finish();
                break;
            case R.id.tv_register:
                if (!verifyEmpty()) {
                    BaseApplication.showToast("注册账号");
                }
                break;
            default:

                break;
        }
    }

    private boolean verifyEmpty() {
        if (StringUtil.isEmpty(etPhone.getText().toString())) {
            BaseApplication.showToast("请输入注册手机号码");
            return true;
        }

        if (!StringUtil.isPhoneNumber(etPhone.getText().toString())) {
            BaseApplication.showToast("请输入正确的手机号码");
            return true;
        }

        if (StringUtil.isEmpty(etPassWord.getText().toString())) {
            BaseApplication.showToast("请输入密码");
            return true;
        }

        if (StringUtil.isEmpty(etPassWord2.getText().toString())) {
            BaseApplication.showToast("请再次确认密码");
            return true;
        }

        if (etPassWord.getText().toString().contains(" ")) {
            BaseApplication.showToast("密码不能包含空格");
            return true;
        }

        if (!etPassWord.getText().toString().equals(etPassWord2.getText().toString())) {
            BaseApplication.showToast("输入的密码不相同");
            return true;
        }

        return false;
    }
}
