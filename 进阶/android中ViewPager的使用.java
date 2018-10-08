/*
 * 使用ViewPager作为应用的启动页
 **/

//activity_guide.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.v4.view.ViewPager  //ViewPager的组件
        android:id="@+id/container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@drawable/view1"/>

</RelativeLayout>

//GuideActivity.java

public class GuideActivity extends Activity {
    private ViewPager vp;  //创建ViewPager的对象
    private ViewPagerActivity vpAdapter;   //创建ViewPager的数据适配器对象
    private List<View> views;  //创建视图列表对象

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initViews();  //初始化视图
    }

    public void initViews() {
    	//创建布局服务对象
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        //通过布局填充的方式在视图列表中添加视图
        views.add(inflater.inflate(R.layout.activity_viewpage_one,null));
        views.add(inflater.inflate(R.layout.activity_viewpage_two,null));
        views.add(inflater.inflate(R.layout.activity_viewpage_three,null));
        views.add(inflater.inflate(R.layout.activity_viewpage_four,null));
        views.add(inflater.inflate(R.layout.activity_viewpage_five,null));
        views.add(inflater.inflate(R.layout.activity_viewpage_six,null));
        vpAdapter = new ViewPagerActivity(views,this);  //给ViewPager数据适配器设置值
        vp = (ViewPager) findViewById(R.id.container);
        vp.setAdapter(vpAdapter);  //给ViewPager组件赋值数据适配器
    }
}

//ViewPagerActivity.java

public class ViewPagerActivity extends PagerAdapter {

    private List<View> views;  //创建视图列表对象
    private Context context;   //创建上下文对象

    //给本类创建构造函数
    public ViewPagerActivity(List<View> views,Context context) {
        this.views = views;
        this.context = context;
    }

    //销毁某个视图
    public void destroyItem(View container,int position,Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }

    //添加某个视图并返回某个视图
    public Object instantiateItem(View container,int position) {
        ((ViewPager) container).addView(views.get(position));
        return views.get(position);
    }

    //返回视图的个数
    @Override
    public int getCount() {
        return views.size();
    }

    //判断视图是否是对象
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}

//activity_viewpage_one.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:src="@drawable/view1"
        android:scaleType="fitXY"/>

	//android:scaleType的值默认为fitCenter(意思是图片按照比例扩大到视图的宽和高然后居中显示)
    //然后设置android:scaleType="fitXY"的作用是强制将该组件拉伸到父控件的大小

</RelativeLayout>

//给导航页设置导航点

//activity_guide.xml中给ViewPager底部添加导航点的布局

//以下节点添加在和ViewPager同一个级别节点下

<LinearLayout
    android:id="@+id/linearLayout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:gravity="center_horizontal"
    android:layout_alignParentBottom="true">
    <ImageView
        android:id="@+id/point_first"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/light"/>
    <ImageView
        android:id="@+id/point_second"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/no_light"/>
    <ImageView
        android:id="@+id/point_third"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/no_light"/>
    <ImageView
        android:id="@+id/point_four"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/no_light"/>
    <ImageView
        android:id="@+id/point_five"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/no_light"/>
    <ImageView
        android:id="@+id/point_six"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/no_light"/>
</LinearLayout>

//给ViewPager添加监听事件

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {
    private ViewPager vp;
    private ViewPagerActivity vpAdapter;
    private List<View> views;
    private ImageView[] dots;  //创建图片数组
    private int[] ids = {      //创建图片id数组
            R.id.point_first,
            R.id.point_second,
            R.id.point_third,
            R.id.point_four,
            R.id.point_five,
            R.id.point_six
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initViews();
        initDots();
    }

    public void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.activity_viewpage_one,null));
        views.add(inflater.inflate(R.layout.activity_viewpage_two,null));
        views.add(inflater.inflate(R.layout.activity_viewpage_three,null));
        views.add(inflater.inflate(R.layout.activity_viewpage_four,null));
        views.add(inflater.inflate(R.layout.activity_viewpage_five,null));
        views.add(inflater.inflate(R.layout.activity_viewpage_six,null));
        vpAdapter = new ViewPagerActivity(views,this);
        vp = (ViewPager) findViewById(R.id.container);
        vp.setAdapter(vpAdapter);
        vp.setOnPageChangeListener(this);
    }

    private void initDots() {  //初始化导航点图片数组
        dots = new ImageView[views.size()];  //创建图片视图数组长度
        for(int i = 0;i < views.size();i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {   //在ViewPager选择发生改变时触发的事件
        for(int j = 0;j < ids.length;j++) {
            if(i == j) {
                dots[j].setImageResource(R.drawable.light);  //设置图片的资源
            }
            else {
                dots[j].setImageResource(R.drawable.no_light);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}

//在最后一个导航页中点击按钮进入主页面

Button intoMainButton = (Button) views.get(5).findViewById(R.id.toFirstActivity);
intoMainButton.setOnClickListener(this);

public void onClick(View v) {
    Intent i = new Intent(GuideActivity.this,GuideMain.class);
    startActivity(i);
    finish();
}

//使用本地变量决定是进入导航页还是主页面

public class WelcomeActivity extends Activity {

    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    private static final int TIME = 1000;
    private Boolean isFirstIn = false;
    @SuppressLint("HandlerLeak")
    private Handler mhandler = new Handler() {  //创建handler对象
        public void handleMessage(android.os.Message msg) {  //创建处理消息的函数
            switch(msg.what) {
                case GO_HOME:
                    goHome();  //跳转到主页
                    break;
                case GO_GUIDE:
                    goGuide();  //跳转到导航页
                    break;
            }
        }
    };

    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_welcome);
        init();
    }

    public void init() {
        // 此方法接收两个参数，第一个参数用于指定SharedPreferences文件的名称（格式为xml文件），
        // 如果指定的文件不存在则会创建一个。SharedPreferences文件都是存放在/data/data/<packagename>/shared_prefs/
        // 目录下的；第二个参数用于指定操作模式：
        // MODE_PRIVATE：默认操作模式，和直接传0效果相同，表示只有当前应用程序才可以对这个SharedPreferences文件进行读写
        // MODE_WORLD_READABLE：指定此SharedPreferences对其他程序只读且无法修改。
        // MODE_WORLD_WRITEABLE：指定此SharedPreferences能被其他程序读写。
        SharedPreferences perPreferences = getSharedPreferences("jike",MODE_PRIVATE);
        isFirstIn = perPreferences.getBoolean("isFirstIn",true);
        if(!isFirstIn) {
            mhandler.sendEmptyMessageDelayed(GO_HOME,TIME);
        }
        else {
            mhandler.sendEmptyMessageDelayed(GO_GUIDE,TIME);
            // 调用SharedPreferences对象的edit()方法来获取一个SharedPreferences.Editor对象
            SharedPreferences.Editor editor = perPreferences.edit();
            // 向SharedPreferences.Editor对象中添加数据，比如添加一个布尔型数据就使用putBoolean方法，
            // 添加一个字符串就用putString()方法，以此类推
            editor.putBoolean("isFirstIn",false);
            // 调用commit()方法将添加的数据提交，从而完成数据存储操作
            editor.commit();
        }
    }

    private void goHome() {
        Intent i = new Intent(WelcomeActivity.this,GuideMain.class);
        startActivity(i);
        finish();
    }

    private void goGuide() {
        Intent i = new Intent(WelcomeActivity.this,GuideActivity.class);
        startActivity(i);
        finish();
    }

}