/*
 * DrawLayout的使用
 **/

//activity_drawlayout.xml

<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.DrawerLayout  //andorid官方提供的侧滑菜单栏使用support.v4z支持的DrawLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/drawLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!--The content view-->  //frameLayout中的内容是主视图

    <FrameLayout
        android:id="@+id/frameLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </FrameLayout>

    <!--The navigation view-->  //listView中的是导航视图(侧滑菜单栏)

    <ListView
        android:id="@+id/listView"
        android:layout_width="240dp"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:background="#ffffcc">

    </ListView>

</android.support.v4.widget.DrawerLayout>

//在侧滑菜单栏中添加数据列表

1.创建数组
2.给数组赋值
3.创建数据集
4.给数据集绑定系统列表组件(样式)和数组(数据)
5.给列表组件绑定绑定数据集

public class DrawLayout extends AppCompatActivity {

    private DrawerLayout drawLayout;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> menuList;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_drawlayout);
        drawLayout = (DrawerLayout) findViewById(R.id.drawLayout);
        listView = (ListView) findViewById(R.id.listView);
        menuList = new ArrayList<String>();
        for(int i = 0;i< 5;i++) {
            menuList.add("极客学院"+i);
        }
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuList);
        listView.setAdapter(arrayAdapter);
    }
}

//给DrawLayout菜单设置选中项事件

//activity_drawlayout.xml

<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/drawLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!--The content view-->

    <FrameLayout
        android:id="@+id/frameLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </FrameLayout>

    <!--The navigation view-->

    <ListView
        android:id="@+id/listView"
        android:layout_width="240dp"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:background="#ffffcc">

    </ListView>

</android.support.v4.widget.DrawerLayout>

//activity_contentfragment.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="主页内容"/>

</RelativeLayout>

//DrawLayout.java

public class DrawLayout extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawLayout;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> menuList;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_drawlayout);
        drawLayout = (DrawerLayout) findViewById(R.id.drawLayout);
        listView = (ListView) findViewById(R.id.listView);
        menuList = new ArrayList<String>();  //给创建的空数组赋值
        for(int i = 0;i< 5;i++) {
            menuList.add("极客学院"+i);
        }
        //给数据集赋值所需要的组件和内容
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuList);
        listView.setAdapter(arrayAdapter);  //给菜单列表设置数据集
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContentFragment contentFragment = new ContentFragment();  //创建内容片断对象
        Bundle args = new Bundle();  //创建数据绑定
        args.putString("text",menuList.get(position));  //获取到菜单列表中被点击项的文字，并放到数据绑定中
        contentFragment.setArguments(args);  //给内容片断设置需要传递的参数
        FragmentManager fm = getFragmentManager();  //创建内容片断管理对象
        fm.beginTransaction().replace(R.id.frameLayout,contentFragment).commit();  //给fragment替换内容
        drawLayout.closeDrawer(listView);  //关闭侧滑菜单
    }
}

//ContentFragment.java

public class ContentFragment extends Fragment {

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_contentfragment,container,false);  //加载内容片断视图
        textView = (TextView) view.findViewById(R.id.textView);
        String text = getArguments().getString("text");  //获取到内容片断传递过来的参数
        textView.setText(text);  //改变内容片断视图中的textView的文字
        return view;  //返回视图
    }
}

//当滑动侧边菜单栏时改动应用顶部的标题

public class DrawLayout extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawLayout;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> menuList;
    private ActionBarDrawerToggle mDrawToggle;
    private String title;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_drawlayout);
        title = (String) getTitle();  //保存应用的原本标题
        drawLayout = (DrawerLayout) findViewById(R.id.drawLayout);
        listView = (ListView) findViewById(R.id.listView);
        menuList = new ArrayList<String>();
        for(int i = 0;i< 5;i++) {
            menuList.add("极客学院"+i);
        }
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
        //注意此处的ActionBarDrawerToggle的引用包,必须使用support.v4的
        //import android.support.v4.app.ActionBarDrawerToggle
        //创建侧滑菜单栏被滑动时的监听对象
        mDrawToggle = new ActionBarDrawerToggle(this,drawLayout,R.drawable.ic_drawer,R.string.draw_open,R.string.draw_close) {
            public void onDrawerOpened(View drawerView) {  //重写侧滑菜单栏被打开时
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("请选择");
            }
            public void onDrawerClosed(View drawerView) {  //重写侧滑菜单栏被关闭时
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(title);  //获取actionBar并设置标题
            }
        };
        drawLayout.setDrawerListener(mDrawToggle);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString("text",menuList.get(position));
        contentFragment.setArguments(args);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.frameLayout,contentFragment).commit();
        drawLayout.closeDrawer(listView);
    }
}

//当侧滑菜单栏出现时隐藏顶部的图标,当侧滑菜单栏隐藏时展示顶部的图标

//menu.xml

<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <item
        android:id="@+id/action_websearch"
        android:icon="@drawable/search"
        android:title="@string/websearch"
        app:showAsAction="ifRoom|withText" />

</menu>

//DrawLayout.java

public class DrawLayout extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawLayout;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> menuList;
    private ActionBarDrawerToggle mDrawToggle;
    private String title;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_drawlayout);
        title = (String) getTitle();
        drawLayout = (DrawerLayout) findViewById(R.id.drawLayout);
        listView = (ListView) findViewById(R.id.listView);
        menuList = new ArrayList<String>();
        for(int i = 0;i< 5;i++) {
            menuList.add("极客学院"+i);
        }
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
        mDrawToggle = new ActionBarDrawerToggle(this,drawLayout,R.drawable.ic_drawer,R.string.draw_open,R.string.draw_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("请选择");
                invalidateOptionsMenu();  //通知系统刷新菜单
            }
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu();
            }
        };
        drawLayout.setDrawerListener(mDrawToggle);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString("text",menuList.get(position));
        contentFragment.setArguments(args);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.frameLayout,contentFragment).commit();
        drawLayout.closeDrawer(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean isDrawOpen = drawLayout.isDrawerOpen(listView);  //判断侧滑菜单栏是否已经打开
        menu.findItem(R.id.action_websearch).setVisible(!isDrawOpen);  //在顶部菜单栏中找到搜索的按钮，然后设置该元素的是否显示或者隐藏
        return super.onPrepareOptionsMenu(menu);
    }
}

//给顶部的搜索按钮添加点击跳转到Internet

public class DrawLayout extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawLayout;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> menuList;
    private ActionBarDrawerToggle mDrawToggle;
    private String title;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_drawlayout);
        title = (String) getTitle();
        drawLayout = (DrawerLayout) findViewById(R.id.drawLayout);
        listView = (ListView) findViewById(R.id.listView);
        menuList = new ArrayList<String>();
        for(int i = 0;i< 5;i++) {
            menuList.add("极客学院"+i);
        }
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
        mDrawToggle = new ActionBarDrawerToggle(this,drawLayout,R.drawable.ic_drawer,R.string.draw_open,R.string.draw_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("请选择");
                invalidateOptionsMenu();
            }
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu();
            }
        };
        drawLayout.setDrawerListener(mDrawToggle);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString("text",menuList.get(position));
        contentFragment.setArguments(args);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.frameLayout,contentFragment).commit();
        drawLayout.closeDrawer(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean isDrawOpen = drawLayout.isDrawerOpen(listView);
        menu.findItem(R.id.action_websearch).setVisible(!isDrawOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_websearch:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri uri = Uri.parse("http://www.baidu.com");
                intent.setData(uri);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

//点击顶部的左上角使得侧滑菜单栏消失

public class DrawLayout extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawLayout;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> menuList;
    private ActionBarDrawerToggle mDrawToggle;
    private String title;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_drawlayout);
        title = (String) getTitle();
        drawLayout = (DrawerLayout) findViewById(R.id.drawLayout);
        listView = (ListView) findViewById(R.id.listView);
        menuList = new ArrayList<String>();
        for(int i = 0;i< 5;i++) {
            menuList.add("极客学院"+i);
        }
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
        mDrawToggle = new ActionBarDrawerToggle(this,drawLayout,R.drawable.ic_drawer,R.string.draw_open,R.string.draw_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("请选择");
                invalidateOptionsMenu();
            }
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu();
            }
        };
        drawLayout.setDrawerListener(mDrawToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString("text",menuList.get(position));
        contentFragment.setArguments(args);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.frameLayout,contentFragment).commit();
        drawLayout.closeDrawer(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean isDrawOpen = drawLayout.isDrawerOpen(listView);
        menu.findItem(R.id.action_websearch).setVisible(!isDrawOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    //如果当侧滑菜单栏出现时，顶部的返回图标将会出现
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch(item.getItemId()) {
            case R.id.action_websearch:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri uri = Uri.parse("http://www.baidu.com");
                intent.setData(uri);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}