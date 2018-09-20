/*
 * android的权限管理
 **/

//上网权限

<uses-permission android:name="android.permission.INTERNET" />

WebView wv = (WebView) findViewById(R.id.wv);
wv.loadUrl("http://www.baidu.com");

//给某个方法设定权限

<permission android:name="com.example.myandroid01.permission.SAY_HELLO"/>  //声明权限
<uses-permission android:name="com.example.myandroid01.permission.SAY_HELLO" />  //使用权限

Hello.sayHello(this);

public class Hello {
    public static final String PERMISSION_SAY_HELLO = "com.example.myandroid01.permission.SAY_HELLO";
    public static void sayHello(Context context) {
        int checkResult = context.checkCallingOrSelfPermission(PERMISSION_SAY_HELLO);  //获取是否拥有权限的返回值
        if(checkResult != PackageManager.PERMISSION_GRANTED) {  //判断权限是否存在
            throw new SecurityException("执行该方法需要有权限");
        }
    }
}

//为基本组件添加权限

