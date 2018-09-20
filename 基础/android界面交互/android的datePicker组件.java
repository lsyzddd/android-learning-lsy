/*
 * 最简单的datePicker展示
 **/

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <DatePicker
        android:id="@+id/datePicker"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:calendarViewShown="false"/>

</RelativeLayout>

Calendar calendar = Calendar.getInstance();  //获得当前日期对象
DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
//初始化日期选择器，给日期选择器设置当前的年月日，并且监听时间选择的变化
datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
    @Override
    public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
        Toast.makeText(DatepickerActivity.this,"您选择的日期为:"+year+":"+(month+1)+":"+day,Toast.LENGTH_SHORT).show();
    }
});