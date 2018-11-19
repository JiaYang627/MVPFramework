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
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6ImEzck1VZ01Gdjl0UGNsTGE2eUYzekFrZnF1RSIsImtpZCI6ImEzck1VZ01Gdjl0UGNsTGE2eUYzekFrZnF1RSJ9.eyJpc3MiOiJodHRwOi8vc2Fhcy5oa3MzNjAuY29tOjgzL2NvcmUiLCJhdWQiOiJodHRwOi8vc2Fhcy5oa3MzNjAuY29tOjgzL2NvcmUvcmVzb3VyY2VzIiwiZXhwIjoxNTMwODQ2OTQxLCJuYmYiOjE1MzA4NDMzNDEsImNsaWVudF9pZCI6InphLmNsaWVudCIsImNsaWVudF9sb2NhdGlvbiI6ImRhdGFjZW50ZXIiLCJzY29wZSI6IndyaXRlIn0.kuh-DoFy7Pg5svAcyoPOnp77tanqI4TNMjgcKhIxBrHTAMloLK2mFL1VvHekzBNutvqUaryoHGPhjgF25wi6BaINjcrQx-VPTfZb5lOQv7Y57TGaSTgD--6s7gI3UtXfTrGlFRRvtNlJOaqVqJV5ZUiMoNCNI0VGXrA65x8BYncu4PXoKylckP3_lxRDkgAgn5G7CwUM0c1tdKKrWgBocK_GVDBUBdgHyToNt_yOADJKDmBuwvG8CqyViDmefqirmoGviTHkNjp5FrzlHDPj75-kdjiKKR3aInm01xKrcMHAYvtKSqVbA-qkhpbGird5rli5XtgRUxheWO9Rlsxznw";


    public RequestInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                //                .header("data", fetchHeaderInfo())
                .addHeader("Authorization", "Bearer " + token)
                .method(original.method(), original.body());
        return chain.proceed(requestBuilder.build());
    }

    private String fetchHeaderInfo() {
//        String deviceCode = MD5Utils.GetMD5Code(MobileInfo.phoneOnlyCode(context));

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6ImEzck1VZ01Gdjl0UGNsTGE2eUYzekFrZnF1RSIsImtpZCI6ImEzck1VZ01Gdjl0UGNsTGE2eUYzekFrZnF1RSJ9.eyJpc3MiOiJodHRwOi8vc2Fhcy5oa3MzNjAuY29tOjgzL2NvcmUiLCJhdWQiOiJodHRwOi8vc2Fhcy5oa3MzNjAuY29tOjgzL2NvcmUvcmVzb3VyY2VzIiwiZXhwIjoxNTMwODQ2OTQxLCJuYmYiOjE1MzA4NDMzNDEsImNsaWVudF9pZCI6InphLmNsaWVudCIsImNsaWVudF9sb2NhdGlvbiI6ImRhdGFjZW50ZXIiLCJzY29wZSI6IndyaXRlIn0.kuh-DoFy7Pg5svAcyoPOnp77tanqI4TNMjgcKhIxBrHTAMloLK2mFL1VvHekzBNutvqUaryoHGPhjgF25wi6BaINjcrQx-VPTfZb5lOQv7Y57TGaSTgD--6s7gI3UtXfTrGlFRRvtNlJOaqVqJV5ZUiMoNCNI0VGXrA65x8BYncu4PXoKylckP3_lxRDkgAgn5G7CwUM0c1tdKKrWgBocK_GVDBUBdgHyToNt_yOADJKDmBuwvG8CqyViDmefqirmoGviTHkNjp5FrzlHDPj75-kdjiKKR3aInm01xKrcMHAYvtKSqVbA-qkhpbGird5rli5XtgRUxheWO9Rlsxznw";

        Map<String, String> map = new HashMap<>();
        map.put("DeviceType", String.valueOf(DEVICE_TYPE));
        map.put("Authorization", "Bearer " + token);
//        map.put("DeviceToken", deviceCode);
//        map.put("APPVersion", APP_VERSION_NAME);
//        map.put("RegistrationID", jpushID);
//        map.put("Authorization", token);
        JSONObject json = new JSONObject(map);
        return json.toString();
    }
}
