/*
 * android studio中无法添加library dependencies的解决办法
 **/

当添加library dependencies时会出现：The following packages are not available，Package id extras;android;m2repository

解决的办法是如果设置了代理把代理关掉：setting -> proxy,这里勾选no proxy。

如果代理关掉了的情况下还是出现这种情况：在 项目名称\app\build.gradle中

compile 'com.android.support.constraint:constraint-layout:1.0.0' 改为：

compile 'com.android.support.constraint:constraint-layout:1.0.2'