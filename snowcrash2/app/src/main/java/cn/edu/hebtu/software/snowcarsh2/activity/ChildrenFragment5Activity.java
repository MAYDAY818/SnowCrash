package cn.edu.hebtu.software.snowcarsh2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlaybackException;

import chuangyuan.ycj.videolibrary.listener.VideoInfoListener;
import chuangyuan.ycj.videolibrary.video.GestureVideoPlayer;
import chuangyuan.ycj.videolibrary.video.ManualPlayer;
import cn.edu.hebtu.software.snowcarsh2.R;


public class ChildrenFragment5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children_fragment5);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",1);

        //根据视频id查询视频的名称，资源地址等等
        String title = "视频标题";
        String url = "http://120.79.80.250:8080/video/a.mp4";


        GestureVideoPlayer exoPlayerManager = new ManualPlayer(this, R.id.exo_play_context_id);
        //自定义你的数据源，后面详细介绍如何自定义数据源类
        //http://120.79.80.250:8080/video/a.MKV
        //exoPlayerManager.setPlayUri("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4");
        exoPlayerManager.setPlayUri(url);
        exoPlayerManager.setTitle(title);
        exoPlayerManager.setPosition(1000);
        exoPlayerManager.setVideoInfoListener(new VideoInfoListener() {
            @Override
            public void onPlayStart() {
                //开始播放
            }

            @Override
            public void onLoadingChanged() {
                //加载变化
            }

            @Override
            public void onPlayerError(ExoPlaybackException e) {
                //加载错误
            }

            @Override
            public void onPlayEnd() {
                //播放结束
            }

            @Override
            public void onBack() {
                //返回回调

                finish();

            }
            @Override
            public void onRepeatModeChanged(int repeatMode) {
                //模式变化
            }
        });
    }



}


