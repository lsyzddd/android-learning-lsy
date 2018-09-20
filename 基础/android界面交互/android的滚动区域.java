/*
 * 简单的滚动区域设置
 **/

// AndroidManifest.xml

<manifest
    xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.test.test" >

    <uses-sdk
        android:minSdkVersion="8"
        android:targetSdkVersion="22" />
    <uses-permission android:name="android.permission.VIBRATE" />

    <application
        android:icon="@drawable/ic_launcher_background"
        android:label="@string/app_name"
        android:theme="@style/AppTheme" >

        <activity
            android:name=".scrollerActivity"
            android:label="scrollerActivity" >

            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>

        </activity>

    </application>
</manifest>

// activity_scroller.xml 滚动布局文件

<?xml version="1.0" encoding="utf-8"?>
<ScrollView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/linearLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="文字内容"
            android:textSize="30sp"/>
    </LinearLayout>
</ScrollView>

// scrollerActivity.java

package com.test.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class scrollerActivity extends AppCompatActivity {

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_scroller);

    }

}

/*
 * 滚动区域上拉加载更多数据
 **/

// activity_scroller.xml

<?xml version="1.0" encoding="utf-8"?>
<ScrollView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/scrollView"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/linearLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="textView1"
            android:textSize="30sp"/>
    </LinearLayout>
</ScrollView>

//先添加30条数据到滚动区域中

final LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout); //找到滚动区域的子容器
for(;count < 30; count ++) {  //循环30次，每次创建一个textView，并向滚动区域的子容器中添加textView
    TextView textView = new TextView(scrollerActivity.this);
    textView.setText("textView"+count);
    textView.setTextSize(30);
    layout.addView(textView);
}

ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);  //找到滚动区域
scrollView.setOnTouchListener(new View.OnTouchListener() {  //给滚动区域添加触摸事件
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction()==MotionEvent.ACTION_MOVE) {  //判断滚动区域是否处于滚动状态
            int scrollerY = view.getScrollY();  //滚动区域超出屏幕高度
            int height = view.getHeight();  //整个scrollView显示的高度

            ScrollView v = (ScrollView) view;  //获取到scrollerView的视图
            int linearHeight = v.getChildAt(0).getMeasuredHeight();  //获取到scrollerView的子容器LinearLayout的高度
            //如果(滚动区域超出屏幕高度)+(整个scrollView显示的高度)===(scrollerView的子容器LinearLayout的高度)
            //说明此时已经滑动到底部
            if(scrollerY + height == linearHeight) { //滑动到底部后,在子容器LinearLayout中动态添加30个textView
                Toast.makeText(scrollerActivity.this,"到达底部",Toast.LENGTH_SHORT).show();
                int max = count+30;  //设置添加textView的最大基数增加30
                for(;count<max;count++) {
                    TextView textView = new TextView(scrollerActivity.this);
                    textView.setText("textView"+count);
                    textView.setTextSize(30);
                    layout.addView(textView);
                }
            }
        }
        return false;
    }
});