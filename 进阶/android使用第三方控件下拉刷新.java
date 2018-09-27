/*
 * 使用第三方控件Android-PullToRefresh，地址：https://github.com/chrisbanes/Android-PullToRefresh
 **/

1.在项目中new import module导入PullToRefresh
2.选择PullToRefresh中的library

//activity_pulltorefresh.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    //引入Android-PullToRefresh中的PullToRefreshListView组件
    <com.handmark.pulltorefresh.library.PullToRefreshListView
        android:id="@+id/pullToRefresh"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </com.handmark.pulltorefresh.library.PullToRefreshListView>

</RelativeLayout>

//PullToRefresh.java

public class PullToRefreshActivity extends AppCompatActivity {

    private PullToRefreshListView lv;
    private ArrayAdapter<String> adapter;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_pulltorefresh);
        lv = (PullToRefreshListView) findViewById(R.id.pullToRefresh);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[] {
            "数据项1",
            "数据项2",
            "数据项3"
        });
        lv.setAdapter(adapter);
    }
}

//下拉加载数据

public class PullToRefreshActivity extends AppCompatActivity {

    private PullToRefreshListView lv;
    private ArrayAdapter<String> adapter;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_pulltorefresh);
        lv = (PullToRefreshListView) findViewById(R.id.pullToRefresh);
        List<String> arr = new ArrayList<String>(); //字符串数组列表
        arr.add("数据项1");
        arr.add("数据项2");
        arr.add("数据项3");
        //创建数据适配器
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);
        //给ListView组件设置数据适配器的数据
        lv.setAdapter(adapter);
        //给ListView组件设置下拉刷新事件
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            //重写刷新事件
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                //创建异步任务
                new AsyncTask<Void,Void,Void>() {
                    @Override
                    //重写后台任务方法
                    protected Void doInBackground(Void... voids) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    //重写完成后台任务后的执行事件
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        adapter.addAll("hello","word");
                        //完成下拉刷新
                        lv.onRefreshComplete();
                    }
                }.execute();
            }
        });
    }
}