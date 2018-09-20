/*
 * 界面上动态添加内容
 **/

// addView动态添加视图

LinearLayout linearLayout = new LinearLayout(this);
linearLayout.setOrientation(LinearLayout.VERTICAL);
LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
linearLayout.setLayoutParams(params);
TextView textView = new TextView(this);
textView.setText("点击添加");
textView.setTextSize(30);
textView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        TextView textView = new TextView(ThreadActivity.this);
        textView.setText("动态生成的内容");
        textView.setTextSize(20);
        parent.addView(textView);
    }
});
linearLayout.addView(textView);
this.setContentView(linearLayout);

/*
 * 动态删除生成的内容,
 **/

// removeView动态删除视图

// 在textView.setTextSize(20);和parent.addView(textView);之间加入如下代码

textView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        parent.removeView(view);
    }
});