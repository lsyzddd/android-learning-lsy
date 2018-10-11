/*
 * android环境搭建注意事项
 **/

一.防止刚打开android studio时出现无法更新卡死的现象

配置方式：

1.打开file -- setting 搜索HTTP Proxy
2.选择Manual proxy configuration选项，在Host name中填写mirrors.neusoft.edu.cn
3.Port number填写80
4.Apply

二.防止刚打开android studio时下载gradle包出现bad request 400，需要进行如下配置

1.打开file -- setting 搜索Gradle
2.给Store generated project files externally打钩
3.选择Use local gradle distribution
4.在路径中选择编辑器安装路径下的gradle/gradle-4.4或者其它版本
5.Apply

三.防止android sdk自动更新

1.打开file -- setting 搜索Android SDK
2.选择SDK Update Sites
3.去除Offline Repo的打钩选项
4.Apply

/*
 * 初次配置完opencv环境并运行时修复一些错误
 **/

一.防止出现应该使用implementation和api替代import的错误提示

1.进入project -> app -> build.gradle的文件中
2.修改最后的节点dependencies
3.将最后的import project(path: ':openCVLibrary330')修改为implementation project(path: ':openCVLibrary330')
4.保存

二.防止修正完成上面的错误import android.hardware.camera2后出现提示该包不存在

1.点击file -> project structure -> opencvlibrary330 -> 
2.选择properties
3.修改compile sdk version 为21以上版本和 build tools version到更高版本
4.OK