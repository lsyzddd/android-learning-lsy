/*
 * android的fragment使用
 **/

1.fragment的创建

//activity_fragment.xml

<?xml version="1.0" encoding="utf-8"?>
<fragment xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/container"
    android:name="com.example.myandroid01.PlaceholderFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>

//FragmentActivity.java

public class FragmentActivity extends AppCompatActivity {
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_fragment);
        if(saveInstanceState == null) {
            getSupportFragmentManager().
                    beginTransaction().
                    add(R.id.container, new PlaceholderFragment()).
                    commit();
        }
    }
}

//PlaceholderFragment.java

public class PlaceholderFragment extends Fragment {
    public PlaceholderFragment() {

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fragment02, container, false);
        return rootView;
    }
}

//activity_fragment02.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="第一个fragment"/>

</RelativeLayout>

//结果在页面中展示的是一个textView，textView中展示的是第一个fragment

//动态切换fragment，默认展示的是替换成另一个fragment的按钮，点击后页面中的fragment的被替换成了另一个fragment

//activity_fragment.xml

<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.myandroid01.PlaceholderFragment">

</FrameLayout>

//fragment_activity.java

public class FragmentActivity extends AppCompatActivity {
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_fragment);
        if(saveInstanceState == null) {
            getSupportFragmentManager().
            beginTransaction().
            add(R.id.container, new PlaceholderFragment()).
            commit();
        }
    }
}

//activity_fragment02.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="替换成另一个fragment"/>

</RelativeLayout>

//PlaceholderFragment.java

public class PlaceholderFragment extends Fragment {
    public PlaceholderFragment() {

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fragment02, container, false);

        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.container,new FragmentAnother()).commit();
            }
        });

        return rootView;
    }
}

//activity_fragment03.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="这是第二个fragment"/>

</RelativeLayout>

//FragmentAnother.java

public class FragmentAnother extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_anotherfragment,container,false);
        return root;
    }
}

//给fragment设置后退效果

1.自带返回键的后退效果
addToBackStack(null)

2.点击按钮的后退效果
getFragmentManager().popBackStack();