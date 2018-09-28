/*
 * android自定义视图属性的一些方法
 **/

//只要在布局文件中引入对应自定义组件,那么View类中构造函数将自动执行

//MyRect.java

public class MyRect extends View {

    public MyRect(Context context) {
        super(context);
    }
    //以下函数自动执行
    public MyRect(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //关联自定义属性，定义属性数组
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.MyView);
        //定义颜色变量
        int color = ta.getColor(R.styleable.MyView_rect_color,0xffff0000);
        //设置背景色
        setBackgroundColor(color);
        //回收数据
        ta.recycle();
    }

}

//activity_myrect.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:jkxy="http://schemas.android.com/apk/res-auto"  //定义自己的命名空间为jkxy
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.example.myandroid03.MyRect
        android:layout_width="100dp"
        android:layout_height="100dp"
        jkxy:rect_color="#3F51B5"/>  //设置自定义命名空间的属性值

</LinearLayout>

//attrs.xml

<?xml version="1.0" encoding="utf-8" ?>
<resources>
    <declare-styleable name="MyView">
        <attr name="rect_color" format="color" />
    </declare-styleable>
</resources>

//自定义按钮皮肤

//button_skin.xml

<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_pressed="false" android:drawable="@drawable/pic1"/> //在没按下按钮时的按钮背景
    <item android:state_pressed="true" android:drawable="@drawable/pic2" /> //按下按钮时的按钮背景
</selector>

//activity_custombutton.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <Button
        android:id="@+id/button"
        android:background="@drawable/button_skin"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</RelativeLayout>

//使用canvas进行画图

//RotateRect.java

public class RotateRect extends View {

    private Paint p;

    public RotateRect(Context context) {
        super(context);
        initProperties();
    }

    public RotateRect(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initProperties();
    }

    public RotateRect(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initProperties();
    }

    public RotateRect(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initProperties();
    }

    public void draw(final Canvas canvas) {
        super.draw(canvas);
        canvas.drawRect(0,0,300,300,p);

    }

    private void initProperties() {
        p = new Paint();
        p.setColor(Color.RED);
    }

}

//activity_drawrect.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.example.myandroid03.RotateRect
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</RelativeLayout>
