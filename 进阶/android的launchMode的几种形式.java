/*
 * launchModel的几种形式
 **/

1.standard模式  //该模式处在默认状态下

2.singleTask模式  //该模式下的taskId不会变

3.singleTop模式  //如果处于栈顶的状态下，不会开辟新的activity

4.singleInstance模式  //独立的任务栈，只放了一个实例

//网上对于lauchModel的理解

//第一种理解

a），如果两个activity(A和B）都是standard模式，并且都是以start activity的方式开启（即A里边用intent开启B，
	或者B里边用intent开启A），则会不断的在同一个栈中生成A和B的实例，按back键的时候则是按生成顺序的反向一个个退栈。

b），如果A是standard，B是single top，这个也比较好理解，类似上一个，区别是，当A或B在栈顶部的时候，
在开启同一个activity，此时不会创建新的实例，而是复用已存在的顶部那个activity。

c），如果A和B是standard，在创建一个activity ，名字叫C，启动模式single task：A里启动C，然后C里启动B，
此时它们还是再同一个栈里。因为C是single task，栈里只能有一个C的实例，如果再启动C的话，系统会把C上边的所有activity退栈，
即销毁C上的所有activity，然后复用C。

d），这个比较复杂了，同样A和B是standard，C是single instance，A中开启C，此时C会开启一个新任务栈，假设A的任务栈是Task1，
C的任务栈是Task2。此时若C再开启B的话，B会在Task1中创建自己的实例（利用adb命令获得的图1）看Runing activities下的内容，
发现，A和B的栈id为73，C的栈id是74。再者，在B中再次开启C，虽然C是single instance，也不创建新的实例，
而是复用Task2中已存在的C(图2)，C没生成新的栈，栈id也未变。

//第二种理解

standard:
standard模式，也就是默认模式，不需要配置launchMode。就是每次跳转时都需要创建该Activity的新实例，点击返回键，
可以看到的是按照刚才创建Activity实例的倒序依次出现，类似退栈的操作，而刚才跳转的操作就是压栈的操作。

singleTop:
singleTop和standard模式，都会将intent发送新的实例（后两种模式不发送到新的实例，如果已经有了的话）。
不 过，singleTop要求如果创建intent的时候栈顶已经有要创建的Activity的实例，则将intent发送给该实例，而不发送给新的实例。
1、如果要跳转的Activity位于栈顶，则不创建新实例，否则创建新的实例位于栈顶。

singleTask:
singleTask模式和后面的singleInstance模式都是只创建一个实例的。
当intent到来，需要创建singleTask模式Activity的时候，系统会检查栈里面是否已经有该Activity的实例。如果有直接将intent发送给它。
当一个Activity被设置为singletask时
（1）它并没有创建新的任务栈（TaskId都是一样的）
（2）如果它已经存在于栈中，再次请求触发此Activity时，会调用此类实例的onNewIntent方法，不会重新创建新的实例
（3）如果此类所在的任务栈上面有其它Activity，那么其它的Activity会被销毁**

singleInstance:
当一个Activity被设置为singleinstance时
（1）如果此Activity没有实例，它会创建一个新的任务栈。（两个TaskId不一样）
（2）如果任务栈中已经有此实例，会调用onNewIntent方法，不会创建新的任务栈和实例
（3）独立成Taskstack ，并且有且仅有你自己 。而且始终保持一个实例离栈的时候 ， 先退当前的task ，再退另外的task