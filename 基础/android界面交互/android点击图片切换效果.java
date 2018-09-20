/*
 * 点击图片切换
 */

// 简单切换

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ThreadActivity">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:src="@drawable/a4"/>

</RelativeLayout>

private int[] iamgeArray = new int[] {
    R.drawable.a1,
    R.drawable.a2,
    R.drawable.a3,
    R.drawable.a4,
};

private int count = 0;

ImageView imageView = (ImageView) findViewById(R.id.imageView);
imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ImageView v = (ImageView) view;
        v.setImageResource(iamgeArray[count++]);
    }
});

// 根据点击的范围进行图片切换

ImageView imageView = (ImageView) findViewById(R.id.imageView);

final int width = this.getWindowManager().getDefaultDisplay().getWidth();

imageView.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        ImageView v = (ImageView) view;
        if(x >= (width / 2)) {
            if(count == 3) {
                count = -1;
            }
            v.setImageResource(iamgeArray[++count]);
        }
        else {
            if(count == 0) {
                count =4;
            }
            v.setImageResource(iamgeArray[--count]);
        }
        return false;
    }
});