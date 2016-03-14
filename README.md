# LessCode [![Jcenter Status](https://api.bintray.com/packages/openproject/maven/lesscode/images/download.svg)](https://bintray.com/openproject/maven/lesscode)

less code, more efficient for android, for the best android tools library!

## Overview

> * support more effective methods than android
> * high performence
> * easy integration
> * small size (less than 50k, only 45k+ by proguard)
> * open source

## Gradle

```groovy
compile('com.jayfeng:lesscode-core:0.9.0') {
    // exclusion for update the android support jar
    // for example, you can use the appcompat-v7 in your project
    exclude group: 'com.android.support', module: 'support-v4'
    exclude group: 'com.android.support', module: 'appcompat-v7'
}
```

## Usage

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

## Proguard
```
-dontwarn com.jayfeng.lesscode.**
```

See more details on the [Wiki](https://github.com/openproject/LessCode/wiki)

## Alternative libraries
* [xUtils3](https://github.com/wyouflf/xUtils3)
* [LiteCommon](https://github.com/litesuits/android-common)
* [TrineaAndroidCommon](https://github.com/Trinea/android-common)
* [AndroidUtils](https://github.com/jingle1267/android-utils)

## Author

> Author weibo：<a href="http://weibo.com/xiaofengjian" target="_blank">冯建V</a>&nbsp;&nbsp;&nbsp;&nbsp;mail：673592063@qq.com&nbsp;&nbsp;&nbsp;&nbsp;QQ：673592063

## License

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
