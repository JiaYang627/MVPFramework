package com.jiayang.mvp.mvpframework.v.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.p.TimeSelectActivityPst;
import com.jiayang.mvp.mvpframework.v.base.BaseActivity;
import com.jiayang.mvp.mvpframework.v.iview.TimeSelectActivityViewIpm;
import com.jiayang.mvp.mvpframework.widget.pickerview.TimePicker;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author ：张 奎
 * @date ：2018-11-20 09：17
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */
public class TimeSelectActivity extends BaseActivity<TimeSelectActivityPst> implements TimeSelectActivityViewIpm {
    @BindView(R.id.timeChangeButton)
    Button timeChangeButton;
    @BindView(R.id.noTimeChangeButton)
    Button noTimeChangeButton;
    private TimePicker mBuild;

    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_timeselect;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.timeChangeButton, R.id.noTimeChangeButton})
    public void onViewClicked(View view) {
        if (mBuild.isShowing()) {
            mBuild.dismiss();
        }
        switch (view.getId()) {
            case R.id.timeChangeButton:
                timeShow(true);
                break;
            case R.id.noTimeChangeButton:
                timeShow(false);
                break;
        }
    }

    private void timeShow(boolean isShowChange) {
        mBuild = new TimePicker.Builder(this)
                .setTimeChange(isShowChange)
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

        mBuild.show();
    }
}
