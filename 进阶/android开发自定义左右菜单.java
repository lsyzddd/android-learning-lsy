/*
 * 自定义左右菜单
 **/

//CustomSlidingMenu.java

public class CustomSlidingMenu extends FragmentActivity {

    private MainUI mainUI;
    private LeftMenuActivity leftMenu;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        mainUI = new MainUI(this);
        setContentView(mainUI);
        leftMenu = new LeftMenuActivity();
        //给对应的布局添加对应的视图组件
        getSupportFragmentManager().beginTransaction().add(MainUI.LEFT_ID,leftMenu).commit();
    }

}

//LeftMenuActivity.java

public class LeftMenuActivity extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    //创建视图的函数
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //加载布局文件
        View v = inflater.inflate(R.layout.activity_menu_left,container,false);
        //在该布局文件下找到按钮
        Button leftMenuButton = (Button) v.findViewById(R.id.left_menu_button);
        leftMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myLog","被点击了");
            }
        });
        return v;
    }
}

//MainUI.java

public class MainUI extends RelativeLayout {

    public static final int LEFT_ID = 0xaabbcc;  //左侧布局的ID
    public static final int MIDDLE_ID = 0xaaccbb;  //中间布局的ID
    public static final int RIGHT_ID = 0xccbbaa;  //右侧布局的ID
    private Context context;
    private FrameLayout leftMenu;
    private FrameLayout middleMenu;
    private FrameLayout rightMenu;
    private static final int TEST_DIS = 20;
    private boolean isTestComplete;
    private boolean isLeftRightEvent;
    private Point point;
    private Scroller scroller;

    public MainUI(Context context) {
        super(context);
        initView(context);
    }

    public MainUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MainUI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MainUI(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(Context context) {
        this.context = context;  //获得构造函数的上下文
        scroller = new Scroller(context,new DecelerateInterpolator());
        leftMenu = new FrameLayout(context);
        middleMenu = new FrameLayout(context);
        rightMenu = new FrameLayout(context);
        leftMenu.setBackgroundColor(Color.RED);
        middleMenu.setBackgroundColor(Color.GREEN);
        rightMenu.setBackgroundColor(Color.RED);
        leftMenu.setId(LEFT_ID);
        middleMenu.setId(MIDDLE_ID);
        rightMenu.setId(RIGHT_ID);
        addView(leftMenu);
        addView(middleMenu);
        addView(rightMenu);
        point = new Point();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        middleMenu.measure(widthMeasureSpec,heightMeasureSpec);  //给中间视图的宽和高赋值
        int realWidth = MeasureSpec.getSize(widthMeasureSpec);
        //定义一个准确确定的宽度
        int tempWidthMeasure = MeasureSpec.makeMeasureSpec((int) (realWidth * 0.8f),MeasureSpec.EXACTLY);
        leftMenu.measure(tempWidthMeasure,heightMeasureSpec);
        rightMenu.measure(tempWidthMeasure,heightMeasureSpec);
    }

    //布局初始化的构造函数
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //给中间的布局设置左上右下的坐标
        middleMenu.layout(l,t,r,b);
        //给左边的布局设置左上右下的坐标
        leftMenu.layout(l - leftMenu.getMeasuredWidth(),t,r,b);]
        //给右边的布局设置左上右下的坐标
        rightMenu.layout(l + middleMenu.getMeasuredWidth(),t,l + middleMenu.getMeasuredWidth() + rightMenu.getMeasuredWidth(),b);
    }

    //发布的触摸事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(!isTestComplete) {
            getEventType(ev);
            return true;
        }
        if(isLeftRightEvent) {
            slidingLeftOrRight(ev);
        }
        else {
            slidingUpOrDown(ev);
        }
        return super.dispatchTouchEvent(ev);
    }


    //获取事件类型
    public void getEventType(MotionEvent ev) {
        switch(ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                //在手指触摸按下后
                afterActionDown(ev);
                super.dispatchTouchEvent(ev);
            break;
            case MotionEvent.ACTION_MOVE:
                //在滑动事件发生后的事件
                afterActionMove(ev);
            break;
            case MotionEvent.ACTION_UP:  //在手指触摸抬起后
            case MotionEvent.ACTION_CANCEL:  //当用户按下操作，并移动到外层控件时
                afterActionCancel(ev);
            break;
        }
    }


    //设置触摸点的横纵坐标
    public void setPointXY(MotionEvent ev) {
        point.x = (int) ev.getX();
        point.y = (int) ev.getY();
    }

    public void afterActionDown(MotionEvent ev) {
        setPointXY(ev);
    }

    public void afterActionMove(MotionEvent ev) {
        //ev.getX()是触摸屏幕的横坐标，point.x是也是接触屏幕的横坐标，该值是从被赋的值中所取出的
        int dX = Math.abs((int)ev.getX() - point.x);
        //ev.getY()是触摸屏幕的纵坐标，point.y是也是接触屏幕的纵坐标，该值是从被赋的值中取出来的
        int dY = Math.abs((int)ev.getY() - point.y);
        //以上的dx和dy可能不等于0，因为手指滑动的有快慢，获取坐标和赋值坐标的值肯定有误差，不如手指滑动的快
        //如果横向的滑动距离大于纵向的滑动距离，并且横坐标的滑动距离大于20，则认为是横向滑动
        if(dX >= TEST_DIS && dX > dY) {
            isLeftRightEvent = true;
            isTestComplete = true;
        }
        //如果纵向的滑动距离大于横向的滑动距离，并且纵坐标的滑动距离大于20，则认为是纵向滑动
        else if(dY >= TEST_DIS && dY > dX) {
            isLeftRightEvent = false;
            isTestComplete = true;
        }
        //滑动结束后给横纵坐标赋值
        setPointXY(ev);
    }

    public void afterActionUp(MotionEvent ev) {

    }

    public void afterActionCancel(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        resetVariable();
    }

    public void resetVariable() {
        isLeftRightEvent = false;
        isTestComplete = false;
    }

    //当滑动事件发生时会触发以下函数
    public void scrollerView(MotionEvent ev) {
        int finalX = 0;
        //获取滑动的距离
        int curScrollX = getScrollX();
        //滑动距离差是按下的横坐标减去离开屏幕时的横坐标
        int distanceX = (int) (ev.getX() - point.x);
        int expectX = curScrollX - distanceX;
        if(expectX < 0) {
            finalX = Math.max(expectX,-leftMenu.getMeasuredWidth());
        }
        else {
            finalX = Math.min(expectX,rightMenu.getMeasuredWidth());
        }
        scrollTo(finalX,0);
        point.x = (int) ev.getX();
    }

    //左滑和右划的事件
    public void slidingLeftOrRight(MotionEvent ev) {
        switch(ev.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:
                scrollerView(ev);
            break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                slidingAnimation();
                resetVariable();
            break;
        }
    }

    //上划和下划的事件
    public void slidingUpOrDown(MotionEvent ev) {
        switch(ev.getActionMasked()) {
            case MotionEvent.ACTION_UP:
                resetVariable();
            break;
        }
    }

    //边滚动边计算
    @Override
    public void computeScroll() {
        super.computeScroll();
        if(!scroller.computeScrollOffset()) {
            return;
        }
        int tempx = scroller.getCurrX();
        scrollTo(tempx,0);
    }

    public void slidingAnimation() {
        int curScrollX = getScrollX();
        if(Math.abs(curScrollX) > leftMenu.getMeasuredWidth() >> 1) {
            if(curScrollX < 0) {
                scroller.startScroll(curScrollX,0,-leftMenu.getMeasuredWidth() - curScrollX,0,200);
            }
            else {
                scroller.startScroll(curScrollX,0,rightMenu.getMeasuredWidth() - curScrollX,0,200);
            }
        }
        else {
            scroller.startScroll(curScrollX,0,-curScrollX,0,200);
        }
        invalidate();
    }

}

//给侧滑菜单栏的背景添加蒙版效果

//MainUI.java

public class MainUI extends RelativeLayout {

    public static final int LEFT_ID = 0xaabbcc;
    public static final int MIDDLE_ID = 0xaaccbb;
    public static final int RIGHT_ID = 0xccbbaa;
    private Context context;
    private FrameLayout leftMenu;
    private FrameLayout middleMenu;
    private FrameLayout rightMenu;
    private FrameLayout middleMask;  //创建蒙版遮罩层的对象
    private static final int TEST_DIS = 20;
    private boolean isTestComplete;
    private boolean isLeftRightEvent;
    private Point point;
    private Scroller scroller;

    public MainUI(Context context) {
        super(context);
        initView(context);
    }

    public MainUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MainUI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MainUI(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(Context context) {
        this.context = context;
        scroller = new Scroller(context,new DecelerateInterpolator());
        leftMenu = new FrameLayout(context);
        middleMenu = new FrameLayout(context);
        rightMenu = new FrameLayout(context);
        middleMask = new FrameLayout(context);
        leftMenu.setBackgroundColor(Color.RED);
        middleMenu.setBackgroundColor(Color.GREEN);
        rightMenu.setBackgroundColor(Color.RED);
        middleMask.setBackgroundColor(0x88000000);  //设置遮罩层的背景色
        leftMenu.setId(LEFT_ID);
        middleMenu.setId(MIDDLE_ID);
        rightMenu.setId(RIGHT_ID);
        addView(leftMenu);
        addView(middleMenu);
        addView(rightMenu);
        addView(middleMask);  //把遮罩层的视图添加到主视图上
        point = new Point();
        middleMask.setAlpha(0);   //把遮罩层的透明度设置成全透明状态
    }

    public float onMiddleMask() {
        Log.d("myLog","透明度" + middleMask.getAlpha());
        return middleMask.getAlpha();
    }

    //当视图发生滚动时，将自动执行以下的方法
    public void scrollTo(int x,int y) {
        super.scrollTo(x,y);
        //调用方法输出当前的透明度
        onMiddleMask();
        //获取到滚动的绝对值，这里的值是float类型
        int curX = Math.abs(getScrollX());
        //定义放大倍数为滑动的距离除以菜单栏的宽度，所得的结果必须是浮点型
        float scale = curX / (float)leftMenu.getMeasuredWidth();
        //给遮罩层的透明度设置该值
        middleMask.setAlpha(scale);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        middleMenu.measure(widthMeasureSpec,heightMeasureSpec);
        middleMask.measure(widthMeasureSpec,heightMeasureSpec);
        int realWidth = MeasureSpec.getSize(widthMeasureSpec);
        int tempWidthMeasure = MeasureSpec.makeMeasureSpec((int) (realWidth * 0.8f),MeasureSpec.EXACTLY);
        leftMenu.measure(tempWidthMeasure,heightMeasureSpec);
        rightMenu.measure(tempWidthMeasure,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        middleMenu.layout(l,t,r,b);
        middleMask.layout(l,t,r,b);
        leftMenu.layout(l - leftMenu.getMeasuredWidth(),t,r,b);
        rightMenu.layout(l + middleMenu.getMeasuredWidth(),t,l + middleMenu.getMeasuredWidth() + rightMenu.getMeasuredWidth(),b);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(!isTestComplete) {
            getEventType(ev);
            return true;
        }
        if(isLeftRightEvent) {
            slidingLeftOrRight(ev);
        }
        else {
            slidingUpOrDown(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void getEventType(MotionEvent ev) {
        switch(ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                afterActionDown(ev);
                super.dispatchTouchEvent(ev);
            break;
            case MotionEvent.ACTION_MOVE:
                afterActionMove(ev);
            break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                afterActionCancel(ev);
            break;
        }
    }

    public void setPointXY(MotionEvent ev) {
        point.x = (int) ev.getX();
        point.y = (int) ev.getY();
    }

    public void afterActionDown(MotionEvent ev) {
        setPointXY(ev);
    }

    public void afterActionMove(MotionEvent ev) {
        int dX = Math.abs((int)ev.getX() - point.x);
        int dY = Math.abs((int)ev.getY() - point.y);
        if(dX >= TEST_DIS && dX > dY) {
            isLeftRightEvent = true;
            isTestComplete = true;
        }
        else if(dY >= TEST_DIS && dY > dX) {
            isLeftRightEvent = false;
            isTestComplete = true;
        }
        setPointXY(ev);
    }

    public void afterActionUp(MotionEvent ev) {

    }

    public void afterActionCancel(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        resetVariable();
    }

    public void resetVariable() {
        isLeftRightEvent = false;
        isTestComplete = false;
    }

    public void scrollerView(MotionEvent ev) {
        int finalX = 0;
        int curScrollX = getScrollX();
        int distanceX = (int) (ev.getX() - point.x);
        int expectX = curScrollX - distanceX;
        //当向左滑动的时候，ev.getX的值<point.x的值，两者之差会接近0但是小于0，
        //此时expectX的值会因为误差而偏小，可能会小于leftMenu的宽度，所以需要取较大的值
        if(expectX < 0) {
            finalX = Math.max(expectX,-leftMenu.getMeasuredWidth());
        }
        //当向右滑动的时候，ev.getX的值>point.x的值，两者之差会接近0但是大于0，
        //此时expectX的值也会偏小，但是绝对不会大于rightMenu的值，所以取较小值
        else {
            finalX = Math.min(expectX,rightMenu.getMeasuredWidth());
        }
        //让侧滑菜单栏自己滑动到对应的位置
        scrollTo(finalX,0);
        point.x = (int) ev.getX();
    }

    //定义左滑和右划的事件
    public void slidingLeftOrRight(MotionEvent ev) {
        switch(ev.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:
                scrollerView(ev);
            break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                slidingAnimation();
                resetVariable();
            break;
        }
    }

    public void slidingUpOrDown(MotionEvent ev) {
        switch(ev.getActionMasked()) {
            case MotionEvent.ACTION_UP:
                resetVariable();
            break;
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(!scroller.computeScrollOffset()) {
            return;
        }
        int tempx = scroller.getCurrX();
        scrollTo(tempx,0);
    }

    //滑动的动画效果
    public void slidingAnimation() {
        //获取滑动的距离差
        int curScrollX = getScrollX();
        if(Math.abs(curScrollX) > leftMenu.getMeasuredWidth() >> 1) {
            //如果滑动的距离的绝对值大于滑动菜单的一半长度的话，并且如果滑动的距离差是小于0的话则认为是左滑动
            //将当前的视图向左滚动左菜单的长度
            if(curScrollX < 0) {
                scroller.startScroll(curScrollX,0,-leftMenu.getMeasuredWidth() - curScrollX,0,200);
            }
            //如果滑动的距离的绝对值大于滑动菜单的一半长度的话，并且如果滑动的距离差是大于0的话则认为是向右滑动
            //将当前的视图向右滚动右菜单的长度
            else {
                scroller.startScroll(curScrollX,0,rightMenu.getMeasuredWidth() - curScrollX,0,200);
            }
        }
        //如果不符合则返回原位
        else {
            scroller.startScroll(curScrollX,0,-curScrollX,0,200);
        }
        //此处需要重复渲染，因为一直在滑动
        invalidate();
    }

}