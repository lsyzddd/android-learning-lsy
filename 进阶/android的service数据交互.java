/*
 * service利用回调进行数据交互
 **/

public class ServiceChat extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private Intent intent;
    private TextView textView;
    private EditText editText;
    private ServiceCommunicaton.Binder binder;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_servicechat);

        intent = new Intent(ServiceChat.this,ServiceCommunicaton.class);
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        Button btnStartService = (Button) findViewById(R.id.btnStartService);
        Button btnStopService = (Button) findViewById(R.id.btnStopService);
        Button btnBindService = (Button) findViewById(R.id.btnBindService);
        Button btnUnBindService = (Button) findViewById(R.id.btnUnbindService);
        Button btnSyncData = (Button) findViewById(R.id.btnSyncData);
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnBindService.setOnClickListener(this);
        btnSyncData.setOnClickListener(this);
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
                //绑定服务 三个参数: intent,this,常量(BIND_AUTO_CREATE)
                bindService(new Intent(this,ServiceCommunicaton.class),this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                unbindService(this);
                break;
            case R.id.btnSyncData:
                if(binder!=null) {
                    binder.setData(editText.getText().toString());
                }
                break;
        }
    }
    //service服务连接上后执行的函数
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        binder = (ServiceCommunicaton.Binder) iBinder;  //创建binder对象
        binder.getService().setCallback(new ServiceCommunicaton.Callback() {  //给service对象设置回调函数
            @Override
            public void onDataChange(String data) {  //重写CallBack接口中的onDataChange方法
                Message msg = new Message();  //创建消息对象
                Bundle b = new Bundle();  //创建bundle对象
                b.putString("data",data);  //给bundle对象添加变量
                msg.setData(b);   //给msg对象设置数据
                handler.sendMessage(msg);  //给handler发送消息
            }
        });
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {  //创建handler对象
        @Override
        public void handleMessage(Message msg) {  //重写handleMessage方法
            super.handleMessage(msg);
            textView.setText(msg.getData().getString("data"));  //将接收到的msg对象的数据赋值给文本标签
        }
    };
}


public class ServiceCommunicaton extends Service {

    private boolean running = false;
    private String data = "这是默认信息";

    public ServiceCommunicaton() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    public class Binder extends android.os.Binder {  //创建Binder对象
        public void setData(String data) {  //创建获取service对象中的数据方法
            ServiceCommunicaton.this.data = data;
        }
        public ServiceCommunicaton getService() {  //创建返回当前service类的对象
            return ServiceCommunicaton.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        running = true;
        new Thread() {
            public void run() {
                super.run();
                int i = 0;
                while(running) {
                    i++;
                    String str = i+":"+data;
                    Log.d("myLog",str);
                    if(callback!=null) {
                        callback.onDataChange(str);
                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void onDestroy() {
        super.onDestroy();
        running = false;
    }

    private Callback callback = null;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public Callback getCallback() {
        return callback;
    }

    public static interface Callback {  //创建callback接口
        void onDataChange(String data);  //声明一个onDataChange函数
    }

}
