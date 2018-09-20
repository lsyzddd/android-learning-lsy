/*
 * android的intent跳转进阶
 **/

// 跳转至浏览器的某个连接

startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com")));

// AppCompatActivity和Activity的区别

AppCompatActivity携带导航和标题，Activity不携带任何东西

//activity传递参数的方法

1.第一种方法，使用putExtra
Intent intent = new Intent(LearnActivity02.this,LearnActivity03.class);
intent.putExtra("data","传递的数据");

2.第二种方法，使用bundle传递数据
Intent intent = new Intent(LearnActivity02.this,LearnActivity03.class);
Bundle bundle = new Bundle();
bundle.putString("name","jikexueyuan");
bundle.putInt("age",20);
intent.putExtras(bundle);
startActivity(intent);

3.第三种方法，将bundle数据打包
intent.putExtra("bundle",bundle);

//activity接收参数的方法

1.第一种方法
Intent intent = getIntent();
String data = intent.getStringExtra("data");

2.第二种方法
Intent intent = getIntent();
Bundle bundle = intent.getExtras();
String name = bundle.getString("name");
int age = bundle.getInt("age");
TextView textView = (TextView) findViewById(R.id.textView);
textView.setText(String.format("name=%s,age=%d",name,age));

3.第三种方法，获取bundle数据包
Bundle bundle = intent.getBundleExtra("bundle");

//getString设置默认值
bundle.getString("name","defaultName");