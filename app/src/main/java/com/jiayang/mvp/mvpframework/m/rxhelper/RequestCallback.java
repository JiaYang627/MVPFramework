package com.jiayang.mvp.mvpframework.m.rxhelper;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/6/14.
 */

public abstract class RequestCallback<T> implements Observer<T> {

    private ErrorListener errorListener;

    public RequestCallback(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
        if (errorListener != null) {
            errorListener.handleError(e);
        }
    }

    @Override
    public void onComplete() {

    }
}
