/*
 * 为布局添加动画效果
 **/

//activity_layoutanimation.xml

<?xml version="1.0" encoding="utf-8"?>
<fragment xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/container"
    android:name="com.example.myandroid03.PlaceholderFragment"  //布局文件所在的类文件
    android:layout_width="match_parent"
    android:layout_height="match_parent" />

//activity_fragment.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="按钮"/>

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="按钮"/>

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="按钮"/>

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="按钮"/>

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="按钮"/>

</LinearLayout>

//LayoutAnimation.java

public class LayoutAnimation extends AppCompatActivity {
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_layoutanimation);
        if(saveInstanceState == null) {  //此处saveInstanceState的值为null
            getSupportFragmentManager().
                beginTransaction().
                add(R.id.container,new PlaceholderFragment()).
                commit();
        }
    }

}

//PlaceholderFragment.java

public class PlaceholderFragment extends Fragment {
    public PlaceholderFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //创建线性布局视图，其中将activity_fragment的布局文件添加到fragment中
        final LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.activity_fragment,container,false);
        //创建方法动画对象
        ScaleAnimation sa = new ScaleAnimation(0,1,0,1);
        //设置动画时间
        sa.setDuration(5000);
        //创建布局动画控制器，其中0.5f为一半时间的动画效果
        LayoutAnimationController lac = new LayoutAnimationController(sa,0.5f);
        //设置动画效果的实现顺序 ORDER_NORMAL  ORDER_RANDOM  ORDER_REVERSE
        lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
        //给线性布局设置动画效果
        rootView.setLayoutAnimation(lac);
        //返回线性布局视图
        return rootView;
    }
}