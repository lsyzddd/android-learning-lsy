/**
 * 按钮的点击事件
 */

// 方法1

<Button
    android:id="@+id/buttonMove"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="可拖拽的按钮"
    android:layout_centerInParent="true"
    android:onClick="buttonClick"/>

public void buttonClick(View view) {
    view.setX(view.getX() + 10);
}

// 方法2

Button buttonDrag = (Button) findViewById(R.id.buttonMove);

buttonDrag.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Log.d("myLog","点击事件发生");
        Toast.makeText(ThreadActivity.this,"点击事件发生",Toast.LENGTH_LONG).show();
    }
});

// 方法3

Button buttonDrag = (Button) findViewById(R.id.buttonMove);
buttonDrag.setOnClickListener(myListener);

private View.OnClickListener myListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.buttonMove:
                Toast.makeText(ThreadActivity.this,"拖拽按钮被点击了",Toast.LENGTH_LONG).show();
                break;
            case R.id.buttonThread:
                Toast.makeText(ThreadActivity.this,"线程按钮被点击",Toast.LENGTH_LONG).show();
                break;
        }
    }
};

/**
 * 按钮的长按事件
 */

Button buttonDrag = (Button) findViewById(R.id.buttonMove);

buttonDrag.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View view) {
        Log.d("myLog","长按事件发生");
        Toast.makeText(ThreadActivity.this,"长按事件发生",Toast.LENGTH_LONG).show();
        return true;   /*true表示事件出发后自动销毁，false表示事件发出后继续向下执行*/
    }
});

/**
 * 触摸事件
 */

import android.annotation.SuppressLint;

Button buttonDrag = (Button) findViewById(R.id.buttonMove);

buttonDrag.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int actionType = motionEvent.getAction();
        if(actionType == MotionEvent.ACTION_DOWN) {
            Log.d("myLog","按下");
        }
        else if(actionType == MotionEvent.ACTION_MOVE) {
            Log.d("myLog","移动");
        }
        else if(actionType == MotionEvent.ACTION_UP) {
            Log.d("myLog","松开");
        }
        return false;
    }
});

/**
  * 拖拽事件,按钮随着viewGroup的拖动而动
  */

<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ThreadActivity">

    <Button
        android:id="@+id/buttonMove"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="可拖拽的按钮"
        android:layout_centerInParent="true"/>

</RelativeLayout>

final Button buttonDrag = (Button) findViewById(R.id.buttonMove);
ViewGroup viewGroup = (ViewGroup) findViewById(R.id.layout);

viewGroup.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int actionType = motionEvent.getAction();
        if(actionType == MotionEvent.ACTION_DOWN) {
            Log.d("myLog","按下");
        }
        else if(actionType == MotionEvent.ACTION_MOVE) {
            Log.d("myLog","正在移动...");
            buttonDrag.setX(motionEvent.getX());
            buttonDrag.setY(motionEvent.getY());
        }
        else if(actionType == MotionEvent.ACTION_UP) {
            Log.d("myLog","弹起");
        }
        return true;
    }
});