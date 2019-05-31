package com.jiayang.mvp.mvpframework.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.adapter.BaseViewAdapter;
import com.jiayang.mvp.mvpframework.bean.BaseViewBean;
import com.jiayang.mvp.mvpframework.common.BaseActivity;
import com.jiayang.mvp.mvpframework.common.Constants;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.mvp.ipm.CustomViewIpm;
import com.jiayang.mvp.mvpframework.mvp.presenter.CustomPresenter;
import com.jiayang.mvp.mvpframework.widget.BaseView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CustomActivity extends BaseActivity<CustomPresenter> implements CustomViewIpm {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.baseView)
    BaseView mBaseView;


    private List<BaseViewBean> mViewBeans;
    private String[] mStrings = new String[]{"SignLineView", "MoreLineView", "SignPointView", "MorePointView",
            "RectView", "RoundRectView", "CircleView"};


    private int[] mInts = new int[]{Constants.BASE_VIEW_SIGN_LINE, Constants.BASE_VIEW_MORE_LINE,
            Constants.BASE_VIEW_SIGN_POINT, Constants.BASE_VIEW_MORE_POINT,
            Constants.BASE_VIEW_RECT, Constants.BASE_VIEW_ROUND_RECT,
            Constants.BASE_VIEW_CIRCLE};


    private BaseViewAdapter mAdapter;

    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBeans = new ArrayList<>();

        for (int i = 0; i < mStrings.length; i++) {

            String mString = mStrings[i];
            int mInt = mInts[i];

            mViewBeans.add(new BaseViewBean(mString, mInt));

        }

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new BaseViewAdapter(mViewBeans);
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int mClickType = mAdapter.getData().get(position).mType;
                mBaseView.onClick(mClickType);
            }
        });

    }
}
