package com.jiayang.mvp.mvpframework.v.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.p.NumAnimActivityPst;
import com.jiayang.mvp.mvpframework.utils.NumAnim;
import com.jiayang.mvp.mvpframework.v.base.BaseActivity;
import com.jiayang.mvp.mvpframework.v.iview.NumAnimActivityViewIpm;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 数字滚动
 * @author ：张 奎
 * @date ：2018-11-20 09：13
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */
public class NumAnimActivity extends BaseActivity<NumAnimActivityPst> implements NumAnimActivityViewIpm {
    @BindView(R.id.editTextView)
    EditText editTextView;
    @BindView(R.id.numTextView)
    TextView numTextView;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_numanim;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        String s = editTextView.getText().toString();
        if (TextUtils.isEmpty(s)) {
            s = "100";
        }
        NumAnim.startAnim(numTextView, Float.valueOf(s), 1000);
    }
}
