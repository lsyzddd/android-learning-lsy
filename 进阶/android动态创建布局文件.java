/*
 * 动态创建布局文件
 **/

//动态创建线性布局

public class LineLayout extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout linearLayout;  //创建水平布局对象
    private Button button;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);  //设置布局方式为垂直布局
        setContentView(linearLayout);  //把linearLayout布局对象添加到视图中
        for(int i = 0; i < 5; i++) {
            button = new Button(this);
            button.setText("点我");
            button.setOnClickListener(this);
            //设置LinearLayout布局的参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            linearLayout.addView(button,params);  //向linearLayout视图中添加按钮
        }
    }

    @Override
    public void onClick(View v) {
        linearLayout.removeView(v);  //点击移除视图中的按钮
    }
}

//动态创建相对布局

super.onCreate(saveInstanceState);
relativeLayout = new RelativeLayout(this);
setContentView(relativeLayout);
RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
textView = new TextView(this);
textView.setText("极客学院");
relativeLayout.addView(textView,params);

