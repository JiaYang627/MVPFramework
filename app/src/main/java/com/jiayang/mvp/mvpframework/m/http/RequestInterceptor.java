package com.jiayang.mvp.mvpframework.m.http;

import android.content.Context;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Forward1129 on 2017/2/16.
 */

public class RequestInterceptor implements Interceptor {

    private final int DEVICE_TYPE = 1; // 设备类型
    private Context context;

    public RequestInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                .header("data", fetchHeaderInfo())
                .method(original.method(), original.body());
        return chain.proceed(requestBuilder.build());
    }

    private String fetchHeaderInfo() {
//        String deviceCode = MD5Utils.GetMD5Code(MobileInfo.phoneOnlyCode(context));


        Map<String, String> map = new HashMap<>();
        map.put("DeviceType", String.valueOf(DEVICE_TYPE));
//        map.put("DeviceToken", deviceCode);
//        map.put("APPVersion", APP_VERSION_NAME);
//        map.put("RegistrationID", jpushID);
//        map.put("Authorization", token);
        JSONObject json = new JSONObject(map);
        return json.toString();
    }
}
