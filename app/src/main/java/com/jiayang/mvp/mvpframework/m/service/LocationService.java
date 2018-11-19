package com.jiayang.mvp.mvpframework.m.service;


import com.jiayang.mvp.mvpframework.m.bean.LocationBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by 张 奎 on 2017-09-02 10:51.
 */

public interface LocationService {

    //    http://apis.juhe.cn/  //baseurl http://121.42.248.165:9099/datacenter/v1/CarCustom/GetPartAndPrice
    //    String BASE_URL = "http://apis.juhe.cn/";
    String BASE_URL = "http://121.42.248.165:9099/";
    String KEY = "daf8fa858c330b22e342c882bcbac622";

    // mobile/get  //相对url
    // ?phone=13812345678&key=daf8fa858c330b22e342c882bcbac622  参数  Query查询参数  拼接参数
    @GET("mobile/get")
    Observable<LocationBean> getLocation(@Query("phone") String phoneNumber, @Query("key") String key);

    @Headers({"Domain-Name:change"}) // 加上 Domain-Name header
    @GET("mobile/get")
    Observable<LocationBean> getLocationForChange(@Query("phone") String phoneNumber, @Query("key") String key);

}
