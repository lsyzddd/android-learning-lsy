//application的onCreate函数比主页面的onCreate函数先执行

public class App extends Application {
    public String textData = "default";
    public void setTextData(String textData) {
        this.textData = textData;
    }
    public String getTextData() {
        return this.textData;
    }
    public void onCreate() {
        super.onCreate();
        Log.d("myLog","application创建");
    }
    //结束，一般在模拟环境下执行
    public void onTerminate() {
        super.onTerminate();
        Log.d("myLog","application结束");
    }
    //在低内存的环境下执行
    public void onLowMemory() {
        super.onLowMemory();
    }
    //在程序内存清理时执行
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
    //在配置发生改变时执行
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}