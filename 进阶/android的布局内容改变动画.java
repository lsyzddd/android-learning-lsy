/*
 * 布局内容改变的动画效果
 **/

//activity_fragment.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/rootView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:animateLayoutChanges="true"> //设置布局改变的动画效果

</LinearLayout>

//menu中的main.xml

<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/action_settings"
        android:orderInCategory="100"
        android:title="@string/action_settings"
        app:showAsAction="never" />
    <item
        android:id="@+id/action_add"
        android:title="@string/menu_item_add"
        app:showAsAction="ifRoom"
        android:icon="@android:drawable/ic_input_add" />
</menu>


//LayoutAnimation.java

public class LayoutAnimation extends AppCompatActivity {

    private LinearLayout linearLayout;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_fragment);
        linearLayout = (LinearLayout) findViewById(R.id.rootView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    private void addButton() {
        Button addButton = new Button(this);
        addButton.setText("删除我");
        linearLayout.addView(addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeView(v);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_add:
                addButton();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

//PlaceholderFragment.java

public class LayoutAnimation extends AppCompatActivity {

    private LinearLayout linearLayout;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_fragment);
        linearLayout = (LinearLayout) findViewById(R.id.rootView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    private void addButton() {
        Button addButton = new Button(this);
        addButton.setText("删除我");
        linearLayout.addView(addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeView(v);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_add:
                addButton();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}