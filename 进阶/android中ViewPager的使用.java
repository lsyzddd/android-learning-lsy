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
    private ImageView[] dots;
    private int[] ids = {
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

    private void initDots() {
        dots = new ImageView[views.size()];
        for(int i = 0;i < views.size();i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        for(int j = 0;j < ids.length;j++) {
            if(i == j) {
                dots[j].setImageResource(R.drawable.light);
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