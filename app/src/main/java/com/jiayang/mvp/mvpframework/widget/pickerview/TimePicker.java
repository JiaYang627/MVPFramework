package com.jiayang.mvp.mvpframework.widget.pickerview;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.utils.ToastUtilsBlankJ;
import com.jiayang.mvp.mvpframework.widget.pickerview.lib.WheelView;
import com.jiayang.mvp.mvpframework.widget.pickerview.listener.CustomListener;
import com.jiayang.mvp.mvpframework.widget.pickerview.view.BasePickerView;
import com.jiayang.mvp.mvpframework.widget.pickerview.view.WheelTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间选择器
 * Created by Sai on 15/11/22.
 * Updated by XiaoSong on 2017-2-22.
 */
public class TimePicker extends BasePickerView implements View.OnClickListener {
    private int layoutRes;
    private CustomListener customListener;

    WheelTime wheelTime; //自定义控件
    private Button btnSubmit, btnCancel; //确定、取消按钮
    private TextView tvTitle;//标题
    private OnTimeSelectListener timeSelectListener;//回调接口
    private boolean timeChange;//
    private int gravity = Gravity.CENTER;//内容显示位置 默认居中
    private boolean[] type;// 显示类型

    private String strSubmit;//确定按钮字符串
    private String strCancel;//取消按钮字符串
    private String strTitle;//标题字符串

    private int colorSubmit;//确定按钮颜色
    private int colorCancel;//取消按钮颜色
    private int colorTitle;//标题颜色

    private int colorBackgroundWheel;//滚轮背景颜色
    private int colorBackgroundTitle;//标题背景颜色

    private int sizeSubmitCancel;//确定取消按钮大小
    private int sizeTitle;//标题字体大小
    private int sizeContent;//内容字体大小

    private Calendar date;//当前选中时间
    private Calendar startDate;//开始时间
    private Calendar endDate;//终止时间
    private int startYear;//开始年份
    private int endYear;//结尾年份

    private boolean cyclic;//是否循环
    private boolean cancelable;//是否能取消
    private boolean isCenterLabel;//是否只显示中间的label
    private boolean isLunarCalendar;//是否显示农历

    private int textColorOut; //分割线以外的文字颜色
    private int textColorCenter; //分割线之间的文字颜色
    private int dividerColor; //分割线的颜色
    private int backgroundId; //显示时的外部背景色颜色,默认是灰色

    // 条目间距倍数 默认1.6
    private float lineSpacingMultiplier = 1.6F;
    private boolean isDialog;//是否是对话框模式
    private String labelYear, labelMonth, labelDay, labelHours, labelMinute, labelSeconds;
    private int xOffsetYear, xOffsetMonth, xOffsetDay, xOffsetHours, xOffsetMinute, xOffsetSeconds;
    private WheelView.DividerType dividerType;//分隔线类型

    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";
    private TextView mLeftTimeTextView;
    private TextView mRightTimeTextView;

    //构造方法
    public TimePicker(Builder builder) {
        super(builder.context);
        this.timeSelectListener = builder.timeSelectListener;
        this.timeChange = builder.timeChange;// 是否显示 时间范围布局 等相关开关
        this.gravity = builder.gravity;
        this.type = builder.type;
        this.strSubmit = builder.strSubmit;
        this.strCancel = builder.strCancel;
        this.strTitle = builder.strTitle;
        this.colorSubmit = builder.colorSubmit;
        this.colorCancel = builder.colorCancel;
        this.colorTitle = builder.colorTitle;
        this.colorBackgroundWheel = builder.colorBackgroundWheel;
        this.colorBackgroundTitle = builder.colorBackgroundTitle;
        this.sizeSubmitCancel = builder.sizeSubmitCancel;
        this.sizeTitle = builder.sizeTitle;
        this.sizeContent = builder.sizeContent;
        this.startYear = builder.startYear;
        this.endYear = builder.endYear;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.date = builder.date;
        this.cyclic = builder.cyclic;
        this.isCenterLabel = builder.isCenterLabel;
        this.isLunarCalendar = builder.isLunarCalendar;
        this.cancelable = builder.cancelable;
        this.labelYear = builder.labelYear;
        this.labelMonth = builder.labelMonth;
        this.labelDay = builder.labelDay;
        this.labelHours = builder.labelHours;
        this.labelMinute = builder.labelMinute;
        this.labelSeconds = builder.labelSeconds;
        this.xOffsetYear = builder.xOffsetYear;
        this.xOffsetMonth = builder.xOffsetMonth;
        this.xOffsetDay = builder.xOffsetDay;
        this.xOffsetHours = builder.xOffsetHours;
        this.xOffsetMinute = builder.xOffsetMinute;
        this.xOffsetSeconds = builder.xOffsetSeconds;
        this.textColorCenter = builder.textColorCenter;
        this.textColorOut = builder.textColorOut;
        this.dividerColor = builder.dividerColor;
        this.customListener = builder.customListener;
        this.layoutRes = builder.layoutRes;
        this.lineSpacingMultiplier = builder.lineSpacingMultiplier;
        this.isDialog = builder.isDialog;
        this.dividerType = builder.dividerType;
        this.backgroundId = builder.backgroundId;
        this.decorView = builder.decorView;
        initView(builder.context);
    }


    //建造器
    public static class Builder {
        private int layoutRes = R.layout.pickerview_time;
        private CustomListener customListener;
        private Context context;
        private OnTimeSelectListener timeSelectListener;
        private boolean timeChange; // 是否显示 时间范围布局 等相关开关
        private boolean[] type = new boolean[]{true, true, true, true, true, true};//显示类型 默认全部显示
        private int gravity = Gravity.CENTER;//内容显示位置 默认居中

        private String strSubmit;//确定按钮文字
        private String strCancel;//取消按钮文字
        private String strTitle;//标题文字

        private int colorSubmit;//确定按钮颜色
        private int colorCancel;//取消按钮颜色
        private int colorTitle;//标题颜色

        private int colorBackgroundWheel;//滚轮背景颜色
        private int colorBackgroundTitle;//标题背景颜色

        private int sizeSubmitCancel = 17;//确定取消按钮大小
        private int sizeTitle = 18;//标题字体大小
        private int sizeContent = 18;//内容字体大小
        private Calendar date;//当前选中时间
        private Calendar startDate;//开始时间
        private Calendar endDate;//终止时间
        private int startYear;//开始年份
        private int endYear;//结尾年份

        private boolean cyclic = false;//是否循环
        private boolean cancelable = true;//是否能取消

        private boolean isCenterLabel = true;//是否只显示中间的label
        private boolean isLunarCalendar = false;//是否显示农历
        public ViewGroup decorView;//显示pickerview的根View,默认是activity的根view

        private int textColorOut; //分割线以外的文字颜色
        private int textColorCenter; //分割线之间的文字颜色
        private int dividerColor; //分割线的颜色
        private int backgroundId; //显示时的外部背景色颜色,默认是灰色
        private WheelView.DividerType dividerType;//分隔线类型
        // 条目间距倍数 默认1.6
        private float lineSpacingMultiplier = 1.6F;

        private boolean isDialog;//是否是对话框模式

        private String labelYear, labelMonth, labelDay, labelHours, labelMinute, labelSeconds;//单位
        private int xOffsetYear, xOffsetMonth, xOffsetDay, xOffsetHours, xOffsetMinute, xOffsetSeconds;//单位

        //Required
        public Builder(Context context, OnTimeSelectListener listener) {
            this.context = context;
            this.timeSelectListener = listener;
        }

        //Required
        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTimeSelectListener(OnTimeSelectListener listener) {
            this.timeSelectListener = listener;
            return this;
        }

        public Builder setTimeChange(boolean openTimeChange) {
            this.timeChange = openTimeChange;
            WheelView.timeChange = openTimeChange;
            return this;
        }

        //Option
        public Builder setType(boolean[] type) {
            this.type = type;
            return this;
        }

        public Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setSubmitText(String Str_Submit) {
            this.strSubmit = Str_Submit;
            return this;
        }

        public Builder isDialog(boolean isDialog) {
            this.isDialog = isDialog;
            return this;
        }

        public Builder setCancelText(String Str_Cancel) {
            this.strCancel = Str_Cancel;
            return this;
        }

        public Builder setTitleText(String Str_Title) {
            this.strTitle = Str_Title;
            return this;
        }

        public Builder setSubmitColor(int Color_Submit) {
            this.colorSubmit = Color_Submit;
            return this;
        }

        public Builder setCancelColor(int Color_Cancel) {
            this.colorCancel = Color_Cancel;
            return this;
        }

        /**
         * 必须是viewgroup
         * 设置要将pickerview显示到的容器id
         *
         * @param decorView
         * @return
         */
        public Builder setDecorView(ViewGroup decorView) {
            this.decorView = decorView;
            return this;
        }

        public Builder setBgColor(int Color_Background_Wheel) {
            this.colorBackgroundWheel = Color_Background_Wheel;
            return this;
        }

        public Builder setTitleBgColor(int Color_Background_Title) {
            this.colorBackgroundTitle = Color_Background_Title;
            return this;
        }

        public Builder setTitleColor(int Color_Title) {
            this.colorTitle = Color_Title;
            return this;
        }

        public Builder setSubCalSize(int sizeSubmitCancel) {
            this.sizeSubmitCancel = sizeSubmitCancel;
            return this;
        }

        public Builder setTitleSize(int sizeTitle) {
            this.sizeTitle = sizeTitle;
            return this;
        }

        public Builder setContentSize(int sizeContent) {
            this.sizeContent = sizeContent;
            return this;
        }

        /**
         * 因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         *
         * @param date
         * @return
         */
        public Builder setDate(Calendar date) {
            this.date = date;
            return this;
        }

        public Builder setLayoutRes(int res, CustomListener customListener) {
            this.layoutRes = res;
            this.customListener = customListener;
            return this;
        }

        public Builder setRange(int startYear, int endYear) {
            this.startYear = startYear;
            this.endYear = endYear;
            return this;
        }

        /**
         * 设置起始时间
         * 因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         *
         * @return
         */

        public Builder setRangDate(Calendar startDate, Calendar endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
            return this;
        }


        /**
         * 设置间距倍数,但是只能在1.2-2.0f之间
         *
         * @param lineSpacingMultiplier
         */
        public Builder setLineSpacingMultiplier(float lineSpacingMultiplier) {
            this.lineSpacingMultiplier = lineSpacingMultiplier;
            return this;
        }

        /**
         * 设置分割线的颜色
         *
         * @param dividerColor
         */
        public Builder setDividerColor(int dividerColor) {
            this.dividerColor = dividerColor;
            return this;
        }

        /**
         * 设置分割线的类型
         *
         * @param dividerType
         */
        public Builder setDividerType(WheelView.DividerType dividerType) {
            this.dividerType = dividerType;
            return this;
        }

        /**
         * //显示时的外部背景色颜色,默认是灰色
         *
         * @param backgroundId
         */

        public Builder setBackgroundId(int backgroundId) {
            this.backgroundId = backgroundId;
            return this;
        }

        /**
         * 设置分割线之间的文字的颜色
         *
         * @param textColorCenter
         */
        public Builder setTextColorCenter(int textColorCenter) {
            this.textColorCenter = textColorCenter;
            return this;
        }

        /**
         * 设置分割线以外文字的颜色
         *
         * @param textColorOut
         */
        public Builder setTextColorOut(int textColorOut) {
            this.textColorOut = textColorOut;
            return this;
        }

        public Builder isCyclic(boolean cyclic) {
            this.cyclic = cyclic;
            return this;
        }

        public Builder setOutSideCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setLunarCalendar(boolean lunarCalendar) {
            isLunarCalendar = lunarCalendar;
            return this;
        }

        public Builder setLabel(String labelYear, String labelMonth, String labelDay, String labelHours, String labelMinute, String labelSeconds) {
            this.labelYear = labelYear;
            this.labelMonth = labelMonth;
            this.labelDay = labelDay;
            this.labelHours = labelHours;
            this.labelMinute = labelMinute;
            this.labelSeconds = labelSeconds;
            return this;
        }

        /**
         * 设置X轴倾斜角度[ -90 , 90°]
         *
         * @param xOffsetYear    年
         * @param xOffsetMonth   月
         * @param xOffsetDay     日
         * @param xOffsetHours   时
         * @param xOffsetMinute  分
         * @param xOffsetSeconds 秒
         * @return
         */
        public Builder setTextXOffset(int xOffsetYear, int xOffsetMonth, int xOffsetDay, int xOffsetHours, int xOffsetMinute, int xOffsetSeconds) {
            this.xOffsetYear = xOffsetYear;
            this.xOffsetMonth = xOffsetMonth;
            this.xOffsetDay = xOffsetDay;
            this.xOffsetHours = xOffsetHours;
            this.xOffsetMinute = xOffsetMinute;
            this.xOffsetSeconds = xOffsetSeconds;
            return this;
        }

        public Builder isCenterLabel(boolean isCenterLabel) {
            this.isCenterLabel = isCenterLabel;
            return this;
        }

        public TimePicker build() {
            return new TimePicker(this);
        }
    }


    private void initView(Context context) {
        setDialogOutSideCancelable(cancelable);
        initViews(backgroundId);
        init();
        initEvents();
        if (customListener == null) {
            LayoutInflater.from(context).inflate(R.layout.pickerview_time, contentContainer);

            //顶部标题
            tvTitle = (TextView) findViewById(R.id.tvTitle);

            if (timeChange) {

                // 左侧时间
                mLeftTimeTextView = (TextView) findViewById(R.id.leftTextView);

                // 右侧时间
                mRightTimeTextView = (TextView) findViewById(R.id.rightTextView);

                mLeftTimeTextView.setText("2018/09/01");
                mRightTimeTextView.setText(parseDateToStringTime(new Date(System.currentTimeMillis())));

                mLeftTimeTextView.setSelected(true);
                mLeftTimeTextView.setTextColor(Color.parseColor("#2a99ff"));

                mLeftTimeTextView.setOnClickListener(this);
                mRightTimeTextView.setOnClickListener(this);
                strTitle = "时间范围";
                colorTitle = Color.parseColor("#333333");


                currentView = mLeftTimeTextView;

            }
            findViewById(R.id.selectTimeLayout).setVisibility(timeChange ? View.VISIBLE : View.GONE);

            //确定和取消按钮
            btnSubmit = (Button) findViewById(R.id.btnSubmit);
            btnCancel = (Button) findViewById(R.id.btnCancel);

            btnSubmit.setTag(TAG_SUBMIT);
            btnCancel.setTag(TAG_CANCEL);

            btnSubmit.setOnClickListener(this);
            btnCancel.setOnClickListener(this);

            //设置文字
            btnSubmit.setText(TextUtils.isEmpty(strSubmit) ? context.getResources().getString(R.string.pickerview_submit) : strSubmit);
            btnCancel.setText(TextUtils.isEmpty(strCancel) ? context.getResources().getString(R.string.pickerview_cancel) : strCancel);
            tvTitle.setText(TextUtils.isEmpty(strTitle) ? "" : strTitle);//默认为空

            //设置文字颜色
            btnSubmit.setTextColor(colorSubmit == 0 ? pickerviewTimebtnNor : colorSubmit);
            btnCancel.setTextColor(colorCancel == 0 ? pickerviewTimebtnNor : colorCancel);
            tvTitle.setTextColor(colorTitle == 0 ? pickerviewTopbarTitle : colorTitle);

            //设置文字大小
            btnSubmit.setTextSize(sizeSubmitCancel);
            btnCancel.setTextSize(sizeSubmitCancel);
            tvTitle.setTextSize(sizeTitle);
            RelativeLayout rvTopBar = (RelativeLayout) findViewById(R.id.rv_topbar);
            rvTopBar.setBackgroundColor(colorBackgroundTitle == 0 ? pickerviewBgTopbar : colorBackgroundTitle);
        } else {
            customListener.customLayout(LayoutInflater.from(context).inflate(layoutRes, contentContainer));
        }
        // 时间转轮 自定义控件
        LinearLayout timePickerView = (LinearLayout) findViewById(R.id.timepicker);

        timePickerView.setBackgroundColor(colorBackgroundWheel == 0 ? bgColorDefault : colorBackgroundWheel);

        wheelTime = new WheelTime(timePickerView, type, gravity, sizeContent);
        wheelTime.setLunarCalendar(isLunarCalendar);

        if (startYear != 0 && endYear != 0 && startYear <= endYear) {
            setRange();
        }

        if (startDate != null && endDate != null) {
            if (startDate.getTimeInMillis() <= endDate.getTimeInMillis()) {

                setRangDate();
            }
        } else if (startDate != null && endDate == null) {
            setRangDate();
        } else if (startDate == null && endDate != null) {
            setRangDate();
        }

        setTime();
        wheelTime.setLabels(labelYear, labelMonth, labelDay, labelHours, labelMinute, labelSeconds);
        wheelTime.setTextXOffset(xOffsetYear, xOffsetMonth, xOffsetDay, xOffsetHours, xOffsetMinute, xOffsetSeconds);

        setOutSideCancelable(cancelable);
        wheelTime.setCyclic(cyclic);
        wheelTime.setDividerColor(dividerColor);
        wheelTime.setDividerType(dividerType);
        wheelTime.setLineSpacingMultiplier(lineSpacingMultiplier);
        wheelTime.setTextColorOut(textColorOut);
        wheelTime.setTextColorCenter(textColorCenter);
        wheelTime.isCenterLabel(isCenterLabel);
        if (timeChange) {

            wheelTime.setOnTimeChangeListener(new WheelTime.onTimeChangeListener() {
                @Override
                public void onTimeChange() {
                    timeTextChange();
                }
            });
        }
    }

    private void timeTextChange() {
        try {
            //            ToastUtils.initToast("公历 -- > " + parseDateToStringTime(WheelTime.dateFormat.parse(wheelTime.getTime())));

            String beforeStr = ((TextView) currentView).getText().toString();
            ((TextView)currentView).setText(parseDateToStringTime(WheelTime.dateFormat.parse(wheelTime.getTime())));
            if (parseDate2TimeMillis1(mLeftTimeTextView.getText().toString()) > parseDate2TimeMillis1(mRightTimeTextView.getText().toString())) {
                ToastUtilsBlankJ.showShort(currentView == mLeftTimeTextView ?"开始时间不能晚于结束时间" :"结束时间不能早于开始时间");
                ((TextView) currentView).setText(beforeStr);
                changeWheelToText(beforeStr);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    /**
     * 设置默认时间
     */
    public void setDate(Calendar date) {
        this.date = date;
        setTime();
    }

    /**
     * 设置可以选择的时间范围, 要在setTime之前调用才有效果
     */
    private void setRange() {
        wheelTime.setStartYear(startYear);
        wheelTime.setEndYear(endYear);

    }

    /**
     * 设置可以选择的时间范围, 要在setTime之前调用才有效果
     */
    private void setRangDate() {
        wheelTime.setRangDate(startDate, endDate);
        //如果设置了时间范围
        if (startDate != null && endDate != null) {
            //判断一下默认时间是否设置了，或者是否在起始终止时间范围内
            if (date == null || date.getTimeInMillis() < startDate.getTimeInMillis()
                    || date.getTimeInMillis() > endDate.getTimeInMillis()) {
                date = startDate;
            }
        } else if (startDate != null) {
            //没有设置默认选中时间,那就拿开始时间当默认时间
            date = startDate;
        } else if (endDate != null) {
            date = endDate;
        }
    }

    /**
     * 设置选中时间,默认选中当前时间
     */
    private void setTime() {
        int year, month, day, hours, minute, seconds;

        Calendar calendar = Calendar.getInstance();

        if (date == null) {
            calendar.setTimeInMillis(System.currentTimeMillis());
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            hours = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
            seconds = calendar.get(Calendar.SECOND);
        } else {
            year = date.get(Calendar.YEAR);
            month = date.get(Calendar.MONTH);
            day = date.get(Calendar.DAY_OF_MONTH);
            hours = date.get(Calendar.HOUR_OF_DAY);
            minute = date.get(Calendar.MINUTE);
            seconds = date.get(Calendar.SECOND);
        }

        wheelTime.setPicker(year, month, day, hours, minute, seconds);
    }

    private View currentView;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.leftTextView || v.getId() == R.id.rightTextView) {
            if (currentView == null) {
                return;
            }

            // 上一个改变状态
            changeTextStatus();

            currentView = v;

            // 当前选中的改变状态
            changeTextStatus();

            changeWheelToText(null);



            return;
        }
        String tag = (String) v.getTag();
        if (tag.equals(TAG_SUBMIT)) {
            returnData();
        }
        dismiss();
    }


    /**
     * 选中 左右两侧 时间节点后，保证时间轮子以 TextView上的时间
     * 开始时间 晚于 结束时间 ，改变轮子选中时间
     * 结束时间 早于 开始时间 ，改变轮子选中时间
     */
    private void changeWheelToText(String time) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(parseDate2TimeMillis1(TextUtils.isEmpty(time) ? ((TextView) currentView).getText().toString() : time));
        setDate(calendar);
    }

    /**
     * 选中 开始/结束时间 控件后，改变显示状态
     */
    private void changeTextStatus() {
        currentView.setSelected(!currentView.isSelected());
        ((TextView) currentView).setTextColor(currentView.isSelected() ? Color.parseColor("#2a99ff") : Color.parseColor("#666666"));
    }


    public void returnData() {

        if (timeSelectListener != null) {
            try {
                if (timeChange) {

                    timeSelectListener.onTimeChangeSelect(mLeftTimeTextView.getText().toString(), mRightTimeTextView.getText().toString(), clickView);
                    ToastUtilsBlankJ.showShort("选择的时间范围为：" + mLeftTimeTextView.getText().toString() + "  --  " + mRightTimeTextView.getText().toString());
                } else {

                    Date date = WheelTime.dateFormat.parse(wheelTime.getTime());

                    timeSelectListener.onTimeSelect(date, clickView);
                    ToastUtilsBlankJ.showShort("选择的时间为：" + date.toString());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }


    public void setLunarCalendar(boolean lunar) {
        try {
            int year, month, day, hours, minute, seconds;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(WheelTime.dateFormat.parse(wheelTime.getTime()));
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            hours = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
            seconds = calendar.get(Calendar.SECOND);

            wheelTime.setLunarCalendar(lunar);
            wheelTime.setLabels(labelYear, labelMonth, labelDay, labelHours, labelMinute, labelSeconds);
            wheelTime.setPicker(year, month, day, hours, minute, seconds);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public boolean isLunarCalendar() {
        return wheelTime.isLunarCalendar();
    }


    public interface OnTimeSelectListener {
        void onTimeSelect(Date date, View v);

        void onTimeChangeSelect(String leftTimeStr, String rightTimeStr, View view);
    }

    @Override
    public boolean isDialog() {
        return isDialog;
    }

    public static String parseDateToStringTime(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(d);
    }

    /**
     * 字符串日期转成秒值
     */
    public static long parseDate2TimeMillis1(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date d = sdf.parse(date);
            return d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}
