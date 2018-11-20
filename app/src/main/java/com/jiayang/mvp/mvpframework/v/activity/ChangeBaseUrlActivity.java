package com.jiayang.mvp.mvpframework.v.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.p.ChangeBaseUrlActivityPst;
import com.jiayang.mvp.mvpframework.utils.LogUtils;
import com.jiayang.mvp.mvpframework.utils.ToastUtils;
import com.jiayang.mvp.mvpframework.v.base.BaseActivity;
import com.jiayang.mvp.mvpframework.v.iview.ChangeBaseUrlActivityViewIpm;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import me.jessyan.retrofiturlmanager.onUrlChangeListener;
import okhttp3.HttpUrl;

/**
 * @author ：张 奎
 * @date ：2018-11-20 09：18
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */
public class ChangeBaseUrlActivity extends BaseActivity<ChangeBaseUrlActivityPst> implements ChangeBaseUrlActivityViewIpm {
    @BindView(R.id.editTextView)
    EditText editTextView;
    @BindView(R.id.oneChangeButton)
    Button oneChangeButton;
    @BindView(R.id.allChangeButton)
    Button allChangeButton;
    @BindView(R.id.clearChangeButton)
    Button clearChangeButton;
    @BindView(R.id.defaultButton)
    Button defaultButton;
    @BindView(R.id.textOneButton)
    Button textOneButton;
    private Disposable mSubscribe;
    private ChangeListener mListener;

    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_changebaseurl;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mListener = new ChangeListener();
        RetrofitUrlManager.getInstance().registerUrlChangeListener(mListener);
    }

    @OnClick({R.id.defaultButton, R.id.oneChangeButton, R.id.allChangeButton, R.id.clearChangeButton,R.id.textOneButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.defaultButton:

                /**
                 * 最开始的时候 此方法的 接口是没有问题的。但是不知何时 关闭了此接口。
                 * 网络错误会报：HttpException: HTTP 404 Not Found 是对着的。
                 */
                mPresenter.defaultGetInfo();
                break;
            case R.id.oneChangeButton:

                /**
                 * RetrofitUrlManager.getInstance().putDomain("change", "http://121.42.248.165:8088/");
                 *  此处为了测试 更换BaseUrl ,具体使用方法 参看 JessYan 的 RetrofitManager:https://github.com/JessYanCoding/RetrofitUrlManager/blob/master/README-zh.md
                 *  具体 BaseUrl 带有“/” 级别的 请看 JessYan 简书文章：https://www.jianshu.com/p/35a8959c2f86
                 *  此处简单说明一下 当前方法的使用
                 *
                 *  点击 此方法对应的按钮后，改变其BaseUrl，但是通过 网络打印 连接地址 还是为 改变之前的BaseUrl ，莫慌！！！，看清楚 网络信息
                 *  此处的网络日志打印 里 显示出了 ：Domain-Name: change   也就是说此处起到左右了，由于网络连接设置了 重连机制 并且超时时间设置的为 60s
                 *  待其重连结束后 可以看出 网络错误为：java.net.SocketTimeoutException: failed to connect to /121.42.248.165 (port 8088) after 60000ms
                 *  说明此时的BaseUrl 已经改变了。
                 */
                String s = editTextView.getText().toString();
                RetrofitUrlManager.getInstance().putDomain("change", s);

                mPresenter.oneChangeGetInfo();
                break;
            case R.id.allChangeButton:
                String all = editTextView.getText().toString();
                RetrofitUrlManager.getInstance().setGlobalDomain(all);
                mPresenter.allChangeGetInfo();
                break;
            case R.id.clearChangeButton:
                RetrofitUrlManager.getInstance().removeGlobalDomain();
                LogUtils.e("移除了全局baseUrl");
                ToastUtils.initToast("移除了全局baseUrl");
                break;
            case R.id.textOneButton:
                mPresenter.oneChangeGetInfo();
                break;
        }
    }

    private class ChangeListener implements onUrlChangeListener {

        @Override
        public void onUrlChangeBefore(HttpUrl oldUrl, String domainName) {
            LogUtils.e(String.format("The oldUrl is <%s>, ready fetch <%s> from DomainNameHub",
                    oldUrl.toString(),
                    domainName));
        }

        @Override
        public void onUrlChanged(final HttpUrl newUrl, HttpUrl oldUrl) {
            mSubscribe = Observable.just(1)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            ToastUtils.initToast("The newUrl is { " + newUrl.toString() + " }");
                            LogUtils.e("The newUrl is { " + newUrl.toString() + " }");
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            throwable.printStackTrace();
                        }
                    });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RetrofitUrlManager.getInstance().unregisterUrlChangeListener(mListener); //记住注销监听器
        if (mSubscribe != null) {
            mSubscribe.dispose();
        }
    }
}
