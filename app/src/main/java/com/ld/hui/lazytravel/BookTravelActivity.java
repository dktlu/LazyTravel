package com.ld.hui.lazytravel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.BaseRecyclerAdapter;
import adapter.BookTravelAdapter;
import async.BaseHttpAsyncTask;
import base.BaseApplication;
import http.HttpRequestUtil;
import model.BookTravelInfo;
import result.BookTravelResult;
import result.ScenicResult;
import utils.LogUtil;
import utils.Pinyin4jUtil;
import utils.StringUtil;
import view.DividerItemDecoration;

/**
 * Created by tao on 2015/12/18.
 */
public class BookTravelActivity extends Activity implements View.OnClickListener {

    private LinearLayoutManager layoutManager;
    private BookTravelAdapter adapter;
    private RecyclerView recyclerView;
    private List<BookTravelInfo> bookTravelInfos;
    private ImageView ivLeft;
    private TextView tvTitle;
    private int page_count = 4;
    private SwipeRefreshLayout swiperefresh;
    private String city;
    private int lastVisibleItem = 0;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_travel_list);
        initViews();
        bindViews();
        initData();
    }

    private void initViews() {
        ivLeft = (ImageView) findViewById(R.id.iv_left);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
    }

    private void bindViews() {
        ivLeft.setOnClickListener(this);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBookTravel(Pinyin4jUtil.getPinYin(city), false);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        (lastVisibleItem + 1) == adapter.getItemCount()) {
                    page += 1;
                    page_count = 4 * page;
                    getBookTravel(Pinyin4jUtil.getPinYin(city), true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void initData() {
        tvTitle.setText("游记");
        bookTravelInfos = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BookTravelAdapter(bookTravelInfos, this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));

        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, long id) {
                Intent intent = new Intent(BookTravelActivity.this,
                        BookTrvelDetailActivity.class);
                intent.putExtra("url", bookTravelInfos.get(position).getBookUrl());
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        city = null != intent ? intent.getStringExtra("city") : "";
        getBookTravel(Pinyin4jUtil.getPinYin(city), true);
    }

    /**
     * 获取热么游记信息
     * @param address
     */
    private void getBookTravel(final String address, boolean isShow) {
        new BaseHttpAsyncTask<Void, Void, BookTravelResult>(this, isShow) {
            @Override
            protected void onCompleteTask(final BookTravelResult result) {
                if (swiperefresh.isRefreshing()) {
                    swiperefresh.setRefreshing(false);
                    if (null != bookTravelInfos) {
                        bookTravelInfos.clear();
                    }
                }
                if (result.getErrcode() == result.SUCCESS) {
                    if (null != result.getData() && null != result.getData().getBooks()) {
                        if (null != bookTravelInfos) {
                            bookTravelInfos.addAll(result.getData().getBooks());
                        }
                        if (null != adapter) {
                            adapter.notifyDataSetChanged();
                        }
                    }
                } else {
                    if (StringUtil.isEmpty(result.getErrmsg())) {

                    } else {
//                        BaseApplication.showToast(result.getErrmsg());
                    }
                }
            }

            @Override
            protected BookTravelResult run(Void... params) {
                return HttpRequestUtil.getInstance().getBookTravel(address, page_count + "");
            }

        }.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
