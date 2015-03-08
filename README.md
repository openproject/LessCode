LessCode - Less Code For Android
================================
Less code, more efficient for android

> Author weibo：<a href="http://weibo.com/xiaofengjian" target="_blank">冯建V</a>&nbsp;&nbsp;&nbsp;&nbsp;mail：673592063@qq.com&nbsp;&nbsp;&nbsp;&nbsp;QQ：673592063

Main freatures
------
* support many more simpler method than android
* easy integrate the app upgrade feature
* very small (less than 100k)
* is open source

Gradle
------
core version：
```groovy
compile 'com.jayfeng.lesscode:lesscode-core:0.1.1'
```

fully version：
```groovy
compile 'com.jayfeng.lesscode:lesscode-full:0.1.1'
```

Usage
-------
####Config
```java
$.getInstance()
 .context(getApplicationContext())
 .log(BuildConfig.DEBUG, "LESSCODE")
 .build();
```

####Android VS LessCode

* ViewLess
```java
// 强制转化View类型
// Before
ListView listView = (ListView) findViewById(R.id.list);
// After
ListView listView = ViewLess.$(this, R.id.list);
```

* ActivityLess
```java
// 无标题全屏
// Before
requestWindowFeature(Window.FEATURE_NO_TITLE);
getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
// After
ActivityLess.$noTitle(this);
ActivityLess.$fullScreen(this);
```

See more details on the [WIKI](https://github.com/openproject/LessCode/wiki)
