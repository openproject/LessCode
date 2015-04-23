package com.jayfeng.lesscode.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Formatter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.app.model.LessItem;
import com.jayfeng.lesscode.app.model.Person;
import com.jayfeng.lesscode.core.ActivityLess;
import com.jayfeng.lesscode.core.AdapterLess;
import com.jayfeng.lesscode.core.LogLess;
import com.jayfeng.lesscode.core.NetworkLess;
import com.jayfeng.lesscode.core.ResourceLess;
import com.jayfeng.lesscode.core.SharedPreferenceLess;
import com.jayfeng.lesscode.core.StorageLess;
import com.jayfeng.lesscode.core.ToastLess;
import com.jayfeng.lesscode.core.UpdateLess;
import com.jayfeng.lesscode.core.ViewLess;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    List<LessItem> list;

    ListView listView;

    BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityLess.$noTitle(this);
        ActivityLess.$fullScreen(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        listView = ViewLess.$(this, R.id.listview);

        initData();

        adapter = AdapterLess.$base(this, list, R.layout.activity_main_list_item,
                new AdapterLess.CallBack<LessItem>() {
                    @Override
                    public View getView(int position, View convertView, AdapterLess.ViewHolder holder, LessItem lessItem) {
                        TextView nameView = holder.$view(convertView, R.id.name);
                        nameView.setText(lessItem.getName());
                        return convertView;
                    }
                });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClassName(MainActivity.this, "com.jayfeng.lesscode.app.activity." + list.get(position).getClassName());
                startActivity(intent);
            }
        });

        LogLess.$d("network:" + NetworkLess.$online() + ", type:" + NetworkLess.$type());

        SharedPreferenceLess.$put(this, "key1", "value");
        SharedPreferenceLess.$put(this, "key2", "100000022");
        ToastLess.$(MainActivity.this,
                "key1:" + SharedPreferenceLess.$get(MainActivity.this, "key1", "") +
                ", " +
                "key2:" + SharedPreferenceLess.$get(MainActivity.this, "key2", ""));

//        LogLess.$e("hello id:" + R.id.hello + "/" + ResourceLess.$id(this, "hello", ResourceLess.TYPE.ID));
    }

    private void initData() {
        list = new ArrayList<>();

        LessItem lessItem = new LessItem();
        lessItem.setName("ActivityLess的使用");
        lessItem.setClassName("ActivityActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("AdapterLess的使用");
        lessItem.setClassName("AdapterActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("AppLess的使用");
        lessItem.setClassName("AppActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("CacheLess的使用");
        lessItem.setClassName("CacheActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("DeviceLess的使用");
        lessItem.setClassName("DeviceActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("DisplayLess的使用");
        lessItem.setClassName("DisplayActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("EncodeLess的使用");
        lessItem.setClassName("EncodeActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("FileLess的使用");
        lessItem.setClassName("FileActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("HttpLess的使用");
        lessItem.setClassName("HttpActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("KeyboradLess的使用");
        lessItem.setClassName("KeyboradActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("NetworkLess的使用");
        lessItem.setClassName("NetworkActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("ResourceLess的使用");
        lessItem.setClassName("ResourceActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("SharedPreferenceLess的使用");
        lessItem.setClassName("SharedPreferenceActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("StorageLess的使用");
        lessItem.setClassName("StorageActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("ToastLess的使用");
        lessItem.setClassName("ToastActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("UpdateLess的使用");
        lessItem.setClassName("AppActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("ViewLess的使用");
        lessItem.setClassName("ViewActivity");
        list.add(lessItem);
    }

    @Override
    public void onBackPressed() {
        if (ActivityLess.$exitTwice()) {
            super.onBackPressed();
        } else {
            ToastLess.$(this, "再按一次退出程序");
        }
    }
}
