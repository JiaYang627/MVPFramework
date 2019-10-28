package com.jiayang.mvp.mvpframework.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.bean.Model;

import java.util.List;

/**
 * @author ：张 奎
 * @date ：2019-10-28 15：24
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */
public class MainActivityAdapter extends BaseQuickAdapter<Model,BaseViewHolder> {
    public MainActivityAdapter(@Nullable List<Model> data) {
        super(R.layout.item_adapter,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Model item) {
        helper.setText(R.id.item, item.mName);
    }
}
