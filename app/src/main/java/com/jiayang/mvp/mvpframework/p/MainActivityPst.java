package com.jiayang.mvp.mvpframework.p;

import android.content.Intent;

import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.m.service.LocationService;
import com.jiayang.mvp.mvpframework.p.base.BasePresenter;
import com.jiayang.mvp.mvpframework.v.iview.ImainAcitivityView;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class MainActivityPst extends BasePresenter<ImainAcitivityView> {


    private LocationService locationService;



    @Inject
    public MainActivityPst(ErrorListener errorListener,LocationService locationService) {
        super(errorListener);
        this.locationService = locationService;
    }


    public void show() {
//        测试跳转 TestActivity 时打开即可
        wyNavigate.goToTestActivity(context);

        // ↑ 和 ↓ 两个必须 只存一个！！！！ 测试其中一个 另外一个必须注释掉

//        // 测试 手机归属地 ，LocationService.KEY 必须写死 只需更换第一个参数 即可
//        locationService.getLocation("13838385438" ,LocationService.KEY)
//                .retryWhen(new RetryWithDelay(3 ,2))
//                .compose(RxUtils.<LocationBean>getSchedulerTransformer())
//                .compose(RxUtils.<LocationBean>bindToLifecycle(mView))
//                .subscribe(new RequestCallback<LocationBean>(this) {
//                    @Override
//                    public void onNext(@NonNull LocationBean locationBean) {
//                        super.onNext(locationBean);
//                        Toast.makeText(context,locationBean.result.city , Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        super.onError(e);
//                        Log.e("MainActivityError", e+"");
//                    }
//                });
    }

    @Override
    public void getData(Intent intent) {

    }

    @Override
    public void onActivityResult(int requestCode, int requestCode1, Intent data) {

    }
}
