/*
 * 给组件分配权限，然后在另外一个应用使用该权限吊起该应用的页面
 **/

//第一个应用

//权限声明

<permission android:name="com.example.myandroid01.permission.MyAty" />

//给组件限定权限，但在应用的内部是无法限制组件的权限的

<activity
    android:name=".MyAty"
    android:label="另一个界面"
    android:permission="com.example.myandroid01.permission.MyAty">
    <intent-filter>
        <action android:name="com.example.myandroid01.intent.action.MyAty" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>

//第二个应用

//使用第一个应用的权限

<uses-permission android:name="com.example.myandroid01.permission.MyAty" />

//启动第一个应用的页面，这里的action名字是上一个应用的intent-filter中的action的name值

startActivity(new Intent("com.example.myandroid01.intent.action.MyAty"));