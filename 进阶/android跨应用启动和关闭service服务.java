/*
 * 跨应用启动service服务
 **/

//第一个问价需要新建一个service服务的文件

intent = new Intent();
//	其中第一个参数是另一个应用的包名，第二个参数是另一个应用的service名称
intent.setComponent(new ComponentName("com.example.myandroid01","com.example.myandroid01.AppService"));
startService(intent);