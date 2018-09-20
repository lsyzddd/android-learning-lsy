/*
 * 最简单的进度条
 **/

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ProgressBar
        android:id="@+id/progressBar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        style="@android:style/Widget.ProgressBar.Horizontal"  //默认的水平的进度条样式
        android:max="100"
        android:progress="29"/>   //当前进度29%

</LinearLayout>

/*
 * 手动控制进度条的进度
 **/

//该进度条默认为无限循环动画的圆圈
<ProgressBar
    android:id="@+id/myProgressBar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    style="@android:style/Widget.ProgressBar.Horizontal"  //水平的进度条,并且设置最大进度和当前进度
    android:max="100"
    android:progress="29"/>

final ProgressBar progressBar = (ProgressBar) findViewById(R.id.myProgressBar);
Button addButton = (Button) findViewById(R.id.add);
Button reduceButton =  (Button) findViewById(R.id.reduce);
addButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        progressBar.incrementProgressBy(10); //给进度条设置相对进度，前进或者后退
    }
});
reduceButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        progressBar.incrementProgressBy(-10);
    }
});

/*
 * 在进度条线程之外创建新的线程,新的线程将在6s后使得进度条消失
 **/

<ProgressBar
    android:id="@+id/myProgressBar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>

<Button
    android:id="@+id/buttonStart"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="开始任务"
    android:layout_below="@+id/myProgressBar"
    android:layout_marginTop="20dp"
    android:layout_marginLeft="20dp"/>

//需要对进度条进行一些修改操作，所以需要final定义
final ProgressBar progressBar = (ProgressBar) findViewById(R.id.myProgressBar);
Button startMessionButton = (Button) findViewById(R.id.buttonStart);
startMessionButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(final View view) {
    	/*
    	 * 线程的规则是，1.不能阻塞主线程中的UI线程，所以必须创建新的线程。 2.不能在新创建的线程中操作原本的线程
    	 * 所以只能在新线程中调用官方的异步更新视图的方式----> view.post方法
    	 **/
    	//创建新的线程
        new Thread(new Runnable() {
            @Override
            //线程中的执行方法，必须用try和catch包围
            public void run() {
                try {
                    Log.d("myLog","线程开始睡眠6s");
                    Thread.sleep(6000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //异步更新视图
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("myLog","移除进度条");
                        ViewGroup group = (ViewGroup) progressBar.getParent();
                        group.removeView(progressBar);
                    }
                });
            }
        }).start(); //此处必须有start方法，否则该线程将无法启动
    }
});

/*
 * 使用handler进行多线程操作
 * handler必须在UI主线程中初始化完成
 **/

private Handler handler = new Handler();

final ProgressBar progressBar = (ProgressBar) findViewById(R.id.myProgressBar);
new Thread(new Runnable() {
    @Override
    public void run() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //handler的post方式和view.post相似
        handler.post(new Runnable() { //该方法中进行UI组件更新
            @Override
            public void run() {
                ViewGroup group = (ViewGroup) progressBar.getParent();
                group.removeView(progressBar);
                TextView textView = new TextView(MyProcess.this);
                textView.setText("下载成功");
                group.addView(textView);
            }
        });
    }
}).start();

/*
 * 自动增加进度条，然后当进度条100%时删除进度条
 **/

private Handler handler = new Handler();
private boolean flag = true;

final ProgressBar progressBar = (ProgressBar) findViewById(R.id.myProgressBar);
new Thread(new Runnable() {
    @Override
    public void run() {
        while(flag) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //每次循环都要判断是否更新视图,handler的post方法是异步执行的
            //因为handler的post方法是异步的，所以执行时慢于while循环，有可能post中的方法还没执行完，就又继续执行了while
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(progressBar.getProgress() == progressBar.getMax()) {
                        ViewGroup group = (ViewGroup) progressBar.getParent();
                        if(group!=null) {  //判断group指针是否为空
                            group.removeView(progressBar);
                            TextView textView = new TextView(MyProcess.this);
                            textView.setText("下载成功");
                            group.addView(textView);
                            flag = false;
                        }
                    }
                    progressBar.incrementProgressBy(10);
                }
            });
        }
    }
}).start();