/*
 * 将n位小数转换成float类型的小数
 **/

TextView textView = (TextView) findViewById(R.id.textView);
String formula = "( sqrt(20) + sqrt(10) ) / ( sqrt(20) - sqrt(10) ) = ";
float result = (float) (( Math.sqrt(20) + Math.sqrt(10) ) / ( Math.sqrt(20) - Math.sqrt(10) ));
//先将小数扩大10倍，然后向下取整,
//Math.round()方法表示的是“四舍五入”的计算。 算法为Math.floor(f+0.5)，即将原来的数字加上0.5后再向下取整 
float transformResult = (float) (Math.round(result*10) / 10.0); //整数除以10.0后将转换为浮点数
textView.setText(formula + transformResult);