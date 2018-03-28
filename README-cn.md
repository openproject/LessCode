# LessCode [![Jcenter Status](https://api.bintray.com/packages/openproject/maven/lesscode/images/download.svg)](https://bintray.com/openproject/maven/lesscode) [![Build Status](https://travis-ci.org/openproject/LessCode.svg)](https://travis-ci.org/openproject/LessCode)

打造精简、高效的最好Android工具库！

## Overview

> * 大量比原生更便捷的方法
> * 高性能
> * 易于集成
> * 小体积 (60k+，开启混淆后更小)
> * 开源

## Gradle

```groovy
implementation 'com.jayfeng:lesscode-core:2.4.2'
```

## Features
|源码|备注|
|-------|-------|
|ActivityLess|Activity相关辅助类：去标题，全屏，两次退出提示，优化Overdraw背景等|
|AdapterLess|打造通用的BaseAdapter，PagerAdapter，RecyclerView.Adapter等|
|AlarmLess|定时器相关|
|AppLess|获取应用版本，名称，签名，清理缓存等|
|BitmapLess|Bitmap处理相关|
|CacheLess|缓存网络请求返回的json|
|DeviceLess|获取设备信息，比如mac|
|DisplayLess|屏幕相关，比如dp和px的转换，状态栏或者标题栏的高度，dpi判断|
|DrawableLess|通用的着色方案|
|EncodeLess|md5加密|
|FileLess|文件处理相关|
|HttpLess|简单的Http工具类，如果是专业用途还是用其他更强大的第三方库吧|
|ImageLess|图片相关|
|KeyBoardLess|输入法的弹出或隐藏|
|LogLess|强大的Log库|
|NetworkLess|网络判断|
|ResourceLess|根据资源名获取ID|
|SerializeLess|序列化和反序列化|
|SharedPreferenceLess|简化和增强SharedPreference的操作|
|StorageLess|手机存储相关,包括:手机内存,内置存储卡(Sdcard),外置存储卡(ExtSdcard)|
|ToastLess|简化Toast的使用|
|UpdateLess/UpdateService|简单但完整的自动更新实现，无缝对接(已分离到LessCode-Update项目)|
|ViewLess|简化繁琐的findViewById和强制转换|

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
