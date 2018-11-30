package cn.edu.hebtu.software.snow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.snow.adapter.CountryAdapter;
import cn.edu.hebtu.software.snow.bean.Country;

//显示adapter
public class ChoseActivity extends AppCompatActivity {

    private List<Country> countryList = new ArrayList<>();
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose);
        initCountry();
        CountryAdapter countryAdapter = new CountryAdapter(ChoseActivity.this,R.layout.conutry_item,countryList);
        ListView listView = (ListView)findViewById(R.id.list_country);
        listView.setAdapter(countryAdapter);
        //设置点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country country = countryList.get(position);
                Intent intent = new Intent(ChoseActivity.this, RegisterActivity.class);
                intent.putExtra("countryInformation", country.getCountryName()+" +"+country.getCountryId());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void initCountry(){
        countryList.add(new Country("国家1",11));
        countryList.add(new Country("国家2",21));
        countryList.add(new Country("国家3",32));
        countryList.add(new Country("国家4",42));
        countryList.add(new Country("国家5",53));
        countryList.add(new Country("国家6",64));
        countryList.add(new Country("国家7",75));
        countryList.add(new Country("国家8",86));
        countryList.add(new Country("国家9",66));
        countryList.add(new Country("国家10",10));
        countryList.add(new Country("国家11",11));
    }
}
