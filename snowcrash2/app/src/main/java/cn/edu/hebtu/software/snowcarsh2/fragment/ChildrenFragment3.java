package cn.edu.hebtu.software.snowcarsh2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.snowcarsh2.R;

import cn.edu.hebtu.software.snowcarsh2.activity.childrenfragment5.ChildrenFragment5Activity;
import cn.edu.hebtu.software.snowcarsh2.adapter.childrenfragment3.CustomerAdapterRead;
import cn.edu.hebtu.software.snowcarsh2.adapter.childrenfragment3.RefreshListView;
import cn.edu.hebtu.software.snowcarsh2.bean.DataRead;

public class ChildrenFragment3 extends Fragment {
    public static ChildrenFragment3 newInstance() {
        ChildrenFragment3 fragment = new ChildrenFragment3();
        return fragment;

    }
    @Nullable
    int fromIndex=0;
    int j=2,k=900;
    private RefreshListView listt;
    private View view;
    CustomerAdapterRead customAdapterRead;
    List<DataRead> dataReads;
    //之后更换文章
    private  List<DataRead> List=new ArrayList<>();
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
        thread1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initData();
        update();

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
        DataRead dataRead1;
        for (int i = 0;i<20;i++){
            dataRead1 = new DataRead(List.get(i).getTitle().toString(), List.get(i).getPic(),List.get(i).getLove(),List.get(i).getSay());
            dataReads.add(dataRead1);
        }
        customAdapterRead = new CustomerAdapterRead(getContext(), R.layout.childrenfragment3_item,dataReads);
        listt.setAdapter(customAdapterRead);

    }

    private void update() {
        //刷新数据操作
        listt.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //列表清空，从数据库中取出一些数据，实现刷新操作
                dataReads.clear();
                DataRead dataRead1;
                for (int i = j;i<j+20;i++){
                    dataRead1 = new DataRead(List.get(i).getTitle().toString(), List.get(i).getPic(),List.get(i).getLove(),List.get(i).getSay());
                    dataReads.add(dataRead1);
                }
                j=j+2;
                if(j>900){j=0;}
                handler.sendEmptyMessageDelayed(1, 2000);
             }
        });
        //加载数据操作
        listt.setOnLoadListener(new RefreshListView.OnLoadListener() {
            @Override
            public void onLoad() {
                //列表不清空，从数据库中取一些数据，实现添加操作
                DataRead dataRead1;
                for (int i = k;i<k+20;i++){
                    dataRead1 = new DataRead(List.get(i).getTitle().toString(), List.get(i).getPic(),List.get(i).getLove(),List.get(i).getSay());
                    dataReads.add(dataRead1);
                }
                k=k-2;
                if(k<30){k=900;}
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        });
    }
    //获得数据
    public static List<DataRead> getNews(int fromIndex){
        List<DataRead> newsList = new ArrayList<>();
        try {
            StringBuilder r =new StringBuilder();
            r.append("http://120.79.80.250:8080/mysqltest6/a?fromIndex=");
            r.append(fromIndex+"&count=930");

            URL url = new URL(r.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("contentType","UTF-8");
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();

            JSONArray array = new JSONArray(res);


            for (int i = 0; i < array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                DataRead n = new DataRead();
                n.setId(object.getInt("id"));
                n.setTitle(object.getString("title"));
//                n.setIntroduce(object.getString("info"));
                n.setPic(object.getString("img"));
                n.setLove(i);
                n.setSay(i);
//                n.setTime(object.getString("date"));
//                n.setLinkUrl(object.getString("uri"));
                newsList.add(n);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    //线程加载
    Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List=getNews(fromIndex);
                    Thread.sleep(1000);
                    Log.e("!!!!!!!!!!!!",""+fromIndex);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
}
