/*
 * ActionBar的使用
 **/

//使得ActionBar变得透明并漂浮在主题内容之上，使得主题内容能在透明的ActionBar底下被看到

//style.xml修改应用中的ActionBar主题风格

//此处自定义了一个CustomActionBarOverlayTheme

<resources>

    <!-- Base application theme. -->
    <style name="AppTheme" parent="android:Theme.Holo.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>

    <style name="CustomActionBarOverlayTheme" parent="@android:style/Theme.Holo">
        <item name="android:windowActionBarOverlay">true</item>
    </style>

</resources>

//AndroidManifest.xml

<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.android06">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/CustomActionBarOverlayTheme">
        <activity android:name=".MainActivity" android:label="@string/app_name">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>


//开启overlay模式，即使是该模式，但是布局文件产生上边距使得主题内容一定在ActionBar之下

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingTop="?android:attr/actionBarSize">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:src="@drawable/background"
        android:contentDescription="@string/background_introduce"
        android:scaleType="fitXY"/>

</RelativeLayout>