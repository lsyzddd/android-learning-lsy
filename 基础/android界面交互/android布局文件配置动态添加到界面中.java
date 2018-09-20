/*
 * 根据布局文件配置动态生成内容
 **/

LinearLayout linearLayout = new LinearLayout(this);
linearLayout.setOrientation(LinearLayout.VERTICAL);
LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
linearLayout.setLayoutParams(params);
LayoutInflater inflater = getLayoutInflater();
View view1 = inflater.inflate(R.layout.module,null);
View view2 = inflater.inflate(R.layout.module,null);
linearLayout.addView(view1);
linearLayout.addView(view2);
setContentView(linearLayout);