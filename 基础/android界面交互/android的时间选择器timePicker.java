/*
 * 最简单基本的时间选择器
 **/

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TimePicker
        android:id="@+id/timePicker"
        android:layout_width="wrap_content"
        android:layout_height="match_parent" />

</RelativeLayout>

TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);  //获取时间选择器
timePicker.setIs24HourView(true);   //是否设置时间选择器为24小时制，默认为非24小时，也就是上午和下午的形式
timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {  //监听时间选择所发生的变化
    @Override
    public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
        Toast.makeText(TimepickerActivity.this,"您选择的日期为:"+hour+":"+minute,Toast.LENGTH_SHORT).show();

    }
});