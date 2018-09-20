/*
 * 简单的activity页面跳转
 * 创建两个独立的activity，将两个activity配置到AndroidManifest中去
 **/

Button nextButton = (Button) findViewById(R.id.next);
nextButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();  //创建intent对象
        intent.setClass(IntentActivity.this,DialogDateAndTimePicker.class);  //设置页面跳转的起始和目的
        IntentActivity.this.startActivity(intent);  //页面开始跳转
    }
});

/*
 * 两个activity之间进行传参
 **/

//第一个activity

Button nextButton = (Button) findViewById(R.id.next);
nextButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String uname = editText.getText().toString();
        Intent intent = new Intent();
        intent.setClass(IntentActivity.this,DialogDateAndTimePicker.class);
        intent.putExtra("uname",uname);  //给intent对象添加额外需要传入的参数
        IntentActivity.this.startActivity(intent);
    }
});

//第二个activity

Intent intent = this.getIntent();  //获取到intent对象
String uname = intent.getStringExtra("uname");  //从intent中获取到需要的额外参数

/*
 * 单页跳转应用
 **/

//其中传入需要重新绘制的activity
setContentView();

/*
 * 页面之间传值，将第二个页面中的值传给第一个页面
 **/

//第一个activity活动页面
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <Button
        android:id="@+id/firstButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="跳转"
        android:layout_centerHorizontal="true"/>

    <EditText
        android:id="@+id/firstEditText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/firstButton"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="20dp"/>

</RelativeLayout>

Button button = (Button) findViewById(R.id.firstButton);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(FirstActivity.this,SecondActivity.class);
        //第二个参数用于传给onActivityResult中的requestCode
        FirstActivity.this.startActivityForResult(intent,1);
    }
});

//处理上一个页面关闭后传回的数据，该数据在data中返回
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    String value = data.getStringExtra("msg"); //获取额外参数msg的值
    EditText editText = (EditText) findViewById(R.id.firstEditText);
    editText.setText(value);
}

//第二个activity活动页面
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <EditText
        android:id="@+id/secondEditText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"/>

    <Button
        android:id="@+id/secondButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/secondEditText"
        android:layout_marginTop="20dp"
        android:text="返回"/>

</RelativeLayout>

Button button = (Button) findViewById(R.id.secondButton);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        EditText editText = (EditText) findViewById(R.id.secondEditText);
        String msg = editText.getText().toString();
        Intent intent = getIntent();
        intent.putExtra("msg",msg);
        //第一个参数将传给onActivityResult中的resultCode
        SecondActivity.this.setResult(3,intent);
        SecondActivity.this.finish(); //销毁当前activity
    }
});