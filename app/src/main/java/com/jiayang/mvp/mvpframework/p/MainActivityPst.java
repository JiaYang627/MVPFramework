package com.jiayang.mvp.mvpframework.p;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.jiayang.mvp.mvpframework.common.WyNavigate;
import com.jiayang.mvp.mvpframework.m.bean.LocationBean;
import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.m.rxhelper.RequestCallback;
import com.jiayang.mvp.mvpframework.m.service.LocationService;
import com.jiayang.mvp.mvpframework.p.base.BasePresenter;
import com.jiayang.mvp.mvpframework.utils.RxUtils;
import com.jiayang.mvp.mvpframework.v.iview.ImainAcitivityView;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class MainActivityPst extends BasePresenter<ImainAcitivityView> {


    private LocationService locationService;

    @Inject
    WyNavigate wyNavigate;

    @Inject
    public MainActivityPst(ErrorListener errorListener,LocationService locationService) {
        super(errorListener);
        this.locationService = locationService;
    }


    public void show() {
        //wyNavigate.goToTestActivity(context);  测试跳转 TestActivity 时打开即可

        // ↑ 和 ↓ 两个必须 只存一个！！！！ 测试其中一个 另外一个必须注释掉

        // 测试 手机归属地 ，LocationService.KEY 必须写死 只需更换第一个参数 即可
        locationService.getLocation("13838385438" ,LocationService.KEY)
                .compose(RxUtils.<LocationBean>getSchedulerTransformer())
                .subscribe(new RequestCallback<LocationBean>(errorListener) {
                    @Override
                    public void onNext(@NonNull LocationBean locationBean) {
                        super.onNext(locationBean);
                        Toast.makeText(context,locationBean.result.city , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        Log.e("MainActivityError", e+"");
                    }
                });
    }

    @Override
    public void getData(Intent intent) {

    }

    @Override
    public void onActivityResult(int requestCode, int requestCode1, Intent data) {

    }
}
