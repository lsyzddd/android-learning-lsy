/*
 * application的应用
 **/

//application的使用之组件之间数据的互相传递

//以下的代码会产生两个相同的app图标

//第一个activity中的内容

<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.myandroid01">

    <application
        android:name=".App"
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
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>

final TextView textView = (TextView) findViewById(R.id.textView);
final EditText editText = (EditText) findViewById(R.id.editText);
Button button = (Button) findViewById(R.id.button);
String text = getAppContext().getTextData();
textView.setText(text);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String inputEditText = editText.getText().toString();
        getAppContext().setTextData(inputEditText);
        textView.setText(getAppContext().getTextData());
    }
});

public App getAppContext() {
    return ((App) getApplicationContext());
}

//第二个activity中的内容

final TextView textView = (TextView) findViewById(R.id.textView);
final EditText editText = (EditText) findViewById(R.id.editText);
Button button = (Button) findViewById(R.id.button);
String text = getAppContext().getTextData();
textView.setText(text);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String inputEditText = editText.getText().toString();
        getAppContext().setTextData(inputEditText);
        textView.setText(getAppContext().getTextData());
    }
});

public App getAppContext() {
    return ((App) getApplicationContext());
}