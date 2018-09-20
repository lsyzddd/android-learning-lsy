/*
 * 使用receiver接收消息
 **/

//在该应用中创建一个BoradcastCast

//AndroidManifest.xml

<receiver
    android:name=".MyReceiver"
    android:enabled="true"
    android:exported="true" />

//MyReceiver.java

public class MyReceiver extends BroadcastReceiver {

	//定义action常量，用于IntentFilter
    public static final String ACTION = "com.example.myandroid01.intent.action.MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        Log.d("myLog","这是接收到的消息"+msg);
    }
}

//MainActivity.java

private MyReceiver receiver = null;  //创建receiver的空对象

public void onClick(View view) {
    switch(view.getId()) {
        case R.id.btnRegisterReceiver:
            if(receiver == null) {  //判断receiver对象是否存在，不存在的话则创建
                receiver = new MyReceiver();  //创建receiver的新对象
                //注册receiver对象，其中第二个参数数是IntentFilter
                registerReceiver(receiver, new IntentFilter(MyReceiver.ACTION));
            }
            break;
        case R.id.btnUnRegisterReceiver:
            if(receiver != null) {  //判断receiver对象是否存在，存在的话则注册
                unregisterReceiver(receiver);
                receiver = null;
            }
            break;
        case R.id.btnSendMsg:
            Intent intent = new Intent(MyReceiver.ACTION);
            intent.putExtra("msg","这是传过来的数据");
            sendBroadcast(intent);  //发送广播
            break;
    }
}

//android的receiver接收器优先级

//android:priority设置receiver接收器的优先级，它的值越大那么接收器的优先级越高

<receiver
    android:name=".MyReceiver2"
    android:enabled="true"
    android:exported="true">
    <intent-filter android:priority="9">
        <action android:name="com.example.myandroid01.intent.action.MyReceiver" />
    </intent-filter>
</receiver>

<receiver
    android:name=".MyReceiver">
    <intent-filter android:priority="10">
        <action android:name="com.example.myandroid01.intent.action.MyReceiver" />
    </intent-filter>
</receiver>

//abortBroadcast在android6.0不能阻断broadcast的通信，必须使用sendOrderedBroadcast才能阻止