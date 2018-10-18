/*
 * 使用surface的回调巧妙的创建动画效果
 **/

//DrawRect.java

public class DrawRect extends CanvasContainer {

    private Paint paint = null;

    public DrawRect() {
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    public void childrenView(Canvas canvas) {
        super.childrenView(canvas);
        canvas.drawRect(0,0,100,100,paint);
        this.setY(this.getY() + 1);
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
        canvas.drawCircle(50,50,50,paint);
    }

}

//CanvasContainer.java

public class CanvasContainer {

    private List<CanvasContainer> children = null;
    private float x = 0,y = 0;

    public CanvasContainer() {
        children = new ArrayList<CanvasContainer>();
    }

    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(getX(),getY());
        childrenView(canvas);
        for(CanvasContainer c : children) {
            c.draw(canvas);
        }
        canvas.restore();
    }

    public void childrenView(Canvas canvas) {

    }

    public void addChildrenView(CanvasContainer child) {
        children.add(child);
    }

    public void removeChildrenView(CanvasContainer child) {
        children.remove(child);
    }

    public float getX() {  //获取x坐标
        return x;
    }

    public float getY() {  //获取y坐标
        return y;
    }

    public void setX(float x) {  //设置x坐标
        this.x = x;
    }

    public void setY(float y) {  //设置y坐标
        this.y = y;
    }

}

//GameView.java

import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Callback {

    private CanvasContainer canvasContainer;
    private DrawRect drawRect;
    private DrawCircle drawCircle;
    private Timer timer = null;
    private TimerTask task = null;

    public GameView(Context context) {
        super(context);
        canvasContainer = new CanvasContainer();
        drawRect = new DrawRect();
        drawCircle = new DrawCircle();
        drawRect.addChildrenView(drawCircle);
        canvasContainer.addChildrenView(drawRect);
        getHolder().addCallback(this);
    }

    public void draw() {
        Canvas canvas = getHolder().lockCanvas();
        canvas.drawColor(Color.WHITE);
        canvasContainer.draw(canvas);
        getHolder().unlockCanvasAndPost(canvas);
    }

    public void startTimer() {
        timer = new Timer();  //创建时间对象
        task = new TimerTask() {  //创建时间间隔任务
            @Override
            public void run() {  //重写run方法,run方法的内部是具体执行的内容
                draw();
            }
        };
        timer.schedule(task,100,100);  //在100ms后开启每隔100ms执行一次的任务
    }

    public void stopTimer() {
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    //在surface创建后开始时间间隔函数
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startTimer();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    //在surface销毁后停止时间间隔函数
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopTimer();
    }
}

//CanvasActivity.java

public class CanvasActivity extends Activity {
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(new GameView(this));
    }
}