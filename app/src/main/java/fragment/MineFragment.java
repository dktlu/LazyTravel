package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ld.hui.lazytravel.LoginActivity;
import com.ld.hui.lazytravel.R;

import base.BaseApplication;
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
    }

    private void bindViews() {
        llLogin.setOnClickListener(this);
    }

    private void initData() {
        ivLeft.setVisibility(View.GONE);
        tvTitle.setText("我的");

        if (BaseApplication.get(Constant.LOGIN_STATUS, false)) {
            tvUserName.setText(BaseApplication.get(Constant.USER_NAME, "点击登录"));
        }

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
            default:

                break;
        }
    }
}
