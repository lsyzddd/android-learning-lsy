/*
 * 使用AIDL从一个应用启动另一个应用
 **/

// 第一个应用，被启动的应用

//AppService.java  这是一个service文件

public class AppService extends Service {

    private String data = "默认数据";
    private Boolean running = false;

    public AppService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IAppServiceRemoteInterface.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }
            //rebuild后根据aidl文件中声明的setData函数创建的函数
            public void setData(String data) throws RemoteException {
                AppService.this.data = data;
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("myLog","service创建");
        new Thread(){
            @Override
            public void run() {
                super.run();
                running = true;
                while(running) {
                    Log.d("myLog",data);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        running = false;
        Log.d("myLog","service销毁");
    }

}

//IAppServiceRemoteInterface.aidl  这是一个aidl文件

package com.example.myandroid01;

// Declare any non-default types here with import statements

interface IAppServiceRemoteInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void setData(String data);  //添加的文件

}

// 第二个应用  启动另一个应用的应用

// 在该应用中创建一个aidl的folder，然后在该folder中创建一个和上一个应用一样的包名，
// 在该包名中创建一个和上一个应用一样的AIDL文件

// IAppServiceRemoteInterface.aidl
package com.example.myandroid01;

// Declare any non-default types here with import statements

interface IAppServiceRemoteInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void setData(String data); //此处函数声明，需要在service中定义

}

// 该应用的布局文件

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <Button
        android:id="@+id/btnStartService"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        android:layout_marginStart="10dp"
        android:text="开启外部服务"/>

    <Button
        android:id="@+id/btnStopService"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/btnStartService"
        android:layout_marginTop="10dp"
        android:layout_marginStart="10dp"
        android:text="停止外部服务"/>

    <Button
        android:id="@+id/btnBindService"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/btnStopService"
        android:layout_marginTop="10dp"
        android:layout_marginStart="10dp"
        android:text="绑定外部服务"/>

    <Button
        android:id="@+id/btnUnBindService"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/btnBindService"
        android:layout_marginTop="10dp"
        android:layout_marginStart="10dp"
        android:text="解除绑定外部服务"/>

    <Button
        android:id="@+id/btnSyncServiceData"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/btnUnBindService"
        android:layout_marginStart="10dp"
        android:layout_marginTop="10dp"
        android:text="同步数据"/>

    <EditText
        android:id="@+id/editText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/btnSyncServiceData"
        android:layout_marginTop="10dp"
        android:layout_marginStart="10dp"
        android:text="这是另一个应用程序"/>

</RelativeLayout>

// MainActivity.java

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private Intent intent;
    private EditText editText;
    private IAppServiceRemoteInterface binder = null;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent();
        //此处的ComponentName中的参数分别是要被吊起应用的包名和service名
        intent.setComponent(new ComponentName("com.example.myandroid01","com.example.myandroid01.AppService"));
        editText = (EditText) findViewById(R.id.editText);
        Button btnStartService = (Button) findViewById(R.id.btnStartService);
        Button btnStopService = (Button) findViewById(R.id.btnStopService);
        Button btnBindService = (Button) findViewById(R.id.btnBindService);
        Button btnUnBindService = (Button) findViewById(R.id.btnUnBindService);
        Button btnSyncServiceData = (Button) findViewById(R.id.btnSyncServiceData);
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnBindService.setOnClickListener(this);
        btnSyncServiceData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnStartService:
                startService(intent);
                break;
            case R.id.btnStopService:
                stopService(intent);
                break;
            case R.id.btnBindService:
                bindService(intent,this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnBindService:
                unbindService(this);
                binder = null;
                break;
            case R.id.btnSyncServiceData:
                if(binder!=null) {
                    Log.d("myLog","被点击了");
                    try {
                        binder.setData(editText.getText().toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Log.d("myLog","binder的值为null");
                }
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.d("myLog","service已经连接");
        binder = IAppServiceRemoteInterface.Stub.asInterface(iBinder);
        Log.d("myLog",binder.toString());
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
}