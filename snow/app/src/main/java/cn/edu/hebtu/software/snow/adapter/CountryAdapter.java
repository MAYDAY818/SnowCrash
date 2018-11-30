package cn.edu.hebtu.software.snow.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.security.PublicKey;
import java.util.List;

import cn.edu.hebtu.software.snow.R;
import cn.edu.hebtu.software.snow.bean.Country;
import cn.smssdk.gui.DefaultContactViewItem;

public class CountryAdapter extends ArrayAdapter<Country> {

    private int resourceId;

    public CountryAdapter(Context context,int textViewResourcdId,List<Country> objects){
        super(context,textViewResourcdId,objects);
        resourceId = textViewResourcdId;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        Country country = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.conutryName = (TextView)view.findViewById(R.id.contry_name);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.conutryName.setText(country.getCountryName());
        return view;
    }
    class ViewHolder{
        TextView conutryName;
    }
}