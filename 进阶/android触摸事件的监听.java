/*
 * 触摸事件的监听
 **/

//activity_touch_action.xml

<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

</FrameLayout>

//TouchActionActivty.java

public class TouchActionActivity extends Activity {

    private FrameLayout root;

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_touch_action);
        root = (FrameLayout) findViewById(R.id.container);
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("myLog","执行了action_down事件");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("myLog","执行了action_move事件");
                        //输出当前的坐标点
                        Log.d("myLog",String.format("x%f,y%f",event.getX(),event.getY()));
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("myLog","执行了action_up事件");
                        break;
                }
                //此处的return false决定了是否会触发接下来的事件，如果是return false那么操作系统会认为action_down事件
                //没有触发成功，但是如果是return true那么会触发所有的接触事件
                return false;
            }
        });
    }
}

//动态设置横纵坐标实现图标的手动拖动效果

//activty_touch_action.xml

<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/ic_launcher_background"/>

</FrameLayout>

//TouchActionActivity.java

public class TouchActionActivity extends Activity {

    private FrameLayout root;
    private ImageView imageView;

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_touch_action);
        root = (FrameLayout) findViewById(R.id.container);
        imageView = (ImageView) findViewById(R.id.imageView);
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("myLog","执行了action_down事件");
                        break;
                    case MotionEvent.ACTION_MOVE:
                    	//创建FramLayout的布局参数对象
                        FrameLayout.LayoutParams lp = (android.widget.FrameLayout.LayoutParams)imageView.getLayoutParams();
                        //给FrameLayout对象的属性赋值
                        lp.leftMargin = (int) event.getX();
                        lp.topMargin = (int) event.getY();
                        //给小图标设置布局的参数值
                        imageView.setLayoutParams(lp);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("myLog","执行了action_up事件");
                        break;
                }
                return true;
            }
        });
    }
}

//输出两个手指触摸时的横纵坐标

public class TouchActionActivity extends Activity {

    private FrameLayout root;

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_touch_action);
        root = (FrameLayout) findViewById(R.id.container);
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("myLog","执行了action_down事件");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(event.getPointerCount() == 2) {  //此处必须判断手指的触屏个数，否则会抛出错误
                            Log.d("myLog",String.format("x1:%f y1:%f x2:%f y2:%f",event.getX(0),event.getY(0),event.getX(1),event.getY(1)));
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("myLog","执行了action_up事件");
                        break;
                }
                return true;
            }
        });
    }
}

//使用手指放大图片和缩小图片

public class TouchActionActivity extends Activity {

    private FrameLayout root;
    private Float currentDistance;
    private Float lastDistance = -1f;
    private int maxLength = 300;  //固定了图标的最大长度
    private int minLength = 50;  //固定了图标的最小长度
    private FrameLayout.LayoutParams layoutParams;
    private ImageView imageView;

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_touch_action);
        root = (FrameLayout) findViewById(R.id.container);
        imageView = (ImageView) findViewById(R.id.imageView);
        layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                    	//限制了必须是两个手指触碰屏幕
                        if(event.getPointerCount() == 2) {
                            computeCurrentDistance(event);
                            startAnimation();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });
    }

    public void computeCurrentDistance(MotionEvent event) {
        float offsetX = event.getX(0) - event.getX(1);  //两个手指距离的横坐标之差
        float offsetY = event.getY(0) - event.getY(1);  //两个手指距离的纵坐标之差
        currentDistance = (float)Math.sqrt(offsetX * offsetX + offsetY * offsetY);  //计算两个手指之间的距离
        lastDistance = lastDistance == -1f?currentDistance:lastDistance;  //判断是否有历史的最新距离记录值
    }

    public void startAnimation() {
        if(lastDistance != -1f && currentDistance - lastDistance > 5) {
            scaleAnimation();  //放大图标
        }
        if(lastDistance != -1f && lastDistance - currentDistance > 5) {
            smallerAnimation();  //缩小图标
        }
    }

    public void setParams() {
    	//限制了最小宽度
        if(layoutParams.width <= minLength || layoutParams.height <= minLength) {
            layoutParams.width = minLength;
            layoutParams.height = minLength;
        }
        //限制了最大宽度
        if(layoutParams.width >= maxLength || layoutParams.height >= maxLength) {
            layoutParams.width = maxLength;
            layoutParams.height = maxLength;
        }
        //覆盖组件的布局参数
        imageView.setLayoutParams(layoutParams);
        //记录最后的距离差
        lastDistance = currentDistance;
    }

    public void scaleAnimation() {
    	//放大1.5倍
        layoutParams.width = (int) (1.5f * imageView.getWidth());
        layoutParams.height = (int) (1.5f * imageView.getHeight());
        setParams();
    }

    public void smallerAnimation() {
    	//缩小为原来的0.5
        layoutParams.width = (int) (0.5f * imageView.getWidth());
        layoutParams.height = (int) (0.5f * imageView.getHeight());
        setParams();
    }

}