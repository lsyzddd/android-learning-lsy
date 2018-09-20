/*
 * 使用intent从一个app程序跳转到另一个app程序
 **/

//注意当A调用B的时候要防止B中出现程序错误

1.第一种方法

// 第一个app程序

<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.myandroid01">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme" >
        <activity
            android:name="com.example.myandroid01.LearnActivity02"  //此处是重点
            android:label="主界面"
            android:launchMode="singleTask">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>

</manifest>

// 第二个app程序
//以下程序用到了上一个程序的几个参数
1.程序包名
2.activity的名称
3.action的名称

Intent intent = new Intent();
ComponentName cn = new ComponentName("com.example.myandroid01","com.example.myandroid01.LearnActivity02");
intent.setComponent(cn);
intent.setAction("android.intent.action.MAIN");
startActivityForResult(intent,RESULT_OK);

2.第二种方法

// 第一个app程序

<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.myandroid01">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme" >
        <activity
            android:name=".LearnActivity02"
            android:label="主界面"
            android:launchMode="singleTask">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>
        <activity
            android:name=".LearnActivity03"
            android:label="LearnActivity03">
            <intent-filter>
                <action android:name="com.example.myandroid01.intent.action.LearnActivity03" />  //此处是重点
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
    </application>

</manifest>

//第二个app程序

startActivity(new Intent("com.example.myandroid01.intent.action.LearnActivity03")); //此处引用了activity的包名

//禁止其他程序进行调用app的activity

android:exported="false"

如果有多个activity中的action名字相同，那么当其他app调用这用一个action名字的时候会出现多个程序调用的选择

例如:

<activity
    android:name=".LearnActivity03"
    android:label="LearnActivity03">
    <intent-filter>
        <action android:name="com.example.myandroid01.intent.action.LearnActivity03" />  //此处action的名字相同
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>
<activity
    android:name=".LearnActivity04"
    android:label="LearnActivity04">
    <intent-filter>
        <action android:name="com.example.myandroid01.intent.action.LearnActivity03" />  //此处action的名字相同
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>

//当activity中的action的值相同时，使用android:scheme的属性来选择需要的activity

<activity
    android:name=".LearnActivity04"
    android:label="LearnActivity04">
    <intent-filter>
        <action android:name="com.example.myandroid01.intent.action.LearnActivity03" />
        <category android:name="android.intent.category.DEFAULT" />
        <data android:scheme="app" />
    </intent-filter>
</activity>

//选择的方式，在url.parse中添加参数，其中的参数值是app://，后面的参数值随便写

startActivity(new Intent("com.example.myandroid01.intent.action.LearnActivity03", Uri.parse("app://hello")));

//使用html中的a标签来唤醒app应用

<a href="app://hello"></a>

//app程序获取a标签传过来的数据

Uri uri = getIntent().getData();
Log.d("myLog",uri);   //输出app://hello