package com.jiayang.mvp.mvpframework.mvp.ui.activity


import android.os.Bundle
import com.jiayang.mvp.mvpframework.R
import com.jiayang.mvp.mvpframework.common.BaseActivity
import com.jiayang.mvp.mvpframework.m.component.ApiComponent
import com.jiayang.mvp.mvpframework.mvp.ipm.TestKotlinTempViewIpm
import com.jiayang.mvp.mvpframework.mvp.presenter.TestKotlinTempPresenter


class TestKotlinTempActivity : BaseActivity<TestKotlinTempPresenter>(), TestKotlinTempViewIpm {
    override fun inject(apiComponent: ApiComponent?) {
        apiComponent?.inject(this)
    }

    override fun getLayoutId() = R.layout.activity_test_kotlin_temp;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
