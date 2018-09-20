/*
 * 按钮移动效果
 */

Button buttonDrag = (Button) findViewById(R.id.buttonMove);

/*
 * 横坐标向右移动150px
 */

TranslateAnimation animation = new TranslateAnimation(0,150,0,0);
animation.setRepeatCount(30);
animation.setDuration(2000);
buttonDrag.setAnimation(animation);
buttonDrag.startAnimation(animation);

/*
 * 多线程操作，主线程中按钮在不停地移动，自己的线程中修改按钮的文字。
 * 将我的线程中的任务添加到主线程，主线程读取线程中的任务并进行执行。
 */

// 方法1

Button buttonDrag = (Button) findViewById(R.id.buttonMove);
Button buttonThread = (Button) findViewById(R.id.buttonThread);

TranslateAnimation animation = new TranslateAnimation(0,150,0,0);
animation.setRepeatCount(30);
animation.setDuration(2000);
buttonDrag.setAnimation(animation);
buttonDrag.startAnimation(animation);

buttonThread.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(final View view) {
        Log.d("myLog","点击事件发生");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("myLog","线程沉睡5秒");
                    Thread.sleep(5000);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("myLog","修改按钮文字");
                        TextView buttonView = (TextView) view;
                        buttonView.setText(""+10);
                    }
                });
            }
        }).start();
    }
});

// 方法2

private Button buttonThread = null;

public void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    setContentView(R.layout.activity_thread);
    Button buttonDrag = (Button) findViewById(R.id.buttonMove);
    buttonThread = (Button) findViewById(R.id.buttonThread);
    TranslateAnimation animation = new TranslateAnimation(0,150,0,0);
    animation.setRepeatCount(30);
    animation.setDuration(2000);
    buttonDrag.setAnimation(animation);
    buttonDrag.startAnimation(animation);

    buttonThread.setOnClickListener(new View.OnClickListener() {
    	public void onClick(final View view) {
			new DownloadImageTask().execute();
    	}
    });
}


/*
 * private class MyTask extends AsyncTask<Params, Progress, Result> { ... }
 * MyTask task = new MyTask();
 * task.execute(Params...);
 * 
 * @params   执行任务时传入的参数类型
 * @Progress 后台操作执行过程中发回主线程中的阶段性结果返回类型
 * @Result   后台操作完全结束时返回给主线程的期待的返回类型
 *
 * onPreExecute() 在后台任务即 doInBackground(Params…) 执行前被调用，
 * 一般用于初始化某些值，例如可以是在交互页面上显示一个 ProgressBar 来提示将要进行后台任务

 * doInBackground(Params…) 它将在 onPreExecute() 被调用后马上调用，后台耗时操作真正是被放在这里执行。
 * 这边的可变参数 Params 正是 三个参数 中的第一个参数传入到这边的，另外会在任务执行结束后 return 第三个泛型参数 Result。
 * 过程中还可以调用 publishProgress(Progress…) 来发送返回阶段性结果，
 * 给处于主线程中调用的 onProgressUpdate(Progress…) 中使用.

 * onProgressUpdate(Progress…) 它会在执行 publishProgress(Progress…) 后被主线程调用起，
 * 这时仍在后台执行的任务会发回一个或多个阶段性进度结果，这个是可以用来去更新交互页面
 *
 * onPostExecute(Result)：当后台任务完全结束时会在主线程调用，这里的 Result 正是 doInBackground() 的返回值传入
 *
 */


private class DownloadImageTask extends AsyncTask<String,Void,Integer> {

	//在后台任务即 doInBackground(Params…) 执行前被调用(数据初始化)
	protected void onPreExecute() {

	}

	// 它将在 onPreExecute() 被调用后马上调用(线程任务)
    protected Integer doInBackground(String... urls) {
        try {
            Thread.sleep(5000);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        int sum = 10;
        return sum;
    }

    // 当后台任务完全结束时会在主线程调用(视图操作)
    protected void onPostExecute(Integer sum) {
        buttonThread.setText(""+sum);
    }

    //它会在执行 publishProgress(Progress…)后被主线程调用起(视图更新)
	protected void onProgressUpdate(Bitmap... values) {
	    
	}
}