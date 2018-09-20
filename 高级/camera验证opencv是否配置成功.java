/*
 * 通过OpenCV库实现Camera渲染到手机屏幕
 **/

// AndroidManifest.xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.myandroid01">

    <uses-sdk
        android:minSdkVersion="8"
        android:targetSdkVersion="22" />

    <uses-permission android:name="android.permission.CAMERA"/>  //添加camera的权限
    <uses-feature android:name="android.hardware.camera"/>
    <uses-feature android:name="android.hardware.camera.autofocus"/>
    <uses-feature android:name="android.hardware.camera.front"/>
    <uses-feature android:name="android.hardware.camera.front.autofocus"/>

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme" >
        <activity
            android:name=".MainActivity"
            android:label="主界面">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>
    </application>

</manifest>

//activity_main.xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:opencv="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <org.opencv.android.JavaCameraView
        android:id="@+id/HelloOpenCvView"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:visibility="gone"
        opencv:camera_id="any"
        opencv:show_fps="true" />

</LinearLayout>

//MainActivity.java

private static final String TAG = "HelloOpenCVActivity";
CameraBridgeViewBase mOpenCvCameraView;

private LoaderCallbackInterface mLoaderCallback = new BaseLoaderCallback(this) {
    public void onManagerConnected(int status) {
        switch (status) {
            // OpenCV引擎初始化加载成功
            case LoaderCallbackInterface.SUCCESS:
                Log.i(TAG, "OpenCV loaded successfully.");
                // 连接到Camera
                mOpenCvCameraView.enableView();
                break;
            default:
                super.onManagerConnected(status);
                break;
        }
    }
};

//检查权限
public void checkPermission() {
	//判断是否有权限，如果版本大于5.1才需要判断（即6.0以上），其他则不需要判断
	//Build.VERSION.SDK_INT是指当前设备的API Level
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.CAMERA)) {

            }
            else {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},1);
            }
        }
    }
}

public void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    checkPermission();
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_main);
    mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.HelloOpenCvView);
    mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
    mOpenCvCameraView.setCvCameraViewListener(new CameraBridgeViewBase.CvCameraViewListener2() {

        @Override
        public void onCameraViewStarted(int width, int height) {

        }

        @Override
        public void onCameraViewStopped() {

        }

        @Override
        public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
            return inputFrame.rgba();
        }
    });
}

protected void onResume() {
    super.onResume();
    // OpenCVLoader.initDebug()静态加载OpenCV库
    // OpenCVLoader.initAsync()为动态加载OpenCV库，即需要安装OpenCV Manager
    if (!OpenCVLoader.initDebug()) {
        Log.w(TAG, "static loading library fail,Using Manager for initialization");
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_3_0, this, mLoaderCallback);
    } else {
        Log.w(TAG, "OpenCV library found inside package. Using it!");
        mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
    }
}

protected void onPause() {
    super.onPause();
    // 断开与Camera的连接
    if (mOpenCvCameraView != null) {
        mOpenCvCameraView.disableView();
    }
}

protected void onDestroy() {
    super.onDestroy();
    // 断开与Camera的连接
    if (mOpenCvCameraView != null) {
        mOpenCvCameraView.disableView();
    }
}