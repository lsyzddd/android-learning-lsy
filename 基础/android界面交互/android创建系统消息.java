/*
 * android创建系统应用消息,并有消息提示和默认的声音、震动
 **/

//权限添加
<uses-permission android:name="android.permission.VIBRATE" />

Button buttonDrag = (Button) findViewById(R.id.buttonMove);

buttonDrag.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        NotificationManager manager = (NotificationManager) ThreadActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ThreadActivity.this);
        mBuilder.setSmallIcon(android.R.drawable.sym_def_app_icon);
        mBuilder.setContentTitle("我的应用");
        mBuilder.setContentText("系统消息");
        mBuilder.setTicker("系统消息提示");
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        Notification notification = mBuilder.build();
        manager.notify(1,notification);
    }
});