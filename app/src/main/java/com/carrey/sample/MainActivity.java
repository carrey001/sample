package com.carrey.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.carrey.sample.bean.HomeItem;
import com.carrey.sample.view.photo.PhotoCategoryActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<HomeItem> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);
        initListData();
        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, data.get(position).clazz));
            }
        });
    }

    private void initListData() {
        data.add(new HomeItem("图库相册", PhotoCategoryActivity.class));
        data.add(new HomeItem("控件特效", ViewDuangActivity.class));
//        data.add(new HomeItem("二维码", CaptureActivity.class));
//        data.add(new HomeItem("尺子", RulerAct.class));
//        data.add(new HomeItem("http", HttpTestActivity.class));
//        data.add(new HomeItem("图片选择", PicPick.class));
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }


        @Override
        public Object getItem(int position) {
            return data.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_main, null);
            }
            ((TextView) convertView).setText(data.get(position).name);
            return convertView;
        }
    }
}
