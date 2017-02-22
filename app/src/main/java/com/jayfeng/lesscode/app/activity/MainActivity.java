package com.jayfeng.lesscode.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.app.model.LessItem;
import com.jayfeng.lesscode.core.ActivityLess;
import com.jayfeng.lesscode.core.HttpLess;
import com.jayfeng.lesscode.core.LogLess;
import com.jayfeng.lesscode.core.ToastLess;
import com.jayfeng.lesscode.core.ViewLess;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    List<LessItem> list;

    ListView listView;

    BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        listView = ViewLess.$(this, R.id.listview);

        initData();

        final File mDestDir = new File(Environment.getExternalStorageDirectory().getPath() + "/lesscode-download");
        if (mDestDir.exists() && !mDestDir.isDirectory()) {
            mDestDir.delete();
        }
        if (mDestDir.exists() || mDestDir.mkdirs()) {

        }
        final File mDestFile = new File(mDestDir.getPath() + "/" + URLEncoder.encode("http://www.vpngo.com/download/vpngo-release-v1.3.1-46.apk"));
        new Thread() {
            @Override
            public void run() {
                try {
                    HttpLess.$download("http://www.vpngo.com/download/vpngo-release-v1.3.1-46.apk", mDestFile, false, new HttpLess.DownloadCallBack() {
                        @Override
                        public void onDownloading(int progress) {

                        }

                        @Override
                        public void onDownloaded() {
                            LogLess.$d("下载完成");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    LogLess.$d(e.getMessage());
                }
            }
        }.start();


//        adapter = AdapterLess.$base(this, list, R.layout.activity_main_list_item,
//                new AdapterLess.CallBack<LessItem>() {
//                    @Override
//                    public View getView(int position, View convertView, AdapterLess.ViewHolder holder, LessItem lessItem) {
//                        TextView nameView = holder.$view(convertView, R.id.name);
//                        nameView.setText(lessItem.getName());
//                        return convertView;
//                    }
//                });
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent();
//                intent.setClassName(MainActivity.this, "com.jayfeng.lesscode.app.activity." + list.get(position).getClassName());
//                startActivity(intent);
//            }
//        });
//
//        LogLess.$d("network: %s, type: %s", NetworkLess.$online(), NetworkLess.$type());
//
//        Person person = new Person("fengj");
//        SerializeLess.$se(new File(getCacheDir(), "person").getAbsolutePath(), person);
//
//        Person person1 = SerializeLess.$de(new File(getCacheDir(), "person").getAbsolutePath());
//        ToastLess.$(this, person1.getName());

    }

    private void initData() {
        list = new ArrayList<>();

        LessItem lessItem = new LessItem();
        lessItem.setName("ActivityLess的使用");
        lessItem.setClassName("ActivityActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("AppLess的使用");
        lessItem.setClassName("AppActivity");
        list.add(lessItem);

        lessItem = new LessItem();
        lessItem.setName("BitmapLess的使用");
        lessItem.setClassName("BitmapActivity");
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
        lessItem.setName("DrawableLess的使用");
        lessItem.setClassName("DrawableActivity");
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
        lessItem.setName("LogLess的使用");
        lessItem.setClassName("LogActivity");
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
