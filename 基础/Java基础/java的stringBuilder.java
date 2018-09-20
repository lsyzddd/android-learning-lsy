/*
 * stringBuilder
 **/

1.在字符串缓冲区被单个线程使用的时候，建议优先考虑该类，速度比stringBuffer要快（仅限在单线程中使用）
2.如果涉及到线程安全方面，建议使用stringBuffer
3.常用方法：
  append()
  insert()