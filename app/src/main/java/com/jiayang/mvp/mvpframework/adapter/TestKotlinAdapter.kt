package com.jiayang.mvp.mvpframework.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jiayang.mvp.mvpframework.R

/**
 * @author ：张 奎
 * @date ：2020-04-17 14：28
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class TestKotlinAdapter(list :List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.activity_test_kotlin_temp,list) {
    override fun convert(helper: BaseViewHolder, item: String?) {
    }
}