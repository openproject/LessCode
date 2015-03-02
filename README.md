LessCode - Less Code For Android
================================
简化android开发， 写更少的代码！

> 关于作者 微博：<a href="http://weibo.com/xiaofengjian" target="_blank">冯建V</a>&nbsp;&nbsp;&nbsp;&nbsp;邮箱：673592063@qq.com&nbsp;&nbsp;&nbsp;&nbsp;QQ：673592063

特性
------
* 简化View相关开发
* 简化存储相关开发
* 简化缓存相关开发
* 一键集成检查更新功能
* 更灵活强大的Log开发

Gradle
------
精简版本：
```groovy
compile 'com.jayfeng.lesscode:lesscode-core:0.1.1'
```

完整版本：
```groovy
compile 'com.jayfeng.lesscode:lesscode-core:0.1.1'
compile 'com.jayfeng.lesscode:lesscode-full:0.1.1'
```

使用说明
-------
####配置
```java
$.getInstance()
 .context(getApplicationContext())
 .log(BuildConfig.DEBUG, "LESSCODE")
 .build();
```
####对比
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