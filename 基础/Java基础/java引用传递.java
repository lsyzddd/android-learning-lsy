/*
 * 引用传递
 **/

//改变对象的整形属性值

class Ref1 {
	int temp = 10;
}

public class RefDemo01 {
	public static void main(String args[]) {
		Ref1 r1 = new Ref1();
		r1.temp = 20;
		Syetem.out.printIn(r1.temp);  //20
		tell(r1);
		System.out.printIn(r1.temp);  //30
	}
	public static void tell(Ref1 r2) {
		r2.temp = 30;
	}
}

//改变字符串变量

public class RefDemo02 {
	public static void main(String[] args) {
		String str1 = "Hello";
		System.out.printIn(str1);  //Hello
		tell(str1);
		System.out.printIn(str1);  //Hello
	}
	public static void tell(String str2) {
		str2 = "jike";
	} 
}

//改变对象的字符串属性值

class Ref2 {
	String temp = "hello";
}

public class RefDemo03 {
	public static void main(String args[]) {
		Ref2 r1 = new Ref2();
		r1.temp = "jike";
		System.out.printIn(r1.temp);  //jike
		tell(r1);
		Ststem.out.printIn(r1.temp);  //xueyuan
	}
	public static void tell(Ref2 r2) {
		r2.temp = "xueyuan";
	}
}