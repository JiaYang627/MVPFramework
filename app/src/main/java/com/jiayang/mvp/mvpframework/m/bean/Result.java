package com.jiayang.mvp.mvpframework.m.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by 张 奎 on 2017-09-02 10:54.
 */

public class Result {
    @JSONField(name = "province")
    public String province;
    @JSONField(name = "city")
    public String city;
    @JSONField(name = "areacode")
    public String areacode;
    @JSONField(name = "zip")
    public String zip;
    @JSONField(name = "company")
    public String company;
    @JSONField(name = "card")
    public String card;
}
