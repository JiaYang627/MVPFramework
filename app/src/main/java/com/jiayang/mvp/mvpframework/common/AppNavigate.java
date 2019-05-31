package com.jiayang.mvp.mvpframework.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jiayang.mvp.mvpframework.v.activity.TestActivity;

/**
 * Created by 张 奎 on 2017-09-01 09:00.
 */

public class AppNavigate {

    /**
     * 跳转页面 方法
     *
     * @param target 跳转目标类
     */
    public void startActivity(Activity mContext ,Class<? extends AppCompatActivity> target) {
        if (mContext != null) { // 防止 Monkey 测试 跳转 报空指针
            Intent intent = new Intent(mContext, target);
            mContext.startActivity(intent);
        } else {
            throw new ExceptionInInitializerError("当前上下文为 null !");
        }
    }

    /**
     * 跳转页面 方法
     *
     * @param target        跳转目标类
     * @param bundle        跳转携带数据 bundle对象
     * @param currentFinish 跳转后当前页面是否销毁
     */
    public void startActivity(Activity mContext,Class<? extends AppCompatActivity> target, Bundle bundle, boolean currentFinish) {
        if (mContext != null) { // 防止 Monkey 测试 跳转 报空指针
            Intent intent = new Intent(mContext, target);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            mContext.startActivity(intent);
            if (currentFinish) {
                mContext.finish();
            }
        } else {
            throw new ExceptionInInitializerError("当前上下文为 null !");
        }
    }
    public void goToTestActivity(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, TestActivity.class);
            intent.putExtra("aaa", "This Act is TestActivity");
            context.startActivity(intent);
        }
    }




}
