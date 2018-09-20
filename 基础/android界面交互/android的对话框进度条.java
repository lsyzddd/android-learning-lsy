/*
 * 简单的对话框进度条
 **/

<TextView
    android:id="@+id/textView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_alignParentTop="true"
    android:layout_centerHorizontal="true"/>

<Button
    android:id="@+id/test1"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="开始下载"
    android:layout_below="@+id/textView"
    android:layout_centerHorizontal="true"
    android:layout_marginTop="20dp"/>

<Button
    android:id="@+id/test2"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="按钮2"
    android:layout_below="@+id/test1"
    android:layout_centerHorizontal="true"/>

private TextView textView;
private Handler handler = new Handler(new Handler.Callback() {
    @Override
    public boolean handleMessage(Message message) {
        ProgressDialog dialog = (ProgressDialog) message.obj; //获取到之前创建的dialog对象
        dialog.cancel();  //隐藏对话框
        textView.setText("下载成功");
        return false;
    }
});

textView = (TextView) findViewById(R.id.textView);
Button button1 = (Button) findViewById(R.id.test1);
Button button2 = (Button) findViewById(R.id.test2);
button1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(final View view) {
        final ProgressDialog dialog = new ProgressDialog(MyProgressDialog.this); //变量生命周期短，需要进行拷贝
        dialog.setTitle("标题信息");
        dialog.setMessage("正在下载");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); //设置进度条样式
        dialog.setMax(100);
        dialog.incrementProgressBy(15);
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message(); //创建消息对象
                msg.obj = dialog; //给msg对象创建一个obj的变量
                handler.sendMessage(msg);  //给handler发送一个msg的对象，让handler接收
            }
        }).start();
    }
});