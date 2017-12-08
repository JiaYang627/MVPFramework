package com.jiayang.mvp.mvpframework.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.jiayang.mvp.mvpframework.common.Constants;


/**
 * 作者： 张 奎 on 2017/3/10 10 44
 */

public class DialogUtils {

    public DialogUtils(Activity context) {
    }




    /**
     * 动态权限提示框
     *
     * @param context
     */
    private static AlertDialog dialog;

    public static void showDialogRequestPermission(final Activity context) {
        if (dialog == null) {
            dialog = new AlertDialog.Builder(context).setTitle("权限不可用")
                    .setMessage("请在\"MVPFramework-权限\"里开启以下权限:\n一、获取设备信息权限\n二、读写手机存储权限")
                    .setPositiveButton("前去开启", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                            intent.setData(uri);
                            context.startActivityForResult(intent, Constants.REQUEST_PERMISSION_SETTING);
                        }
                    })
                    .setCancelable(false).create();
        }
        dialog.show();
    }

    public static void dismissDialogRequestPermission() {
        if (dialog != null){
            dialog.dismiss();
        }

    }


}
