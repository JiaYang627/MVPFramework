package com.jiayang.mvp.mvpframework.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.common.BaseActivity;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.mvp.ipm.ComposeVideoAndAudioViewIpm;
import com.jiayang.mvp.mvpframework.mvp.presenter.ComposeVideoAndAudioPresenter;
import com.jiayang.mvp.mvpframework.utils.FileUtils;
import com.jiayang.mvp.mvpframework.utils.LogUtils;
import com.zlw.main.recorderlib.RecordManager;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 处理视频 音频的页面
 */
public class ComposeVideoAndAudioActivity extends BaseActivity<ComposeVideoAndAudioPresenter> implements ComposeVideoAndAudioViewIpm {


    @BindView(R.id.video)
    VideoView mVideoView;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.next)
    TextView mNext;
    @BindView(R.id.title_layout)
    RelativeLayout mTitleLayout;
    @BindView(R.id.video_seek_bar)
    AppCompatSeekBar mVideoSeekBar;
    @BindView(R.id.video_layout)
    LinearLayout mVideoLayout;
    @BindView(R.id.music_seek_bar)
    AppCompatSeekBar mMusicSeekBar;
    @BindView(R.id.local_music)
    TextView mLocalMusic;
    @BindView(R.id.start_audio_record)
    TextView mStartAudioRecord;
    @BindView(R.id.stop_audio_record)
    TextView mStopAudioRecord;
    @BindView(R.id.editor_layout)
    LinearLayout mEditorLayout;
    private RecordManager mRecordManager;
    private boolean mIsPlayer;
    private String mVideoPath;
    private int mVideoTime;
    private FileUtils mFileUtils;

    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_compose_video_and_audio;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initInfo();

        initVideo();
    }

    private void initInfo() {
        mRecordManager = RecordManager.getInstance();
        mIsPlayer = getIntent().getBooleanExtra("isPlayer", false);
        mVideoPath = getIntent().getStringExtra("path");
        mVideoTime = getIntent().getIntExtra("time", 0);

    }

    private void initVideo() {
        if (mIsPlayer) {
            mVideoView.setVideoPath(mVideoPath);
            //            mVideoView.setVideoPath("/storage/emulated/0/ffmpeg/videoMusicAudio.mp4");
            LogUtils.e("initVideo - isPlayer true  - VideoPath - " + mVideoPath);
            mVideoView.start();
        } else {
            mFileUtils = new FileUtils(this);


            LogUtils.e("initVideo - isPlayer false  - VideoPath - " + mVideoPath);
        }

    }

    @OnClick({R.id.back, R.id.local_music, R.id.start_audio_record, R.id.stop_audio_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                ComposeVideoAndAudioActivity.this.finish();
                break;
            case R.id.local_music:
                break;
            case R.id.start_audio_record:
                break;
            case R.id.stop_audio_record:
                break;
        }
    }
}
