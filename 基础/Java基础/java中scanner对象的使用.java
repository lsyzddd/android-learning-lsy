/*
 * 控制台输入数据案例
 **/

//创建Scanner对象
Scanner scanner = new Scanner(System.in);
//向控制台输出文本
System.out.printIn("姓名:");
//接收输入的姓名字符串
String name = scanner.next();
//向控制台输出文本
System.out.printIn("性别:");
//接收输入的第一个字符
chat sex = scanner.next().charAt(0);
//向控制台输出文本
System.out.printIn("年龄:");
//接收输入的整数
int age = scanner.nextInt();
//向控制台输出文本
System.out.printIn("身高:");
//接收输入的双精度数
double height = scanner.nextDouble();
//向控制台输出文本
System.out.printIn("性格");
//接收输入的文本
String type = scanner.next();