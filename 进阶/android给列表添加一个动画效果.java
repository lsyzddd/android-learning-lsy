/*
 * 列表的动画效果
 **/

第一种方法实现列表的动画效果

public class ListViewAnimation extends ListActivity {

    private ArrayAdapter<String> adapter;  //创建数据适配器
    private LayoutAnimationController lac;  //创建布局动画控制器
    private ScaleAnimation sa;  //创建缩放动画对象

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        // 给数据适配器赋值
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{
            "列表项1",
            "列表项2",
            "列表项3",
            "列表项4",
            "列表项5"
        });
        setListAdapter(adapter);  //设置列表适配器
        sa = new ScaleAnimation(0,1,0,1);  //创建缩放动画对象
        sa.setDuration(1000);  //设置动画时间间隔
        lac = new LayoutAnimationController(sa,0.5f);  //给布局动画控制器设置值，包括动画对象和动画播放时间间隔
        getListView().setLayoutAnimation(lac);  //给列表视图设置布局动画控制器
    }
}

第二种方法实现列表的动画效果

//使用配置文件的方式实现动画效果

//ListViewAnimation.java

public class ListViewAnimation extends ListActivity {

    private ArrayAdapter<String> adapter;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_listviewanimation);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{
            "列表项1",
            "列表项2",
            "列表项3",
            "列表项4",
            "列表项5"
        });
        setListAdapter(adapter);
    }
}

//activity_listviewanimation.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ListView
        android:id="@android:id/list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layoutAnimation="@anim/listview_animation"/>

</RelativeLayout>

//listview_animation.xml

<?xml version="1.0" encoding="utf-8"?>
<layoutAnimation xmlns:android="http://schemas.android.com/apk/res/android"
    android:animation="@anim/listview_scale"
    android:delay="0.5">

</layoutAnimation>

//listview_scale.xml

<?xml version="1.0" encoding="utf-8"?>
<scale xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromXScale="0"
    android:toXScale="1"
    android:fromYScale="0"
    android:toYScale="1"
    android:duration="1000">

</scale>