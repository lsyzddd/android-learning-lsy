/*
 * ActionProvide的使用和注意事项
 **/

注意事项:
1.在样式文件中指定的父元素的主题必须是Theme.AppCompat
2.在AndroidManifest.xml文件中定义android:theme必须是AppTheme
3.menu_main.xml中actionProviderClass的值必须为android.support.v7.widget.ShareActionProvider
4.引入如下应用包：
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;

//使用ActionProvider在ActionBar的顶部创建一个简单的分享菜单

//style.xml

<resources>

    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>

    <style name="CustomActionBarOverlayTheme" parent="@android:style/Theme.Holo">
        <item name="android:windowActionBarOverlay">true</item>
    </style>

</resources>

//Androidmanifest.xml

<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.android06">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity" android:label="@string/app_name">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>

//main_activity.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:src="@drawable/background"
        android:contentDescription="@string/background_introduce"
        android:scaleType="fitXY"/>

</RelativeLayout>

//menu_main.xml

<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/action_share"
        android:title="@string/menu_share"
        app:actionProviderClass="android.support.v7.widget.ShareActionProvider"
        app:showAsAction="ifRoom" />
</menu>

//MainActivity.xml

public class MainActivity extends AppCompatActivity{

    private ShareActionProvider mShareActionProvider;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        if(mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(getDefaultIntent());
        }
        return true;
    }

    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        return intent;
    }

}