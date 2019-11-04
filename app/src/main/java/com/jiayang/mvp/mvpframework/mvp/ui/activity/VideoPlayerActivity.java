package com.jiayang.mvp.mvpframework.mvp.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.common.BaseActivity;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.mvp.ipm.VideoPlayerViewIpm;
import com.jiayang.mvp.mvpframework.mvp.presenter.VideoPlayerPresenter;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;


/**
 * 视频播放
 * <p>
 * https://github.com/CarGuo/GSYVideoPlayer
 */
public class VideoPlayerActivity extends BaseActivity<VideoPlayerPresenter> implements VideoPlayerViewIpm {


    @BindView(R.id.video_player)
    StandardGSYVideoPlayer mVideoPlayer;
    private OrientationUtils mOrientationUtils;

    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_player;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
//                String source1 = "https://hks-dev-test.oss-cn-qingdao.aliyuncs.com/admin/notice/d02d33a93b2f0f5c17ddb19181be1b9c_15414735776699851.mp4";
        String source1 = "https://hks-saas.oss-cn-qingdao.aliyuncs.com/admin/notice/全流程接车操作_15379510083035392.mp4";

        Map map = new HashMap<>();
        map.put("Referer", "http://www.kuaixiuge.com");
        mVideoPlayer.setUp(source1, true, null, map, "测试视频");

        //增加封面
        //        ImageView imageView = new ImageView(this);
        //        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //        imageView.setImageResource(R.mipmap.xxx1);
        //        videoPlayer.setThumbImageView(imageView);
        //增加title
        mVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        mVideoPlayer.getBackButton().setVisibility(View.GONE);
        //设置旋转
        mOrientationUtils = new OrientationUtils(this, mVideoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        mVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mOrientationUtils.resolveByClick();
                // 这里是全屏
                mVideoPlayer.startWindowFullscreen(VideoPlayerActivity.this, false, true);

            }
        });

        // 是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        mVideoPlayer.setAutoFullWithSize(true);
        //是否可以滑动调整
        mVideoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        mVideoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mVideoPlayer.startPlayLogic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (mOrientationUtils != null)
            mOrientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (mOrientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            mVideoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        mVideoPlayer.setVideoAllCallBack(null);
        super.onBackPressed();
    }
}
