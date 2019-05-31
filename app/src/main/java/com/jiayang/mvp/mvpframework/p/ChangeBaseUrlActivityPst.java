package com.jiayang.mvp.mvpframework.p;

import com.jiayang.mvp.mvpframework.common.BasePresenter;
import com.jiayang.mvp.mvpframework.m.bean.LocationBean;
import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.m.rxhelper.RequestCallback;
import com.jiayang.mvp.mvpframework.m.service.LocationService;
import com.jiayang.mvp.mvpframework.utils.LogUtils;
import com.jiayang.mvp.mvpframework.utils.RxUtils;
import com.jiayang.mvp.mvpframework.utils.ToastUtilsBlankJ;
import com.jiayang.mvp.mvpframework.v.iview.ChangeBaseUrlActivityViewIpm;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * @author ：张 奎
 * @date ：2018-11-20 09：19
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */
public class ChangeBaseUrlActivityPst extends BasePresenter<ChangeBaseUrlActivityViewIpm> {
    private LocationService mLocationService;
    @Inject
    public ChangeBaseUrlActivityPst(ErrorListener errorListener, LocationService locationService) {
        super(errorListener);
        this.mLocationService = locationService;
    }


    public void defaultGetInfo() {
        mLocationService.getLocation("13838385438" ,LocationService.KEY)
                .compose(RxUtils.<LocationBean>getSchedulerTransformer())
                .compose(RxUtils.<LocationBean>bindToLifecycle(mView))
                .subscribe(new RequestCallback<LocationBean>(this) {
                    @Override
                    public void onNext(@NonNull LocationBean locationBean) {
                        super.onNext(locationBean);
                        ToastUtilsBlankJ.showShort(locationBean.result.city);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        LogUtils.e("ChangeBaseUrlActivityPst onError:46  -- > " + e);
                    }
                });
    }

    public void oneChangeGetInfo() {
        mLocationService.getLocationForChange("13838385438" ,LocationService.KEY)
                .compose(RxUtils.<LocationBean>getSchedulerTransformer())
                .compose(RxUtils.<LocationBean>bindToLifecycle(mView))
                .subscribe(new RequestCallback<LocationBean>(this) {
                    @Override
                    public void onNext(@NonNull LocationBean locationBean) {
                        super.onNext(locationBean);
                        ToastUtilsBlankJ.showShort(locationBean.result.city);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        LogUtils.e("ChangeBaseUrlActivityPst onError:65  -- > " + e);
                    }
                });
    }

    public void allChangeGetInfo() {
        mLocationService.getLocationForAll("13838385438" ,LocationService.KEY)
                .compose(RxUtils.<LocationBean>getSchedulerTransformer())
                .compose(RxUtils.<LocationBean>bindToLifecycle(mView))
                .subscribe(new RequestCallback<LocationBean>(this) {
                    @Override
                    public void onNext(@NonNull LocationBean locationBean) {
                        super.onNext(locationBean);
                        ToastUtilsBlankJ.showShort(locationBean.result.city);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        LogUtils.e("ChangeBaseUrlActivityPst onError:84  -- > " + e);
                    }
                });
    }
}
