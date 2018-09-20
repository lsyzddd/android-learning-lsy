/*
 * 对java异常的认识
 **/

1.ArrayIndexOutOfBoundsExecption //数组越界异常
2.NumberFormatException  //数字格式化异常
3.AirthmeticException  //算数异常
4.NullPointerException  //空指针异常

class Exc {
    int a = 10;
    int b = 0;
}

public void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    setContentView(R.layout.activity_learnjava02);

    int temp = 0;
    Exc e = null;
    e = new Exc();
    try {
        temp = e.a / e.b;
        Log.d("myLog",Integer.toString(temp));
    }
    catch(NullPointerException error) {  //空指针异常
        Log.d("myLog","空指针异常:"+error.toString());
        //空指针异常:java.lang.NullPointerException: Attempt to read from field 'int com.example.myandroid01.LearnActivity02$Exc.a' on a null object reference
    }
    catch(ArithmeticException error) {  //算数异常
        Log.d("myLog","算数异常"+error.toString());
        //算数异常java.lang.ArithmeticException: divide by zero
    }
}

/*
 * 自定义错误类
 **/

class MyException extends Exception {
    MyException(String msg) {
        super(msg);
    }
}

public void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    setContentView(R.layout.activity_learnjava02);

    try {
        throw new MyException("自定义错误");
    } catch (MyException e) {
        Log.d("myLog",e.toString());
    }
}