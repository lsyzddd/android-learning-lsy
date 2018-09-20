/*
 * service服务
 **/

1.开启服务和停止服务

// androidManifest.xml
<service
    android:name=".MyService"
    android:enabled="true"
    android:exported="true" />

// MyService.java
public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {  //service一开启就开始执行
        new Thread() {
            public void run() {
                super.run();
                while (true) {
                    Log.d("myLog","后台服务正在运行...");
                    try {
                        sleep(1000);
                    }
                    catch(Exception e) {
                        Log.d("myLog",e.toString());
                    }
                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }
}

// ServiceActivity.java

Button startServiceBtn = (Button) findViewById(R.id.btnStartService);
Button stopServiceBtn = (Button) findViewById(R.id.btnStopService);
final Intent intent = new Intent(ServiceActivity.this,MyService.class);
startServiceBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startService(intent);
    }
});
stopServiceBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        stopService(intent);
    }
});

2.绑定服务和解除绑定

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private Intent intent;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_service);
        intent = new Intent(ServiceActivity.this,MyService.class);
        Button startServiceBtn = (Button) findViewById(R.id.btnStartService);
        Button stopServiceBtn = (Button) findViewById(R.id.btnStopService);
        Button bindServiceBtn = (Button) findViewById(R.id.btnBindService);
        Button unBindServiceBtn = (Button) findViewById(R.id.btnUnbindService);
        startServiceBtn.setOnClickListener(this);
        stopServiceBtn.setOnClickListener(this);
        bindServiceBtn.setOnClickListener(this);
        unBindServiceBtn.setOnClickListener(this);
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
                bindService(intent,this,BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                unbindService(this);
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.d("myLog","已经绑定service");
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Log.d("myLog","已经取消绑定service");
    }
}

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();   //此处已经重写
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread() {
            public void run() {
                super.run();
                while (true) {
                    Log.d("myLog","后台服务正在运行...");
                    try {
                        sleep(1000);
                    }
                    catch(Exception e) {
                        Log.d("myLog",e.toString());
                    }
                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onCreate() {
        super.onCreate();
        Log.d("myLog","service 创建");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d("myLog","service 销毁");
    }

}

//根据服务是否开启来打印日志

/*
 * startService不仅执行onCreate，而且执行onStartCommand。stopService执行onDestroy
 * bindService只是执行onCreate，unbindService执行onDestroy
 **/

public class MyService extends Service {

    private boolean serviceRunning = false;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("myLog","startCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public void onCreate() {
        super.onCreate();
        serviceRunning = true;
        Log.d("myLog","service 创建");
        new Thread() {
            public void run() {
                super.run();
                while (serviceRunning) {
                    Log.d("myLog","后台服务正在运行...");
                    try {
                        sleep(1000);
                    }
                    catch(Exception e) {
                        Log.d("myLog",e.toString());
                    }
                }
            }
        }.start();
    }

    public void onDestroy() {
        super.onDestroy();
        serviceRunning = false;
        Log.d("myLog","service 销毁");
    }

}

//启动service并传递数据

private Intent intent;
private EditText editText;

public void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    setContentView(R.layout.activity_servicechat);
    intent = new Intent(ServiceChat.this,ChatService.class);
    editText = (EditText) findViewById(R.id.editText);
    Button btnStartService = (Button) findViewById(R.id.btnStartService);
    Button btnStopService = (Button) findViewById(R.id.btnStopService);
    btnStartService.setOnClickListener(this);
    btnStopService.setOnClickListener(this);
}

@Override
public void onClick(View view) {
    switch(view.getId()) {
        case R.id.btnStartService:
            intent.putExtra("data",editText.getText().toString());
            startService(intent);
            break;
        case R.id.btnStopService:
            stopService(intent);
            break;
    }
}


private Boolean serviceRunning = false;
private String dataName = "默认信息";

public ChatService() {
}

@Override
public IBinder onBind(Intent intent) {
    // TODO: Return the communication channel to the service.
    throw new UnsupportedOperationException("Not yet implemented");
}

@Override
public int onStartCommand(final Intent intent, int flags, int startId) {
    dataName = intent.getStringExtra("data");
    return super.onStartCommand(intent, flags, startId);
}

@Override
public void onCreate() {
    super.onCreate();
    serviceRunning = true;
    new Thread(){
        public void run() {
            while(serviceRunning) {
                Log.d("myLog",dataName);
                try {
                    sleep(1000);
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
    serviceRunning = false;
}