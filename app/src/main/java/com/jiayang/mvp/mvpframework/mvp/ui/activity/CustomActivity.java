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
import com.jiayang.mvp.mvpframework.widget.SpiderGrid;
import com.jiayang.mvp.mvpframework.widget.TextViews;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CustomActivity extends BaseActivity<CustomPresenter> implements CustomViewIpm {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.baseView)
    BaseView mBaseView;
    @BindView(R.id.spiderGrid)
    SpiderGrid mSpiderGrid;
    @BindView(R.id.textView)
    TextViews mTextViews;


    private String[] mStrings = new String[]{"SignLineView", "MoreLineView", "SignPointView", "MorePointView",
            "RectView", "RoundRectView", "CircleView", "OvalView", "ArcView", "RectContains", "LinePathView",
            "ArcPathView", "AddArcPath", "AddRectPath", "AddRoundPath", "PathFillType", "SpiderGridView", "TextPaintStyle",
            "TextViewAlign", "TextStyle"};


    private int[] mInts = new int[]{Constants.BASE_VIEW_SIGN_LINE, Constants.BASE_VIEW_MORE_LINE,
            Constants.BASE_VIEW_SIGN_POINT, Constants.BASE_VIEW_MORE_POINT,
            Constants.BASE_VIEW_RECT, Constants.BASE_VIEW_ROUND_RECT,
            Constants.BASE_VIEW_CIRCLE, Constants.BASE_VIEW_OVAL, Constants.BASE_VIEW_ARC,
            Constants.BASE_VIEW_RECT_CONTAINS, Constants.BASE_VIEW_LINE_PATH, Constants.BASE_VIEW_ARC_PATH,
            Constants.BASE_VIEW_ADD_ARC_PATH, Constants.BASE_VIEW_ADD_RECT_PATH, Constants.BASE_VIEW_ADD_ROUND_RECT_PATH,
            Constants.BASE_VIEW_PATH_FILL_TYPE, Constants.SPIDER_GRID, Constants.TEXT_VIEW_PAINT_STYLE, Constants.TEXT_VIEW_ALIGN,
            Constants.TEXT_VIEW_STYLE};


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
        List<BaseViewBean> mViewBeans = new ArrayList<>();

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
                if (mClickType == Constants.SPIDER_GRID) {

                    if (mSpiderGrid.getVisibility() == View.GONE) {
                        mBaseView.setVisibility(View.GONE);
                        mSpiderGrid.setVisibility(View.VISIBLE);
                        mTextViews.setVisibility(View.GONE);
                    }
                } else if (mClickType < Constants.SPIDER_GRID) {
                    mBaseView.onClick(mClickType);

                    if (mBaseView.getVisibility() == View.GONE) {
                        mBaseView.setVisibility(View.VISIBLE);
                        mSpiderGrid.setVisibility(View.GONE);
                        mTextViews.setVisibility(View.GONE);
                    }

                } else {
                    mTextViews.onClick(mClickType);
                    if (mTextViews.getVisibility() == View.GONE) {
                        mBaseView.setVisibility(View.GONE);
                        mSpiderGrid.setVisibility(View.GONE);
                        mTextViews.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }
}
