package com.jayfeng.lesscode.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jayfeng.lesscode.core.ActivityLess;
import com.jayfeng.lesscode.core.AdapterLess;
import com.jayfeng.lesscode.core.LogLess;
import com.jayfeng.lesscode.core.ToastLess;
import com.jayfeng.lesscode.core.ViewLess;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    List<Person> list;

    TextView textView;
    ListView listView;

    BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityLess.$noTitle(this);
        ActivityLess.$fullScreen(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textView = ViewLess.$(this, R.id.hello);
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

        LogLess.$d("xxxxxxx");
        LogLess.$tag("LESSCODE");
        LogLess.$e("yyyyy");
        LogLess.$release();
        LogLess.$e("zzzzzzzzzz");

        ToastLess.$(this, "Toast less !");
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new Person("jay"));
        list.add(new Person("bee"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
