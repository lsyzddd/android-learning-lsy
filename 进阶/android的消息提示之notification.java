/*
 * android的消息提示notification
 **/

public class NotificationActivity extends Activity {
    private final static int NOTIFICATION_ID = 1004;  //创建消息ID
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_notification);
        Button button_notification = (Button) findViewById(R.id.notification_button);
        button_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	//创建消息的builder对象
                NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this);
                //创建消息小图标
                builder.setSmallIcon(R.mipmap.ic_launcher);
                //创建消息标题
                builder.setContentTitle("新消息来了");
                //创建消息内容
                builder.setContentText("这是新消息的内容");
                //notification对象的创建
                Notification notification = builder.build();
                //创建消息管理对象
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                //使用消息管理对象创建系统栏消息
                manager.notify(NOTIFICATION_ID,notification);
            }
        });
    }
}