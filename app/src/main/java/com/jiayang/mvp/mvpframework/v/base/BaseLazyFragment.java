package com.jiayang.mvp.mvpframework.v.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;


import com.jiayang.mvp.mvpframework.common.MVPApp;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.p.base.BaseLazyPresenter;

import javax.inject.Inject;

/**
 * Created by 张 奎 on 2017-09-17 09:16.
 */

public abstract class BaseLazyFragment<T extends BaseLazyPresenter> extends AppCompatDialogFragment implements IBaseView{

    @Inject
    protected T mPresenter;
    protected boolean presenterFactoryPrepared;

    protected boolean isVisible;

    protected boolean isFirst= true;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        inject(((MVPApp)getActivity().getApplication()).getApiComponent());
        mPresenter.attachView(this);
        presenterFactoryPrepared = true;
    }

    protected abstract void inject(ApiComponent apiComponent);
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        if (!presenterFactoryPrepared){
            return;
        }
        lazyLoad();
    }

    //懒加载的方法,在这个方法里面我们为Fragment的各个组件去添加数据
    protected void lazyLoad(){
        mPresenter.setVisible(isVisible);
        mPresenter.preLazyLoad();
    }


    protected void onInvisible() {
    }

    public boolean isVisiblee() {
        return isVisible;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getContext(getActivity());
        mPresenter.getData(getActivity().getIntent());
        mPresenter.getArguments(getArguments());
        onVisible();
        mPresenter.onTakeView();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mPresenter.onHiddenChanged(hidden);
    }
}
