/*
 * 抽象类和接口
 **/

1.接口是java中最重要的概念，接口可以理解为一种特殊的类，里面全部是由全局常量和公共的抽象方法所组成
2.接口的格式:
interface interfaceName {
	全局常亮
	抽象方法
}
3.接口的实现也必须通过子类，使用关键字implements，而且接口是可以多实现的
4.一个子类可以同时继承抽象类和实现接口
5.一个接口不能继承一个抽象类，但是可以通过extends关键字同时继承多个接口，实现接口的多继承

//抽象类接口实现的例子

interface Inter1 {
    public static final int AGE = 100;
    public abstract void tell();
}

interface Inter2 {
    public abstract void say();
}

interface Inter3 extends Inter1,Inter2 {
    
}

abstract class Abs1 {
    public abstract void print();
}

class A extends Abs1 implements Inter1,Inter2 {
    public void tell() {

    }
    public void say() {

    }
    public void print() {

    }
}

public void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    setContentView(R.layout.activity_learnjava02);
    TextView textView = (TextView) findViewById(R.id.textView);
    A a = new A();
    a.tell();
    a.say();
    a.print();
    String myAge = Integer.toString(Inter1.AGE);
    textView.setText(myAge);
}