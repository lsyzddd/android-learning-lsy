/*
 * 简单的时间和日期选择弹框
 **/

Button showDate = (Button) findViewById(R.id.showDate);
Button showTime = (Button) findViewById(R.id.showTime);
showDate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Calendar calendar = Calendar.getInstance();  //获得日期对象
        /* calendar.get(Calendar.YEAR)获取当前年份
         * calendar.get(Calendar.MONTH)获取当前月份
         * calendar.get(Calendar.DAY_OF_MONTH)获取当前日期
         **/
        //创建日期选择对话框
        DatePickerDialog datePickerDialog = new DatePickerDialog(DialogDateAndTimePicker.this, new DatePickerDialog.OnDateSetListener() {
            @Override   //日期设置发生改变
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Toast.makeText(DialogDateAndTimePicker.this,"您选择的日期:"+year+":"+(month+1)+":"+day,Toast.LENGTH_SHORT).show();
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        //展示日期选择对话框
        datePickerDialog.show();
    }
});
showTime.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Calendar calendar = Calendar.getInstance();
        //创建时间选择对话框
        TimePickerDialog timePickerDialog = new TimePickerDialog(DialogDateAndTimePicker.this, new TimePickerDialog.OnTimeSetListener() {
            @Override   //时间选择发生改变
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Toast.makeText(DialogDateAndTimePicker.this,"您选择的时间为:"+hour+":"+minute,Toast.LENGTH_SHORT).show();
            }
        },12,12,true);  //设置小时的值，设置分钟的值，是否按24小时格式显示数字
        timePickerDialog.show();
    }
});