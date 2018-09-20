/*
 * 页面隐式跳转的方式
 **/

//AndroidManifest.xml

<activity
    android:name=".LearnActivity03"
    android:label="LearnActivity03">
    <intent-filter>
        <category android:name="android.intent.category.DEFAULT" />
        <action android:name="com.example.myandroid01.intent.action.LearnActivity03" />
    </intent-filter>
</activity>

//第一个activity

Intent intent = new Intent(LearnActivity03.ACTION);

//第二个activity

public static final String ACTION = "com.example.myandroid01.intent.action.LearnActivity03";