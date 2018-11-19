package com.jiayang.mvp.mvpframework.v.activity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.common.Constants;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.p.MainActivityPst;
import com.jiayang.mvp.mvpframework.utils.DialogUtils;
import com.jiayang.mvp.mvpframework.utils.LogUtils;
import com.jiayang.mvp.mvpframework.utils.NumAnim;
import com.jiayang.mvp.mvpframework.utils.PermissionUtils;
import com.jiayang.mvp.mvpframework.v.base.BaseActivity;
import com.jiayang.mvp.mvpframework.v.iview.MainActivityViewIpm;
import com.jiayang.mvp.mvpframework.widget.pickerview.TimePicker;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

public class MainActivity extends BaseActivity<MainActivityPst> implements MainActivityViewIpm {


    //必须的权限 预防6.0动态权限   此处模拟 两个权限
    public String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.animNumTextView)
    TextView animNumTextView;

    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        }

        LogUtils.e("JY");
        initImageView();
        activityMain.postDelayed(new Runnable() {
            @Override
            public void run() {
                NumAnim.startAnim(animNumTextView, 0.01f, 1500);
            }
        }, 2000);

    }

    private void initImageView() {
        Glide.with(this)
                .load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
                .placeholder(R.mipmap.ic_launcher)   //占位符
                .error(R.mipmap.ic_launcher)         //错误显示
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)    //磁盘缓存方案：只存原图
                .crossFade()                //默认淡入动画
                //                .centerCrop()             //缩放方式
                .into(imageView);
    }

    private void checkPermission() {
        PermissionUtils.requestPermissions(this, Constants.PERMISSION_REQUEST_CODE, permissions, new PermissionUtils.OnPermissionListener() {
            @Override
            public void onPermissionGranted(List<String> granted) {
                DialogUtils.dismissDialogRequestPermission();
                if (granted.size() == permissions.length) {
                    //                    mPresenter.getHomeInfo();     再次请求数据
                }
            }

            @Override
            public void onPermissionDenied(List<String> denied) {
                DialogUtils.showDialogRequestPermission(MainActivity.this);
            }
        });
    }

    public void button(View view) {
        /**
         * 最开始的时候 此方法的 接口是没有问题的。但是不知何时 关闭了此接口。
         * 网络错误会报：HttpException: HTTP 404 Not Found 是对着的。
         */
        mPresenter.show();
    }

    public void changeUrl(View view) {
        /**
         *  此处为了测试 更换BaseUrl ,具体使用方法 参看 JessYan 的 RetrofitManager:https://github.com/JessYanCoding/RetrofitUrlManager/blob/master/README-zh.md
         *  具体 BaseUrl 带有“/” 级别的 请看 JessYan 简书文章：https://www.jianshu.com/p/35a8959c2f86
         *  此处简单说明一下 当前方法的使用
         *
         *  点击 此方法对应的按钮后，改变其BaseUrl，但是通过 网络打印 连接地址 还是为 改变之前的BaseUrl ，莫慌！！！，看清楚 网络信息
         *  此处的网络日志打印 里 显示出了 ：Domain-Name: change   也就是说此处起到左右了，由于网络连接设置了 重连机制 并且超时时间设置的为 60s
         *  待其重连结束后 可以看出 网络错误为：java.net.SocketTimeoutException: failed to connect to /121.42.248.165 (port 8088) after 60000ms
         *  说明此时的BaseUrl 已经改变了。
         */
        RetrofitUrlManager.getInstance().putDomain("change", "http://121.42.248.165:8088/");
        mPresenter.changeUrl();
    }

    public void showTime(View view) {
        TimePicker build = new TimePicker.Builder(this)
                .setTimeChange(true)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setTimeSelectListener(new TimePicker.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {

                    }

                    @Override
                    public void onTimeChangeSelect(String leftTimeStr, String rightTimeStr, View view) {

                    }
                })
                .build();

        build.show();
    }

    @Override
    public void show() {

    }

    @Override
    public void fillData(String s) {
        textView.setText(s);

    }

}
