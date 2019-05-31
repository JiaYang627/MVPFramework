package com.jiayang.mvp.mvpframework.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.bean.BaseViewBean;

import java.util.List;

/**
 * @author ：张 奎
 * @date ：2019-05-31 09：31
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */
public class BaseViewAdapter extends BaseQuickAdapter<BaseViewBean ,BaseViewHolder> {
    public BaseViewAdapter(@Nullable List<BaseViewBean> data) {
        super(R.layout.item_base_view,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseViewBean item) {

        helper.setText(R.id.nameTextView, item.mName);
    }
}
