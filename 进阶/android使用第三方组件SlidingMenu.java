/*
 * 初识第三方组件SlidingMenu并简单的使用
 **/

//android studio中使用slidingMenu的方式

1.从github上下载地址：https://github.com/jfeinstein10/SlidingMenu
2.android中import module (SlidingMenu目录下的library文件)
3.修改项目下的settings.gradle文件，在里面添加需要的module依赖项，见下面的代码：
4.修改library目录下的build.gradle配置文件，修改方式见下:
5.rebuild project后根据android studio中的提示下载不存在的资源或者修改一些出现的问题
6.修改项目的module依赖，将SlidingMenu的依赖添加到项目中

//注意事项：滑动菜单必须是另一个activity，也就是主页面一个activity，滑动菜单是另一个activity
//引入module的lib中的suportv4必须一致

//settings.gradle
include ':app'
include ':openCVLibrary330', ':SlidingMenu'

//SlidingMenu中build.gradle的配置

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:0.4.+'
    }
}
apply plugin: 'android-library'

dependencies {  //此处有修改
    implementation 'com.android.support:support-v4:13.0.0'
}

android {
    compileSdkVersion 17

    defaultConfig {
        minSdkVersion 7
        targetSdkVersion 16
    }

    sourceSets {
        main {
            java.srcDirs = ['src']
            resources.srcDirs = ['src']
            aidl.srcDirs = ['src']
            renderscript.srcDirs = ['src']
            res.srcDirs = ['res']
            assets.srcDirs = ['assets']

            manifest.srcFile 'AndroidManifest.xml'
        }
    }

}

//activity_slidingmenu.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#ff999999">

    <com.jeremyfeinstein.slidingmenu.lib.SlidingMenu
        android:id="@+id/slidingMenuLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </com.jeremyfeinstein.slidingmenu.lib.SlidingMenu>

</RelativeLayout>

//SlidingMenuActivity.java

package com.example.myandroid03;

import android.app.Activity;
import android.os.Bundle;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class SlidingMenuActivity extends Activity {

    private SlidingMenu slidingMenu;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_mainactivity);
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        //这里要设置滑动菜单背景，否则很难看出滑块，会误以为是bug
        slidingMenu.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset_width);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.activity_slidingmenu);
    }
}

//监听手机模拟器中的菜单键

public boolean onKeyDown(int keyCode, KeyEvent event) {
    switch(keyCode) {
        case KeyEvent.KEYCODE_MENU:
            slidingMenu.toggle(true);
    }
    return super.onKeyDown(keyCode, event);
}