package com.jayfeng.lesscode.app.activity.adapterless;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.app.model.Person;
import com.jayfeng.lesscode.core.AdapterLess;
import com.jayfeng.lesscode.core.ViewLess;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapterActivity extends Activity {

    List<Person> list;
    ViewPager viewPager;
    PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_pager);
        viewPager = ViewLess.$(this, R.id.viewpager);
        initData();

        adapter = AdapterLess.$pager(this, list,
                R.layout.activity_main_list_item,
                new AdapterLess.PageCallBack<Person>() {
                    @Override
                    public void instantiateItem(int position, View view, Person person) {
                        TextView nameView = ViewLess.$(view, R.id.name);
                        nameView.setText("page " + position + ":" + person.getName());
                    }
                });

        viewPager.setAdapter(adapter);
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new Person("header"));
        list.add(new Person("jay"));
        list.add(new Person("bee"));
        list.add(new Person("header"));
        list.add(new Person("chras"));
        list.add(new Person("nichid"));
    }

}
