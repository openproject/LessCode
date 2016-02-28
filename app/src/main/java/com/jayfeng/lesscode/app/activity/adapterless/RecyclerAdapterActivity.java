package com.jayfeng.lesscode.app.activity.adapterless;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.app.model.Person;
import com.jayfeng.lesscode.core.AdapterLess;
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

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST, getResources().getDrawable(R.drawable.list_divider));
        dividerItemDecoration.setWidth(DisplayLess.$dp2px(16) + 1);

        initData();

        adapter = AdapterLess.$recycler(this, list,
                R.layout.activity_main_list_item,
                new AdapterLess.RecyclerCallBack<Person>() {

                    @Override
                    public void onBindViewHolder(int position, AdapterLess.RecyclerViewHolder recyclerViewHolder, Person person) {
                        TextView nameView = recyclerViewHolder.$view(R.id.name);
                        nameView.setText(person.getName());
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
    }

}
