package com.jayfeng.lesscode.app.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class AdapterActivity extends ActionBarActivity {

    List<Person> list;
    ListView listView;
    BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        listView = ViewLess.$(this, R.id.listview);
        initData();

        adapter = AdapterLess.$base(this, list, R.layout.activity_main_list_item,
                new AdapterLess.CallBack<Person>() {
                    @Override
                    public View getView(int position, View convertView, AdapterLess.ViewHolder holder, Person person) {
                        TextView nameView = holder.$view(convertView, R.id.name);
                        nameView.setText(person.getName());
                        return convertView;
                    }
                });
        listView.setAdapter(adapter);
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new Person("jay"));
        list.add(new Person("bee"));
    }

}
