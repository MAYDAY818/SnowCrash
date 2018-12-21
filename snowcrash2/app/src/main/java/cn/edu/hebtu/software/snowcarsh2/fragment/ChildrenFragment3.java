package cn.edu.hebtu.software.snowcarsh2.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


import cn.edu.hebtu.software.snowcarsh2.R;
import cn.edu.hebtu.software.snowcarsh2.activity.ChildrenFragment5Activity;
import cn.edu.hebtu.software.snowcarsh2.adapter.childrenfragment3.CustomerAdapterRead;
import cn.edu.hebtu.software.snowcarsh2.adapter.childrenfragment3.RefreshListView;
import cn.edu.hebtu.software.snowcarsh2.bean.DataRead;

public class ChildrenFragment3 extends Fragment {
    public static ChildrenFragment3 newInstance() {
        ChildrenFragment3 fragment = new ChildrenFragment3();
        return fragment;

    }
    @Nullable
    private RefreshListView listt;
    private View view;
    CustomerAdapterRead customAdapterRead;
    List<DataRead> dataReads;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                //加载完数据之后，要更新适配器和隐藏尾布局
                customAdapterRead.notifyDataSetChanged();
                listt.completeLoad();
            } else if (msg.what == 1) {
                //刷新完数据之后，要更新适配器和隐藏头布局
                customAdapterRead.notifyDataSetChanged();
                listt.completeRefresh();
            }
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initView(inflater,container);
        initData();
        update();



//        // 查询获得游标
//        Cursor cursor = db.query ("COMPANY",null,null,null,null,null,null);
//        // 判断游标是否为空
//        if(cursor.moveToFirst()) {
//            // 遍历游标
//            do {
//                int id = cursor.getInt(cursor.getColumnIndex("ID"));
//                String name=cursor.getString(cursor.getColumnIndex("NAME"));
//                int age=cursor.getInt(cursor.getColumnIndex("AGE"));
//                String address = cursor.getString(cursor.getColumnIndex("ADDRESS"));
//                String salary = cursor.getString(cursor.getColumnIndex("SALARY"));
//                Log.e("mydb", id + "|"+name+"|"+age+"|"
//                        +address+"|"+salary);
//            } while (cursor.moveToNext());
//        }

        listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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


    private void initView(LayoutInflater inflater,ViewGroup container) {
        view = inflater.inflate(R.layout.childrenfragment3,
                container,
                false);
        listt = (RefreshListView) view.findViewById(R.id.listt);
        dataReads = new ArrayList<>();
    }

    private void initData() {
        DataRead dataRead1 = new DataRead("《毒枭：墨西哥》：好戏才刚刚上演（内有剧透）", R.drawable.read1,100,200);
        DataRead dataRead2 = new DataRead("XBox360《穿靴子的猫》：如何让玩家通过Kincet体验奇“喵", R.drawable.read2,22,33);
        DataRead dataRead3 = new DataRead("《毒枭：墨西哥》：好戏才刚刚上演（内有剧透）", R.drawable.read1,30,400);
        DataRead dataRead4 = new DataRead("XBox360《穿靴子的猫》：如何让玩家通过Kincet体验奇“喵", R.drawable.read2,33,200);
        DataRead dataRead5 = new DataRead("《毒枭：墨西哥》：好戏才刚刚上演（内有剧透）", R.drawable.read1,100,333);
        DataRead dataRead6 = new DataRead("XBox360《穿靴子的猫》：如何让玩家通过Kincet体验奇“喵", R.drawable.read2,45,78);
        DataRead dataRead7 = new DataRead("《毒枭：墨西哥》：好戏才刚刚上演（内有剧透）", R.drawable.read1,88,76);
        DataRead dataRead8 = new DataRead("XBox360《穿靴子的猫》：如何让玩家通过Kincet体验奇“喵", R.drawable.read2,45,200);
        DataRead dataRead9 = new DataRead("《毒枭：墨西哥》：好戏才刚刚上演（内有剧透）", R.drawable.read1,44,333);
        DataRead dataRead10 = new DataRead("XBox360《穿靴子的猫》：如何让玩家通过Kincet体验奇“喵", R.drawable.read2,56,122);
        DataRead dataRead11 = new DataRead("《毒枭：墨西哥》：好戏才刚刚上演（内有剧透）", R.drawable.read1,34,111);
        DataRead dataRead12= new DataRead("XBox360《穿靴子的猫》：如何让玩家通过Kincet体验奇“喵", R.drawable.read2,34,256);
        DataRead dataRead13= new DataRead("《毒枭：墨西哥》：好戏才刚刚上演（内有剧透）", R.drawable.read1,45,211);
        DataRead dataRead14= new DataRead("XBox360《穿靴子的猫》：如何让玩家通过Kincet体验奇“喵", R.drawable.read2,339,40);
        DataRead dataRead15= new DataRead("《毒枭：墨西哥》：好戏才刚刚上演（内有剧透）", R.drawable.read1,56,12);
        DataRead dataRead16 = new DataRead("XBox360《穿靴子的猫》：如何让玩家通过Kincet体验奇“喵", R.drawable.read2,22,56);
        DataRead dataRead17= new DataRead("《毒枭：墨西哥》：好戏才刚刚上演（内有剧透）", R.drawable.read1,68,122);
        DataRead dataRead18= new DataRead("XBox360《穿靴子的猫》：如何让玩家通过Kincet体验奇“喵", R.drawable.read2,46,78);

        dataReads.add(dataRead1);
        dataReads.add(dataRead2);
        dataReads.add(dataRead3);
        dataReads.add(dataRead4);
        dataReads.add(dataRead5);
        dataReads.add(dataRead6);
        dataReads.add(dataRead7);
        dataReads.add(dataRead8);
        dataReads.add(dataRead9);
        dataReads.add(dataRead10);
        dataReads.add(dataRead11);
        dataReads.add(dataRead12);
        dataReads.add(dataRead13);
        dataReads.add(dataRead14);
        dataReads.add(dataRead15);
        dataReads.add(dataRead16);
        dataReads.add(dataRead17);
        dataReads.add(dataRead18);
        customAdapterRead = new CustomerAdapterRead(getContext(), R.layout.childrenfragment3_item,dataReads);
        listt.setAdapter(customAdapterRead);

    }

    private void update() {
        //刷新数据操作
        listt.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //列表清空，从数据库中取出一些数据，实现刷新操作
                DataRead dataRead1 = new DataRead("《毒枭：墨西哥》：好戏才刚刚上演（内有剧透）", R.drawable.read1,100,200);
                dataReads.clear();
                dataReads.add(dataRead1);
                dataReads.add(dataRead1);
                dataReads.add(dataRead1);
                dataReads.add(dataRead1);
                dataReads.add(dataRead1);
                dataReads.add(dataRead1);
                dataReads.add(dataRead1);
                dataReads.add(dataRead1);
                handler.sendEmptyMessageDelayed(1, 2000);
            }
        });
        //加载数据操作
        listt.setOnLoadListener(new RefreshListView.OnLoadListener() {
            @Override
            public void onLoad() {
                //列表不清空，从数据库中取一些数据，实现添加操作
                DataRead dataRead1 = new DataRead("XBox360《穿靴子的猫》：如何让玩家通过Kincet体验奇“喵", R.drawable.read2,22,33);
                dataReads.add(dataRead1);
                dataReads.add(dataRead1);
                dataReads.add(dataRead1);
                dataReads.add(dataRead1);
                dataReads.add(dataRead1);
                dataReads.add(dataRead1);
                dataReads.add(dataRead1);
                dataReads.add(dataRead1);
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        });
    }


}
