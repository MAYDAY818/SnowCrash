package cn.edu.hebtu.software.snowcarsh2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.snowcarsh2.R;
import cn.edu.hebtu.software.snowcarsh2.activity.childrenfragment5.ChildrenFragment5Activity;
import cn.edu.hebtu.software.snowcarsh2.adapter.childrenfragment5.RefreshListView;
import cn.edu.hebtu.software.snowcarsh2.adapter.childrenfragment5.VideoAdapter;
import cn.edu.hebtu.software.snowcarsh2.bean.Video;


public class ChildrenFragment5 extends Fragment {
    public static ChildrenFragment5 newInstance() {
        ChildrenFragment5 fragment = new ChildrenFragment5();
        return fragment;

    }
    @Nullable
    private RefreshListView rflv;
    private View view;
    VideoAdapter videoAdapter;
    List<Video> videos;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg1) {
            if (msg1.what == 0) {
                //加载完数据之后，要更新适配器和隐藏尾布局
                videoAdapter.notifyDataSetChanged();
                rflv.completeLoad();
            } else if (msg1.what == 1) {
                //刷新完数据之后，要更新适配器和隐藏头布局
                videoAdapter.notifyDataSetChanged();
                rflv.completeRefresh();
            }
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initView(inflater,container);
        initData();
        update();

        rflv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),ChildrenFragment5Activity.class);
                //根据位置查询对应到id，点击的那个视频，获得哪个
                //传递视频id
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        return view;
    }
    //加载视图
    private void initView(LayoutInflater inflater,ViewGroup container) {
        view = inflater.inflate(R.layout.childrenfragment5,
                container,
                false);
        rflv = view.findViewById(R.id.rflv);
        videos = new ArrayList<>();
    }

    //加载数据
    private void initData() {
        Video video1 = new Video(R.drawable.read1,"《驯龙高手3》即将上线","22'12",54,4);
        Video video2 = new Video(R.drawable.read2,"《驯龙高手3》即将上线","22'12",54,4);
        Video video3 = new Video(R.drawable.read1,"《驯龙高手3》即将上线","22'12",54,4);
        Video video4 = new Video(R.drawable.read2,"《驯龙高手3》即将上线","22'12",54,4);
        Video video5 = new Video(R.drawable.read1,"《驯龙高手3》即将上线","22'12",54,4);
        Video video6 = new Video(R.drawable.read2,"《驯龙高手3》即将上线","22'12",54,4);
        Video video7 = new Video(R.drawable.read1,"《驯龙高手3》即将上线","22'12",54,4);
        Video video8 = new Video(R.drawable.read2,"《驯龙高手3》即将上线","22'12",54,4);
        Video video9 = new Video(R.drawable.read1,"《驯龙高手3》即将上线","22'12",54,4);

        videos.add(video1);
        videos.add(video2);
        videos.add(video3);
        videos.add(video4);
        videos.add(video5);
        videos.add(video6);
        videos.add(video7);
        videos.add(video8);
        videos.add(video9);
        videoAdapter = new VideoAdapter(getActivity(), R.layout.childrenfragment5_item,videos);
        rflv.setAdapter(videoAdapter);
    }

    private void update() {
        //刷新数据操作
        rflv.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            public void onRefresh() {
                videos.clear();
                Video v1 = new Video(R.drawable.read2,"《小黄人大眼萌》即将上线","22'12",54,4);
//                Video v2 = new Video(R.drawable.radio4,"《狮子王》即将上线","22'12",54,4);
                videos.add(v1);
                videos.add(v1);
                videos.add(v1);
                videos.add(v1);
                videos.add(v1);
                videos.add(v1);
                videos.add(v1);
                videos.add(v1);
                videos.add(v1);
//                for (int i=0;i<9;i++){
//                    if ( i%3 ==0 ){
//                        videos.add(v1);
//                    }else {
//                        videos.add(v2);
//                    }
//                }
                handler.sendEmptyMessageDelayed(1, 2000);
            }
        });
        //加载数据操作
        rflv.setOnLoadListener(new RefreshListView.OnLoadListener() {
            @Override
            public void onLoad() {
//                Video v1 = new Video(R.drawable.radio3,"《小黄人大眼萌》即将上线","22'12",54,4);
                Video v2 = new Video(R.drawable.read1,"《狮子王》即将上线","22'12",54,4);
//                for (int i=0;i<9;i++){
//                    if ( i%3 ==0 ){
//                        videos.add(v1);
//                    }else {
//                        videos.add(v2);
//                    }
//                }
                videos.add(v2);
                videos.add(v2);
                videos.add(v2);
                videos.add(v2);
                videos.add(v2);
                videos.add(v2);
                videos.add(v2);
                videos.add(v2);
                videos.add(v2);
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        });
    }

}
