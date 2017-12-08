package com.jiayang.mvp.mvpframework.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 动态权限：GitHub：https://github.com/jokermonn/permissions4m
 * Created by Administrator on 2017/2/13.
 * 关于6.0版本运行时权限的请求封装类，该类复写BaseActivity中的返回方法，封装成工具类，做处理后，统一回调
 * 如此，方便在各种类中使用（非activity，frgment亦可）
 * 前提条件：在BaseActivity中调用onRequestPermissionsResult()方法，参考样例
 * 使用方式：调用requestPermissions()方法，Context必须是一个Activity，在回调中处理逻辑
 */

public class PermissionUtils {

    private static int mRequestCode = -1;
    private static OnPermissionListener mOnPermissionListener;

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermissions(Context context, int requestCode
            , String[] permissions, OnPermissionListener listener) {
        if (permissions.length == 0) {
            return;
        }
        if (context instanceof Activity) {
            mOnPermissionListener = listener;
            List<String> deniedPermissions = getDeniedPermissions(context, permissions);
            if (deniedPermissions.size() > 0) {
                mRequestCode = requestCode;
                ((Activity) context).requestPermissions(deniedPermissions
                        .toArray(new String[deniedPermissions.size()]), requestCode);
            } else {
                if (mOnPermissionListener != null) {
                    mOnPermissionListener.onPermissionGranted(Arrays.asList(permissions));
                }
            }
        } else {
            throw new RuntimeException("Context must be an Activity");
        }
    }

    public static boolean isNeedRequest(Context context, String... permissions) {
        return getDeniedPermissions(context, permissions).size() > 0;
    }

    /**
     * 判断某个权限是否被拒绝并不再提示；
     *
     * @param activity
     * @param deniedPermissions
     * @return
     */
    public static boolean somePermissionPermanentlyDenied(@NonNull Activity activity,
                                                          @NonNull List<String> deniedPermissions) {
        for (String deniedPermission : deniedPermissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, deniedPermission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取请求权限中需要授权的权限
     */
    private static List<String> getDeniedPermissions(Context context, String... permissions) {
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permission);
            }
        }
        return deniedPermissions;
    }


    /**
     * 验证所有权限是否都已经授权
     */
    private static boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    //=====================================================================================

    /**
     * 请求权限结果，对应Activity中onRequestPermissionsResult()方法。
     */
    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        List<String> granted = new ArrayList<>();
        List<String> denied = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            String perm = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                granted.add(perm);
            } else {
                denied.add(perm);
            }
        }
        if (mRequestCode != -1 && requestCode == mRequestCode) {
            if (mOnPermissionListener != null) {
                if (verifyPermissions(grantResults)) {
                    mOnPermissionListener.onPermissionGranted(granted);
                } else {
                    mOnPermissionListener.onPermissionDenied(denied);
                }
            }
        }
    }


    public interface OnPermissionListener {
        void onPermissionGranted(List<String> granted);

        void onPermissionDenied(List<String> denied);
    }
}
