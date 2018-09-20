/*
 * 多态性中的向下转型和向上转型
 **/

//对象的多态性
1.向上转型：程序会自动完成
  父类 父类对象 = 子类实例
2.向下转型
  子类 子类对象 = (子类) 父类实例

class A {
    public void tell1() {
        Log.d("myLog","A--tell1");
    }
    public void tell2() {
        Log.d("myLog","A--tell2");
    }
}

class B extends A {
    public void tell1() {
        Log.d("myLog","B--tell1");
    }
    public void tell3() {
        Log.d("myLog","B--tell3");
    }
}

//向上转型
B b = new B();
A a = b;
a.tell1();  //B--tell1
a.tell2();  //A--tell2

//向下转型
A a = new B();
B b = (B) a;
b.tell1();  //B--tell1
b.tell2();  //A--tell2
b.tell3();  //B--tell3

//多态性的应用

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
    say(new B());  //hello
}

public static void say(A a) {
    a.say();
}