package com.jiayang.mvp.mvpframework.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.common.BaseActivity;
import com.jiayang.mvp.mvpframework.common.MVPApp;
import com.jiayang.mvp.mvpframework.database.AppDatabase;
import com.jiayang.mvp.mvpframework.database.entities.User;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.mvp.ipm.RoomDbViewIpm;
import com.jiayang.mvp.mvpframework.mvp.presenter.RoomDbPresenter;
import com.jiayang.mvp.mvpframework.utils.LogUtils;

import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class RoomDbActivity extends BaseActivity<RoomDbPresenter> implements RoomDbViewIpm, View.OnClickListener {


    private AppDatabase mAppDataBase;
    private int mClickIndex;
    private String[] mAddress = new String[]{"杭州", "北京", "上海", "深圳", "西安", "南宁", "重庆"};
    private Disposable mAddDisposable;
    private Disposable mQueryDisposable;
    private Disposable mDelOneDisposable;
    private Disposable mDelAllDisposable;

    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_room_db;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mAppDataBase = ((MVPApp) getApplication()).getAppDataBase();

        findViewById(R.id.button_add_user).setOnClickListener(this::onClick);
        findViewById(R.id.button_get_user).setOnClickListener(this::onClick);
        findViewById(R.id.button_del_user).setOnClickListener(this::onClick);
        findViewById(R.id.button_del_all_user).setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_add_user:
                addUser();
                break;
            case R.id.button_get_user:
                getAllUser();
                break;
            case R.id.button_del_user:
                delOneUser();
                break;
            case R.id.button_del_all_user:
                delAllUser();
                break;
            default:
                break;
        }
    }

    private void addUser() {
        final User user = new User();
        user.setAddress(mAddress[mClickIndex % 7]);

        //                user.setAge(20);
        user.setName("佳洋");
        user.setPhone("12345678901");

        mClickIndex += 1;
        mAddDisposable = Observable.just(1).map(new Function<Integer, List<Long>>() {
            @Override
            public List<Long> apply(Integer integer) throws Exception {
                List<Long> insert = mAppDataBase.userDao().insert(user);
                return insert;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Long>>() {
                    @Override
                    public void accept(List<Long> longs) throws Exception {
                        if (longs != null) {
                            for (Long id : longs) {
                                LogUtils.e("JY - 新增成功  id = " + id);
                            }

                        }
                    }
                });
    }

    private void getAllUser() {


        mQueryDisposable = Observable.just(1).map(new Function<Integer, List<User>>() {
            @Override
            public List<User> apply(Integer integer) throws Exception {
                List<User> users = mAppDataBase.userDao().loadUser();
                return users;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        if (users != null && users.size() > 0) {
                            LogUtils.e("JY  -  查询到的数据有" + users.size() + "条");
                        } else {
                            LogUtils.e("JY  -  查询到的数据有0条");
                        }
                    }
                });

    }

    private void delOneUser() {
        final int mDelIndex = mClickIndex - 1;
        if (mDelIndex < 0) {
            Toast.makeText(RoomDbActivity.this, "请先添加一条数据", Toast.LENGTH_LONG).show();
            return;
        }

        mDelOneDisposable = Observable.just(1).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {

                int random = new Random().nextInt(mDelIndex) + 1;
                int delete = mAppDataBase.userDao().delete(new User(random));
                return delete;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        if (integer > 0) {
                            //size 表示删除个数
                            LogUtils.e("JY  -  删除成功");
                            mClickIndex -= 1;
                        } else {
                            LogUtils.e("JY  -  删除失败");
                        }
                    }
                });
    }

    private void delAllUser() {


        mDelAllDisposable = Observable.just(1).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
                int x = 0;
                List<User> all = mAppDataBase.userDao().loadUser();
                if (all != null && all.size() > 0) {
                    x = mAppDataBase.userDao().deleteAll(all);
                } else {
                    x = -1;
                }

                return x;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        if (integer > -1) {
                            LogUtils.e("JY  -  一共删除了" + integer + "条");
                            mClickIndex = 0;
                        } else {
                            LogUtils.e("JY  -  全部删除 删除失败");
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mAddDisposable != null) {

            mAddDisposable.dispose();
            mAddDisposable = null;
        }


        if (mQueryDisposable != null) {
            mQueryDisposable.dispose();
            mQueryDisposable = null;
        }

        if (mDelOneDisposable != null) {
            mDelOneDisposable.dispose();
            mDelOneDisposable = null;
        }

        if (mDelAllDisposable != null) {
            mDelAllDisposable.dispose();
            mDelAllDisposable = null;
        }
    }
}
