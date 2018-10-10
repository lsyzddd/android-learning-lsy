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