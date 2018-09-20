/*
 * instanceof的简单使用
 **/

class A {
    public void say() {
        Log.d("myLog","hello");
    }
}

class B extends A {
    public void say1() {

    }
}

class C extends A {
    public void say2() {

    }
}

class D extends A {
    public void say3() {

    }
}

public void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    setContentView(R.layout.activity_learnjava02);
    A a = new A();
    Log.d("myLog",Boolean.toString(a instanceof A));  //true
    Log.d("myLog",Boolean.toString(a instanceof B));  //false

    A a1 = new B();   //向上转型
    Log.d("myLog",Boolean.toString(a1 instanceof A));  //true
    Log.d("myLog",Boolean.toString(a1 instanceof B));  //true
}