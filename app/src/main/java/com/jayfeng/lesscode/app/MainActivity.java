package com.jayfeng.lesscode.app;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jayfeng.lesscode.core.$;
import com.jayfeng.lesscode.core.ActivityLess;
import com.jayfeng.lesscode.core.AdapterLess;
import com.jayfeng.lesscode.core.AppLess;
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


        $.getInstance()
                .context(getApplicationContext())
                .log(BuildConfig.DEBUG, "LESSCODE")
                .update(null, 4)
                .http(5000, 5000)
                .build();

        LogLess.$d("xxxxxxx");
        LogLess.$e("yyyyy");
        LogLess.$e("zzzzzzzzzz");
        LogLess.$d("network:" + NetworkLess.$online() + ", type:" + NetworkLess.$type());

        /*
        SharedPreferenceLess.$put(this, "key1", "value");
        SharedPreferenceLess.$put(this, "key2", 22);
        ToastLess.$(MainActivity.this,
                "key1:" + SharedPreferenceLess.$get(MainActivity.this, "key1", "") +
                ", " +
                "key2:" + SharedPreferenceLess.$get(MainActivity.this, "key2", 0));
        */

        LogLess.$e("hello id:" + R.id.hello + "/" + ResourceLess.$id(this, "hello", ResourceLess.TYPE.ID));

        String updateJson = "{\n" +
                "      \"vercode\":112,\n" +
                "      \"vername\":\"V1.1\",\n" +
                "      \"download\":\"http://jayfeng-files.stor.sinaapp.com/JayFengShare.apk\",\n" +
                "      \"log\":\"upgrade content\"\n" +
                "      }";
        UpdateLess.$check(this, updateJson);

        ToastLess.$(this, "state:" + StorageLess.Sdcard.$ready());

        LogLess.$d("sdcard total:" + StorageLess.Sdcard.$total() + ", used:" + StorageLess.Sdcard.$used() + ", free:" + StorageLess.Sdcard.$free());
        LogLess.$d("phone total:" + StorageLess.Phone.$total() + ", used:" + StorageLess.Phone.$used() + ", free:" + StorageLess.Phone.$free());

        LogLess.$d("sdcard string total:" + StorageLess.Sdcard.$totalString(this)
                + ", used:" + StorageLess.Sdcard.$usedString(this)
                + ", free:" + StorageLess.Sdcard.$freeString(this));

        if (StorageLess.ExtSdcard.$path() != null) {
            LogLess.$d("extSdcard string total:" + Formatter.formatFileSize(this, StorageLess.ExtSdcard.$total())
                    + ", used:" + Formatter.formatFileSize(this, StorageLess.ExtSdcard.$used())
                    + ", free:" + Formatter.formatFileSize(this, StorageLess.ExtSdcard.$free()));
        } else {
            LogLess.$d("no extSdcard.");
        }

    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new Person("jay"));
        list.add(new Person("bee"));
    }

    @Override
    public void onBackPressed() {
        if (ActivityLess.$exitTwice()) {
            super.onBackPressed();
        } else {
            ToastLess.$(this, "再按一次退出程序");
        }
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
