/*
 * frameLayout的使用，简单实现点击图片切换的效果
 **/

//activity_frameLayout.xml

<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.myandroid03.FrameLayout"
    android:id="@+id/root">

    <ImageView
        android:id="@+id/imageView1"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:src="@drawable/pic1"/>

    <ImageView
        android:id="@+id/imageView2"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:src="@drawable/pic2"
        android:visibility="invisible" />

</FrameLayout>

//frameLayout.java

private ImageView pic1;
private ImageView pic2;

public void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    setContentView(R.layout.activity_framelayout);

    pic1 = (ImageView) findViewById(R.id.imageView1);
    pic2 = (ImageView) findViewById(R.id.imageView2);

    findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(pic1.getVisibility() == View.VISIBLE) {
                showB();
            }
            else {
                showA();
            }
        }
    });
}

public void showA() {
    pic1.setVisibility(View.VISIBLE);  //设置可见
    pic2.setVisibility(View.INVISIBLE); //设置不可见
}

public void showB() {
    pic1.setVisibility(View.INVISIBLE);
    pic2.setVisibility(View.VISIBLE);
}