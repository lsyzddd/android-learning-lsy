/*
 * 最简单的对话框
 **/

AlertDialog.Builder builder = new AlertDialog.Builder(ThreadActivity.this);  /*创建弹框*/
builder.setTitle("提示标题");  /*设置标题*/
builder.setMessage("提示信息");  /*设置提示内容*/
AlertDialog alertDialog = builder.create();  /*弹框创建*/
alertDialog.show();  /*展示弹框*/

/*
 * 在对话框中设置按钮,并且监听对话框中的按钮事件
 **/

AlertDialog.Builder builder = new AlertDialog.Builder(ThreadActivity.this);
builder.setTitle("提示标题");
builder.setMessage("提示信息");
builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  /*PositiveButton代表积极的按钮*/
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Toast.makeText(ThreadActivity.this,"确定",Toast.LENGTH_SHORT).show();
    }
});
builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  /*NegativeButton代表消极的按钮*/
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Toast.makeText(ThreadActivity.this,"取消",Toast.LENGTH_SHORT).show();
    }
});
builder.setNeutralButton("不确定", new DialogInterface.OnClickListener() {  /*NeutralButton中间按钮,一般不用*/
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Toast.makeText(ThreadActivity.this,"不确定",Toast.LENGTH_SHORT).show();
    }
});
AlertDialog alertDialog = builder.create();
alertDialog.show();

/*
 * 创建单选对话框
 **/

dialogButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ThreadActivity.this);
        builder.setTitle("提示信息");
        builder.setSingleChoiceItems(ss, 0, new DialogInterface.OnClickListener() {  /*这里的0代表默认选中第一个单选项*/
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {  /*position代表第n个单选项*/
                Toast.makeText(ThreadActivity.this,ss[position],Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
});

/*
 * 创建多选框，并且获取选中的选项
 **/

private String[] ss = new String[] {
    "北京",
    "上海",
    "深圳"
};

Button dialogButton = (Button) findViewById(R.id.dialogButton4);
dialogButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ThreadActivity.this);
        builder.setTitle("多选对话框");
        // 中间布尔类型的数组和ss数组一一对应
        builder.setMultiChoiceItems(ss, new boolean[]{false, false, false}, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean b) {
                Toast.makeText(ThreadActivity.this,ss[position],Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
});

/*
 * 创建自定义对话框
 **/

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingLeft="15dp"
    android:paddingRight="15dp"
    android:paddingTop="20dp"
    android:paddingBottom="20dp"
    android:background="@color/lightGray">

    <LinearLayout
        android:id="@+id/layoutUsername"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <TextView
            android:id="@+id/usernameField"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="@dimen/login_field_size"
            android:textColor="@color/gray"
            android:text="@string/username"
            android:paddingTop="2dp"
            android:paddingBottom="2dp"/>
        <EditText
            android:id="@+id/editUsername"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:inputType="text"
            android:textSize="@dimen/login_value_size"
            android:hint="@string/placeholderUsername"
            android:textColorHint="#bdbdbd"
            android:background="@color/white"
            android:paddingTop="3dp"
            android:paddingBottom="3dp"
            android:paddingLeft="10dp"
            android:paddingRight="10dp"
            android:layout_toRightOf="@+id/usernameField"/>
    </LinearLayout>

    <LinearLayout
        android:id="@+id/layoutPassword"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/layoutUsername"
        android:layout_marginTop="10dp">
        <TextView
            android:id="@+id/passwordField"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="@dimen/login_field_size"
            android:textColor="@color/gray"
            android:text="@string/password"
            android:paddingTop="2dp"
            android:paddingBottom="2dp"/>
        <EditText
            android:id="@+id/editPassword"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:inputType="textPassword"
            android:textSize="@dimen/login_value_size"
            android:hint="@string/placeholderPassword"
            android:textColorHint="#bdbdbd"
            android:background="@color/white"
            android:paddingTop="3dp"
            android:paddingBottom="3dp"
            android:paddingLeft="10dp"
            android:paddingRight="10dp"
            android:layout_toRightOf="@+id/passwordField"/>
    </LinearLayout>

    <RelativeLayout
        android:id="@+id/layoutButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/layoutPassword"
        android:layout_marginTop="10dp">
        <Button
            android:id="@+id/buttonReset"
            android:layout_width="80dp"
            android:layout_height="40dp"
            android:layout_toLeftOf="@+id/buttonLogin"
            android:text="@string/reset"
            android:textSize="16sp"
            android:background="@drawable/reset_button"
            android:layout_marginRight="10dp"/>
        <Button
            android:id="@+id/buttonLogin"
            android:layout_width="80dp"
            android:layout_height="40dp"
            android:layout_alignParentRight="true"
            android:textSize="16sp"
            android:background="@drawable/login_button"
            android:text="@string/login"/>
    </RelativeLayout>

</RelativeLayout>

dialogCustom.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ThreadActivity.this);
        builder.setTitle("登录窗口");
        View loginView = ThreadActivity.this.getLayoutInflater().inflate(R.layout.activity_login,null);
        builder.setView(loginView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
});