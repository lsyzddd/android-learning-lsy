/*
 * int类型装换成float类型精度丢失的问题
 **/

int i = 0x1000001; //其中十六进制的1000001代表28位超过了float类型的23位
float f = i;
Log.d("myLog",Integer.toBinaryString(i+":"+i));
Log.d("myLog",Integer.toBinaryString(f+":"+f));

1000000000000000000000001: 16777217
1000000000000000000000000: 1.6777216E7

/*
 * long类型转换成double类型精度丢失的问题
 **/

long l = 0x20000000000001l;
double d = l;
Log.d("myLog",Long.toBinaryString(l)+":"+l);
Log.d("myLog",Long.toBinartString((long)d)+":"+d);