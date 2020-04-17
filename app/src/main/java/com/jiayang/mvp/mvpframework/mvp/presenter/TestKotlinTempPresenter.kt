package com.jiayang.mvp.mvpframework.mvp.presenter


import com.jiayang.mvp.mvpframework.common.BasePresenter
import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener
import com.jiayang.mvp.mvpframework.mvp.ipm.TestKotlinTempViewIpm
import javax.inject.Inject

class TestKotlinTempPresenter @Inject constructor(errorListener: ErrorListener?) : BasePresenter<TestKotlinTempViewIpm>(errorListener) {
}