/*
 * 关于字符串的知识点
 **/

String str = "hello";
String str1 = new String("hello");
//==比较的是变量的地址，equals比较的是变量的内容
String isEqual = Boolean.toString(str == str1);  //false

1.字符串转换为数组  toCharArray()
2.从字符串中取出指定位置的字符  chatAt()
3.字符串与byte数组的转换
String str = "jikexueyuan";
byte bytes[] = str.getBytes();
for(int i = 0;i<bytes.length;i++) {
	System.out.printIn(new String(bytes));
}
4.过滤字符串中存在的字符 indexOf() //有的话返回当前字符的位置，没得话返回-1
5.trim 去掉字符串的前后空格
6.从字符串中取出子字符串 subString()
7.大小写转换  toLowerCase()  toUpperCase()
8.判断字符串的开头结尾字符  endsWidth()  startWidth()
9.替换字符串中的一个字符  replace()