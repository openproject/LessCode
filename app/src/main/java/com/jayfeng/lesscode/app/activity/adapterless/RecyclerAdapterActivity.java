package com.jayfeng.lesscode.app.activity.adapterless;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.app.model.Person;
import com.jayfeng.lesscode.core.AdapterLess;
import com.jayfeng.lesscode.core.AdapterViewTypeRecyclerCallBack;
import com.jayfeng.lesscode.core.DisplayLess;
import com.jayfeng.lesscode.core.ViewLess;
import com.jayfeng.lesscode.core.other.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterActivity extends Activity {

    List<Person> list;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter<AdapterLess.RecyclerViewHolder> adapter;
    DividerItemDecoration dividerItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_recycler);
        recyclerView = ViewLess.$(this, R.id.recycler);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
        dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.GRID_LIST, new ColorDrawable(Color.parseColor("#00000000")));
        dividerItemDecoration.setWidth(DisplayLess.$dp2px(4));
        dividerItemDecoration.setHeight(DisplayLess.$dp2px(4));

        initData();

        /*
        adapter = AdapterLess.$recycler(this, list,
                R.layout.activity_main_list_item,
                new AdapterLess.RecyclerCallBack<Person>() {

                    @Override
                    public void onBindViewHolder(int position, AdapterLess.RecyclerViewHolder recyclerViewHolder, Person person) {
                        TextView nameView = recyclerViewHolder.$view(R.id.name);
                        nameView.setText(person.getName());
                    }
                });
        */

        adapter = AdapterLess.$recycler(this, list,
                new int[] { R.layout.activity_main_list_item, R.layout.adapter_list_item_header},
                new AdapterViewTypeRecyclerCallBack<Person>() {
                    @Override
                    public int getItemViewType(int position) {
                        Person person = list.get(position);
                        if ("header".equals(person.getName())) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }

                    @Override
                    public void onViewType0(int position, AdapterLess.RecyclerViewHolder recyclerViewHolder, Person person) {
                        TextView nameView = recyclerViewHolder.$view(R.id.name);
                        nameView.setText(person.getName());
                    }

                    @Override
                    public void onViewType1(int position, AdapterLess.RecyclerViewHolder recyclerViewHolder, Person person) {
                        TextView nameView = recyclerViewHolder.$view(R.id.name);
                        nameView.setText(person.getName() + " >>");
                    }
                });

        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new Person("header"));
        list.add(new Person("jay"));
        list.add(new Person("bee"));
        list.add(new Person("header"));
        list.add(new Person("chras"));
        list.add(new Person("nichid"));
        list.add(new Person("header"));
        list.add(new Person("jay"));
        list.add(new Person("bee"));
        list.add(new Person("header"));
        list.add(new Person("chras"));
        list.add(new Person("nichid"));
        list.add(new Person("header"));
        list.add(new Person("jay"));
        list.add(new Person("bee"));
        list.add(new Person("header"));
        list.add(new Person("chras"));
        list.add(new Person("nichid"));
        list.add(new Person("header"));
        list.add(new Person("jay"));
        list.add(new Person("bee"));
        list.add(new Person("header"));
        list.add(new Person("chras"));
        list.add(new Person("nichid"));
        list.add(new Person("header"));
        list.add(new Person("jay"));
        list.add(new Person("bee"));
        list.add(new Person("header"));
        list.add(new Person("chras"));
        list.add(new Person("nichid"));
        list.add(new Person("header"));
        list.add(new Person("jay"));
        list.add(new Person("bee"));
        list.add(new Person("header"));
        list.add(new Person("chras"));
        list.add(new Person("nichid"));
        list.add(new Person("header"));
        list.add(new Person("gggggg"));
        list.add(new Person("ggxxx"));
        list.add(new Person("gewewewe"));
        list.add(new Person("gtyyyy"));
        list.add(new Person("guugyyyy"));
        list.add(new Person("header"));
        list.add(new Person("jay"));
        list.add(new Person("zzzzzzzzzz"));
        list.add(new Person("header"));
        list.add(new Person("yyyyyyyyyyyyyy"));
        list.add(new Person("etrtertret"));
        list.add(new Person("zsssss"));
    }

}
