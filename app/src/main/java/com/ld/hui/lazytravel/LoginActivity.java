package com.ld.hui.lazytravel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import base.BaseApplication;
import utils.Constant;
import utils.StringUtil;

/**
 * Created by tao on 2016/1/21.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private TextView tvTitle;
    private ImageView ivLeft;
    private TextView tvLogin;
    private EditText etUser;
    private EditText etPassword;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        bindViews();
        initData();
    }

    private void initViews() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivLeft = (ImageView) findViewById(R.id.iv_left);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        etUser = (EditText) findViewById(R.id.et_user);
        etPassword = (EditText) findViewById(R.id.et_password);
        tvRegister = (TextView) findViewById(R.id.tv_register);
    }

    private void bindViews() {
        ivLeft.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    private void initData() {
        tvTitle.setText("登录");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                LoginActivity.this.finish();
                break;
            case R.id.tv_login:
                if (verification()) {
                    BaseApplication.set(Constant.LOGIN_STATUS, true);
                    BaseApplication.set(Constant.USER_NAME, etUser.getText().toString());
                    Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(loginIntent);
                }
                break;
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 判断登录信息是否正确
     * @return
     */
    private boolean verification() {
        String user = etUser.getText().toString();
        String password =  etPassword.getText().toString();
        if (StringUtil.isBlank(user)) {
            BaseApplication.showToast("手机号不能为空");
            return false;
        }
        if (!StringUtil.isPhoneNumber(user)) {
            BaseApplication.showToast("你输入的手机号不正确");
            return false;
        }
        if (StringUtil.isBlank(password)) {
            BaseApplication.showToast("请输入密码");
            return false;
        }
        if ("13007469213".equals(user) && "admin".equals(password)) {
            return true;
        } else {
            BaseApplication.showToast("用户名或密码错误，请重新输入");
        }
        return false;
    }
}
