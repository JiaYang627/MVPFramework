package com.jiayang.mvp.mvpframework.mvp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.common.BaseActivity;
import com.jiayang.mvp.mvpframework.common.CommonDialog;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.mvp.ipm.SimpleFFmpegViewIpm;
import com.jiayang.mvp.mvpframework.mvp.presenter.SimpleFFmpegPresenter;
import com.jiayang.mvp.mvpframework.utils.LogUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class SimpleFFmpegActivity extends BaseActivity<SimpleFFmpegPresenter> implements SimpleFFmpegViewIpm {


    @BindView(R.id.single_record)
    Button mSingleRecord;
    private CommonDialog mCommonDialog;
    private int REQUEST_CODE_WRITE_SETTINGS = 111;
    private int MAX_DURATION = 1000 * 60 * 2;// 两分钟

    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_simple_ffmpeg;
    }


    @OnClick({R.id.single_record})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.single_record:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.System.canWrite(this)) {
                        initPermissionForWriteSetting();
                    } else {
                        selectChooseVideo();
                    }
                } else {
                    selectChooseVideo();
                }
                break;
            case R.id.cancelTextView:
                SimpleFFmpegActivity.this.finish();
                break;
            case R.id.confirmTextView:
                requestWriteSettings();
                break;
        }

    }

    /**
     * 确认是否打开系统设置 Dialog
     */
    private void initPermissionForWriteSetting() {

        View mContentView = getLayoutInflater().inflate(R.layout.dialog_permiss, null);

        TextView cancelTextView = mContentView.findViewById(R.id.cancelTextView);
        TextView confirmTextView = mContentView.findViewById(R.id.confirmTextView);
        mCommonDialog = new CommonDialog(this);
        mCommonDialog.setCanceledOnTouchOutside(false);

        cancelTextView.setOnClickListener(this::onClick);
        confirmTextView.setOnClickListener(this::onClick);

        mCommonDialog.setContentView(mContentView);
    }

    private void requestWriteSettings() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQUEST_CODE_WRITE_SETTINGS);
    }

    /**
     * 选择视频
     */
    private void selectChooseVideo() {

        PictureSelector.create(SimpleFFmpegActivity.this)
                .openGallery(PictureMimeType.ofVideo())
                .maxSelectNum(1)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                //                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .compress(true)
                //                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                //                .selectionMedia(list)// 是否传入已选图片 List<LocalMedia> list
                //                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {


                // 图片、视频、音频选择结果回调
                List<LocalMedia> mSelectList = PictureSelector.obtainMultipleResult(data);
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的


                LocalMedia localMedia = mSelectList.get(0);

                LogUtils.e("JY  视频路径是:"+ localMedia.getPath());
                LogUtils.e("JY  视频长度是:"+ localMedia.getDuration());

                int mDuration = 0;

                if (localMedia.getDuration() > MAX_DURATION) {
                    mDuration = 120;
                } else {
                    mDuration = (int) (localMedia.getDuration() / 1000);
                }

                LogUtils.e("JY  视频播放长度为:"+ mDuration);
                Intent intent = new Intent(this, ComposeVideoAndAudioActivity.class);
                intent.putExtra("time", mDuration);
                intent.putExtra("path", localMedia.getPath());
                startActivity(intent);
            }
        } else if (requestCode == REQUEST_CODE_WRITE_SETTINGS) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.System.canWrite(this)) {
                    if (mCommonDialog != null && mCommonDialog.isShowing()) {
                        mCommonDialog.dismissDialog();
                    }
                    selectChooseVideo();
                }
            }
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCommonDialog != null && mCommonDialog.isShowing()) {
            mCommonDialog.dismissDialog();
        }
    }
}
