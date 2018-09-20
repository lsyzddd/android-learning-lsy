/*
 * 获取到焦点后的事件
 **/

Button buttonDrag = (Button) findViewById(R.id.buttonMove);

buttonDrag.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View view, boolean b) {
        Log.d("myLog","button焦点事件");
    }
});


/**
  * 监听键盘的按键
  */

buttonDrag.setOnKeyListener(new View.OnKeyListener() {
    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        Log.d("myLog","keyCode");
        return false;
    }
});