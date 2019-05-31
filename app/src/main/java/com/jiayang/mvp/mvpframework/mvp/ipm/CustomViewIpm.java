package com.jiayang.mvp.mvpframework.mvp.ipm;


import com.jiayang.mvp.mvpframework.common.BaseViewIpm;

/**
 * 必须实现 BaseViewIpm
 * 可以根据不同的业务逻辑划分多个 IPM 类,多个业务逻辑相近的页面可以使用同一个 IPM 类
 * 无需每个页面都创建一个独立的 IPM
 */
public interface CustomViewIpm extends BaseViewIpm {

}
