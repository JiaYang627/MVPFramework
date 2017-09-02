package com.jiayang.mvp.mvpframework.m.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by 张 奎 on 2017-09-02 10:46.
 */

public class LocationBean {

    @JSONField(name = "resultcode")
    public String resultcode;
    @JSONField(name = "reason")
    public String reason;
    @JSONField(name = "result")
    public Result result;
    @JSONField(name = "error_code")
    public Integer errorCode;
}
