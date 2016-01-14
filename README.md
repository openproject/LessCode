![Logo](http://www.atool.org/placeholder.png?size=250x100&text=LessCode&bg=9bce3b&fg=fff)

LessCode - less code, more efficient for androi
================================
[![Jcenter Status](https://api.bintray.com/packages/openproject/maven/lesscode/images/download.svg)](https://bintray.com/openproject/maven/lesscode)

> Author weibo：<a href="http://weibo.com/xiaofengjian" target="_blank">冯建V</a>&nbsp;&nbsp;&nbsp;&nbsp;mail：673592063@qq.com&nbsp;&nbsp;&nbsp;&nbsp;QQ：673592063

Features
------

> * support more effective methods than android
> * high performence
> * easy integration
> * small size (less than 50k, only 45k+ by proguard)
> * open source

Gradle
------
```groovy
compile('com.jayfeng:lesscode-core:0.7.8') {
    // exclusion for update the android support jar
    // for example, you can use the appcompat-v7 in your project
    exclude group: 'com.android.support', module: 'support-v4'
    exclude group: 'com.android.support', module: 'appcompat-v7'
}
```

Usage
-------
####Config
* Required
```java
$.getInstance()
 .context(getApplicationContext())
 .build();
```

* Optional
```java
$.getInstance()
 .context(getApplicationContext())
 .log(BuildConfig.DEBUG, "LESSCODE") // LogLess - debug, tag
 .update(null, 5) // UpdateLess - null means the default value, 5 is the notification frequent, default is 5
 .http(5000, 5000) // HttpLess - default connect and read timeout
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

See more details on the [Wiki](https://github.com/openproject/LessCode/wiki)

License
-------
```
Copyright (C)  LessCode Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
