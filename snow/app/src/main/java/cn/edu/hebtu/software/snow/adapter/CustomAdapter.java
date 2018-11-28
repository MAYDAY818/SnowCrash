package cn.edu.hebtu.software.snow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.snow.R;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private int itemLayout;
    private List<String> datas = new ArrayList<>();
    public CustomAdapter(){
    }

    public CustomAdapter(Context context, int itemLayout, List<String> datas) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //每加载一条item就调用一次
        if(position==0){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.rel_activity,null);
        }
       else {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(itemLayout, null);
            TextView tvName = convertView.findViewById(R.id.tv_text);
            tvName.setText(datas.get(position-1));
        }

//        Button btnDelete = convertView.findViewById(R.id.btn_delete);
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                datas.remove(position);
//                //更新Adapter
//                notifyDataSetChanged();
//            }
//        });
        return convertView;
    }


}
