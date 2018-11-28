package cn.edu.hebtu.software.snow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.snow.adapter.CustomerAdapter;
import cn.edu.hebtu.software.snow.bean.CountryNo;

public class ChoseActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose);
        findview();
        show();
        imageView.setOnClickListener(new setonclick());
    }
    private void findview(){
        imageView = findViewById(R.id.iv);
    }
    private  class setonclick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv:
                  finish();
            }
        }
    }
    private void show(){
        ListView listView = findViewById(R.id.list);
        final List<CountryNo> list = new ArrayList<>();
        list.add(new CountryNo("国家1","编号1"));
        list.add(new CountryNo("国家2","编号2"));
        list.add(new CountryNo("国家3","编号3"));
        list.add(new CountryNo("国家4","编号4"));
        list.add(new CountryNo("国家5","编号5"));
        list.add(new CountryNo("国家6","编号6"));
        list.add(new CountryNo("国家7","编号7"));
        list.add(new CountryNo("国家8","编号8"));
        list.add(new CountryNo("国家9","编号9"));
        list.add(new CountryNo("国家10","编号10"));
        list.add(new CountryNo("国家11","编号11"));
        listView.setAdapter(new CustomerAdapter(this,list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(ChoseActivity.this,MainActivity.class);
                intent.putExtra("list",list.get(position).getCountry());
                setResult(200,intent);
                finish();
            }
        });
    }
}
