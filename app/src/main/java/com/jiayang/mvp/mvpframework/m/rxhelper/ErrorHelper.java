package com.jiayang.mvp.mvpframework.m.rxhelper;

import android.content.Context;

import com.facebook.stetho.common.LogUtil;
import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.utils.ToastUtils;

/**
 * Created by 张 奎 on 2017-09-08 15:22.
 */

public class ErrorHelper {
    public static void onError(Context context, Throwable throwable) {
        String errorString = throwable.toString();
        LogUtil.e("aaaa==============" + throwable.toString());
        //返回的错误为空
        if (errorString == null) {
            ToastUtils.initToast(context.getString(R.string.generic_server_down));
//            ToastUtils.initToast("网络数据异常，请重试");
        }else {

            //请求超时
            if (errorString.contains("TimeoutException") || errorString.contains("SocketTimeoutException")) {
                ToastUtils.initToast(context.getString(R.string.request_time_out));
                //            ToastUtils.initToast("网络请求超时，请重试");
            }
            //能识别的请求异常，忽略不提示
            if (errorString.contains("SSLException")) {

            }
            //403、404等服务错误
            if (errorString.contains("ServiceConfigurationError") || errorString.contains("AuthenticatorException")) {
                ToastUtils.initToast(context.getString(R.string.generic_server_down));
                //            ToastUtils.initToast("网络数据异常，请重试");
            }
            //网络未连接
            if (errorString.contains("NetworkErrorException") || errorString.contains("NoConnectionPendingException") || errorString.contains("UnknownHostException")) {
                ToastUtils.initToast(context.getString(R.string.network_hint));
                //            ToastUtils.initToast("您的网络不给力，请检查网络设置");
            }
            //连接不到服务器
            if (errorString.contains("ConnectException")) {
                ToastUtils.initToast(context.getString(R.string.fail_to_connected) );
                //            ToastUtils.initToast("网络连接失败");
            }
        }
    }
}
