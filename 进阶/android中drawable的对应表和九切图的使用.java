/*
 * drawable的对应表
 **/

QVGA   ldpi   320 * 240   4:3
HVGA   mdpi   480 * 320   4:3
WVGA   hdpi   800 * 480   5:3
FWVGA  hdpi   854 * 480   16:9
QHD    hdpi   960 * 540   16:9
720p   xhdpi  1280 * 720  16:9
1080   xxhdpi 1920 * 1080 16:9

//九切图工具的使用

该工具在android studio目录中的sdk目录下的tools文件夹中名称为draw9patch.bat，使用该工具可以选择图片放大的范围
该工具用于图片背景在被放大的情况下，绝不放大那些不该放大的像素部分，使得图片适用于不同的分辨率的App应用