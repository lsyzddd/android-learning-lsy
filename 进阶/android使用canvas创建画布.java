/*
 * 使用canvas创建画布
 **/

//画一个正方形

//CanvasView.java

public class CanvasView extends SurfaceView implements Callback {

    private Paint paint = null;

    public CanvasView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
        getHolder().addCallback(this);
    }

    public void draw() {
        Canvas canvas = getHolder().lockCanvas();
        canvas.drawColor(Color.WHITE);
        canvas.drawRect(0,0,100,100,paint);
        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        draw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}

//CanvasActivity.java

public class CanvasActivity extends Activity {
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(new CanvasView(this));
    }
}

//画线并旋转

public class CanvasView extends SurfaceView implements Callback {

    private Paint paint = null;

    public CanvasView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
        getHolder().addCallback(this);
    }

    public void draw() {
        Canvas canvas = getHolder().lockCanvas();
        canvas.drawColor(0xFFFFFFFF);
        canvas.save();
        canvas.rotate(90,getWidth()/2,getHeight()/2);
        canvas.drawLine(0,getHeight()/2,getWidth(),getHeight(),paint);
        canvas.restore();
        canvas.drawLine(0,getHeight()/2+100,getWidth(),getHeight()+100,paint);
        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        draw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}

//动画中的图形嵌套

//DrawRect.java

public class DrawRect extends CanvasContainer {

    private Paint paint = null;  //创建画笔

    public DrawRect() {
        paint = new Paint();
        paint.setColor(Color.RED);  //给画笔设置颜色
    }

    public void childrenView(Canvas canvas) {  //重载父类中的方法
        super.childrenView(canvas);
        canvas.drawRect(0,0,100,100,paint);  //画一个长方形
    }

}

//DrawCircle.java

public class DrawCircle extends CanvasContainer {

    private Paint paint = null;

    public DrawCircle() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
    }

    public void childrenView(Canvas canvas) {  
        super.childrenView(canvas);
        canvas.drawCircle(50,50,50,paint);  //画一个圆形
    }

}

//CanvasContainer.java

public class CanvasContainer {

    private List<CanvasContainer> children = null;  //创建canvas图像容器列表

    public CanvasContainer() {
        children = new ArrayList<CanvasContainer>();
    }

    public void draw(Canvas canvas) {
        childrenView(canvas);
        for(CanvasContainer c : children) {  //遍历canvas容器列表中的容器对象
            c.draw(canvas);   //给每个容器对象调用draw方法
        }
    }

    public void childrenView(Canvas canvas) {

    }

    public void addChildrenView(CanvasContainer child) {  //向canvas容器中添加容器对象
        children.add(child);
    }

    public void removeChildrenView(CanvasContainer child) {  //删除canvas容器中的对象
        children.remove(child);
    }

}

//GameView.java

public class GameView extends SurfaceView implements Callback {

    private CanvasContainer canvasContainer;
    private DrawRect drawRect;
    private DrawCircle drawCircle;

    public GameView(Context context) {
        super(context);
        canvasContainer = new CanvasContainer();  //创建canvas容器对象
        drawRect = new DrawRect();  //创建矩形对象
        drawCircle = new DrawCircle();  //创建圆对象
        drawRect.addChildrenView(drawCircle);  //向canvas图像容器中添加圆形视图
        canvasContainer.addChildrenView(drawRect);  //向图像容器中添加矩形视图
        getHolder().addCallback(this);  //添加回调函数
    }

    public void draw() {
        Canvas canvas = getHolder().lockCanvas();  //锁定canvas画布
        canvas.drawColor(Color.WHITE);  //设置canvas画布的背景色为白色
        canvasContainer.draw(canvas);  //调用canvas容器对象的画图方法
        getHolder().unlockCanvasAndPost(canvas);  //解锁canvas画布
    }

    @Override
    //在surfaceView初始化成功后调用surface创建成功后的回调函数
    public void surfaceCreated(SurfaceHolder holder) {
        draw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}

//CanvasActivity.java

public class CanvasActivity extends Activity {
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(new GameView(this));  //创建自定义视图
    }
}