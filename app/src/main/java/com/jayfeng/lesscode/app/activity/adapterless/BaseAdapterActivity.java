package com.jayfeng.lesscode.app.activity.adapterless;

import android.app.Activity;
import android.os.Bundle;
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

public class BaseAdapterActivity extends Activity
{

    List<Person> list;
    ListView listView;
    BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_base);
        listView = ViewLess.$(this, R.id.listview);
        initData();

        // simple callback
        /*
        adapter = AdapterLess.$base(this, list, R.layout.activity_main_list_item,
                new AdapterLess.CallBack<Person>() {
                    @Override
                    public View getView(int position, View convertView, AdapterLess.ViewHolder holder, Person person) {
                        TextView nameView = holder.$view(convertView, R.id.name);
                        nameView.setText(person.getName());
                        return convertView;
                    }
                });
        */
        adapter = AdapterLess.$base(this, list,
                new int[]{R.layout.activity_main_list_item, R.layout.adapter_list_item_header},
                new AdapterLess.FullCallBack<Person>()
                {
                    @Override
                    public View getView(int position, View convertView, AdapterLess.ViewHolder holder, Person person)
                    {
                        TextView nameView = holder.$view(convertView, R.id.name);
                        nameView.setText(person.getName());
                        return convertView;
                    }

                    @Override
                    public int getItemViewType(int position)
                    {
                        Person person = list.get(position);
                        if ("header".equals(person.getName()))
                        {
                            return 1;
                        } else
                        {
                            return 0;
                        }
                    }

                    @Override
                    public boolean isEnabled(int position)
                    {
                        Person person = list.get(position);
                        if ("header".equals(person.getName()))
                        {
                            return false;
                        }
                        return true;
                    }
                });
        // full callback

        listView.setAdapter(adapter);
    }

    private void initData()
    {
        list = new ArrayList<>();
        list.add(new Person("header"));
        list.add(new Person("jay"));
        list.add(new Person("bee"));
        list.add(new Person("header"));
        list.add(new Person("chras"));
        list.add(new Person("nichid"));
        list.add(new Person("header"));
        list.add(new Person("chras"));
        list.add(new Person("nichid"));
        list.add(new Person("header"));
        list.add(new Person("chras"));
        list.add(new Person("nichid"));
        list.add(new Person("header"));
        list.add(new Person("chras"));
        list.add(new Person("nichid"));
        list.add(new Person("header"));
        list.add(new Person("chras"));
        list.add(new Person("nichid"));
        list.add(new Person("header"));
        list.add(new Person("chras"));
        list.add(new Person("nichid"));


    }

}
