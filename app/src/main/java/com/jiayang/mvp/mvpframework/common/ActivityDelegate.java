package com.jiayang.mvp.mvpframework.common;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2017/6/13.
 */

@Singleton
public class ActivityDelegate implements Application.ActivityLifecycleCallbacks {

    private Application appContext;
    private FragmentDelegate fragmentDelegate;

    @Inject
    public ActivityDelegate(Application appContext) {
        this.appContext = appContext;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        if (activity instanceof FragmentActivity) {
            if (fragmentDelegate == null) {
                fragmentDelegate = new FragmentDelegate();
            }
            ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(fragmentDelegate, true);
        }

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (activity instanceof FragmentActivity) {
            if (fragmentDelegate != null) {
                ((FragmentActivity) activity).getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(fragmentDelegate);
            }
        }
    }
}
