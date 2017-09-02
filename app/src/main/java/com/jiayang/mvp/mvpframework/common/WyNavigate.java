package com.jiayang.mvp.mvpframework.common;

import android.content.Context;
import android.content.Intent;

import com.jiayang.mvp.mvpframework.v.activity.TestActivity;

/**
 * Created by 张 奎 on 2017-09-01 09:00.
 */

public class WyNavigate {


    public void goToTestActivity(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, TestActivity.class);
            intent.putExtra("aaa", "This Act is TestActivity");
            context.startActivity(intent);
        }
    }
}
