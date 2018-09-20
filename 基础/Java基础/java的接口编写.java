/*
 * 简单的接口编写
 **/

interface USB {
    void start();
    void stop();
}

static class COMPUTER {
    public static void work(USB u) {
        u.start();
        Log.d("myLog","工作中");
        u.stop();
    }
}

class USBDisk implements USB {
    public void start() {
        Log.d("myLog","开始工作");
    }
    public void stop() {
        Log.d("myLog","结束工作");
    }
}

class Printer implements USB {
    public void start() {
        Log.d("myLog","开始打印");
    }
    public void stop() {
        Log.d("myLog","结束打印");
    }
}

public void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    setContentView(R.layout.activity_learnjava02);

    COMPUTER.work(new USBDisk());
    COMPUTER.work(new Printer());
}