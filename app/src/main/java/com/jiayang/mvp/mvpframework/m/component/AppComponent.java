package com.jiayang.mvp.mvpframework.m.component;

import com.jiayang.mvp.mvpframework.common.MVPAppDeletage;
import com.jiayang.mvp.mvpframework.m.model.AppModule;

import dagger.Component;
import dagger.Module;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by 张 奎 on 2017-09-01 09:02.
 */

@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MVPAppDeletage mvpAppDeletage);
}
