/*
 * 视图动画效果
 **/

第一种方法实现透明度变化效果

//点击按钮让按钮的透明度从0变到1

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlphaAnimation aa = new AlphaAnimation(0,1);  //创建透明动画效果
        aa.setDuration(1000);  //设置动画事件间隔为1s
        v.startAnimation(aa);  //开启所点击界面视图的动画效果
    }
});

第二种方法实现透明度变化效果

//使用配置文件的方式来创建透明度变化效果

//alpha.xml透明度变化的配置文件

<?xml version="1.0" encoding="UTF-8" ?>
<alpha xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromAlpha="0"
    android:toAlpha="1"
    android:duration="1000">

</alpha>

//StartAnimationActivity.java

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    	//调用animation的工具类的加载动画方法
        v.startAnimation(AnimationUtils.loadAnimation(StartAnimationActivity.this,R.anim.alpha));
    }
});

第一种方法实现旋转动画效果

//旋转动画效果

public class StartAnimationActivity extends AppCompatActivity {

    private RotateAnimation ra;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_alphaanimation);
        ra = new RotateAnimation(0,360);
        //ra = new RotateAnimation(0,360,150,25);  创建旋转的坐标
        //以自身的中心点作为旋转中心
        //ra = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(1000);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(ra);
            }
        });
    }
}

第二种方法实现透明度变化效果

//rotate.xml旋转变化效果配置文件

<?xml version="1.0" encoding="utf-8"?>
<rotate xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromDegrees="0"
    android:toDegrees="360"
    android:duration="1000"
    android:pivotX="50%"
    android:pivotY="50%">

</rotate>

//StartRotateActivity.java

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(StartAnimationActivity.this,R.anim.rotate));
    }
});

//移动动画效果

第一种方法实现移动动画效果

//activity_translate.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="点击开始移动"/>

</RelativeLayout>

//StartTranslateActivity.java

public class StartAnimationActivity extends AppCompatActivity {

    private TranslateAnimation ta;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_translate);
        ta = new TranslateAnimation(0,200,0,200);  //创建移动动画对象，其中4个参数是移动的相对距离
        ta.setDuration(1000);  //设置动画时间
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(ta);  //开始视图动画
            }
        });
    }
}

第二种方法实现移动动画效果

//translate.xml 移动动画效果配置文件

<?xml version="1.0" encoding="utf-8"?>
<translate xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromXDelta="0"
    android:toXDelta="200"
    android:fromYDelta="0"
    android:toYDelta="200">

</translate>

//StartTranslateActivity.java

Button button = (Button) findViewById(R.id.button);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        v.setAnimation(AnimationUtils.loadAnimation(StartAnimationActivity.this,R.anim.translate));
    }
});

//缩放动画效果

第一种方法实现缩放动画效果

public class StartAnimationActivity extends AppCompatActivity {

    private ScaleAnimation sa;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_scale);
        sa = new ScaleAnimation(0,1,0,1);  //创建缩放动画对象
        sa.setDuration(1000);  //设置动画时间
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(sa);  //开启动画效果
            }
        });
    }
}

第二种方法实现缩放动画效果

//scale.xml

<?xml version="1.0" encoding="utf-8"?>
<scale xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromXScale="0"
    android:toXScale="1"
    android:fromYScale="0"
    android:toYScale="1"
    android:pivotX="50%"
    android:pivotY="50%"
    android:duration="1000">

</scale>

//StartScaleActivity.java

public class StartAnimationActivity extends AppCompatActivity {

    private ScaleAnimation sa;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_scale);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	//给视图设置动画，内置AnimationUtils.loadAnimation方法
                v.startAnimation(AnimationUtils.loadAnimation(StartAnimationActivity.this,R.anim.scale));
            }
        });
    }
}

//混合动画效果

第一种方法实现混合动画效果

public class StartAnimationActivity extends AppCompatActivity {

    private AnimationSet as;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_scale);
        as = new AnimationSet(true);  //创建动画设置，并设置补间动画效果
        as.setDuration(1000);  //设置动画时间
        AlphaAnimation aa = new AlphaAnimation(0,1);  //创建透明度效果动画对象
        aa.setDuration(1000);
        as.addAnimation(aa);  //把动画添加到动画设置中
        TranslateAnimation ta = new TranslateAnimation(0,200,0,200);  //创建移动动画效果对象
        ta.setDuration(1000);
        as.addAnimation(ta);  //把动画添加到动画设置中
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(as);  //视图开始动画效果
            }
        });
    }
}

第二种方法实现混合动画效果

//set.xml

<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:shareInterpolator="true"
    android:duration="1000">
    <alpha
        android:fromAlpha="0"
        android:toAlpha="1" />
    <translate
        android:fromXDelta="0"
        android:toXDelta="200"
        android:fromYDelta="0"
        android:toYDelta="200"/>
</set>

//StartAnimationActivity.java

public class StartAnimationActivity extends AppCompatActivity {

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_scale);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(StartAnimationActivity.this,R.anim.set));
            }
        });
    }
}

//设置动画事件监听

public class StartAnimationActivity extends AppCompatActivity {

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_scale);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(StartAnimationActivity.this,R.anim.set);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Toast.makeText(StartAnimationActivity.this,"动画结束",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                v.startAnimation(animation);
            }
        });
    }
}

//自定义动画效果

//activity_customanim.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="自定义动画"/>

</RelativeLayout>

//MyAnimation.java

public class MyAnimation extends Animation {

    @Override
    //在执行applyTransformation之前会先执行该方法
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    //在动画效果执行过程中会一直执行该方法
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        t.getMatrix().setTranslate(200 * interpolatedTime,200 * interpolatedTime);
        super.applyTransformation(interpolatedTime, t);
    }
}

//CustomAnimation.java

public class CustomAnimation extends AppCompatActivity {

    private MyAnimation ma;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activty_customanim);
        ma = new MyAnimation();
        ma.setDuration(1000);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(ma);
            }
        });
    }
}

//设置左右摇摆的动画效果

//activity_customanim.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_centerVertical="true"
        android:text="左右摆动"/>

</RelativeLayout>

//CustomAnimation.java

public class CustomAnimation extends AppCompatActivity {

    private MyAnimation ma;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activty_customanim);
        ma = new MyAnimation();
        ma.setDuration(1000);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(ma);
            }
        });
    }
}

//MyAnimation.java

public class MyAnimation extends Animation {

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        //使用正弦函数来控制位置的变化，角速度为interpolatedTime * 10，周期为2π/角速度，振幅为200
        t.getMatrix().setTranslate((float) (Math.sin(interpolatedTime * 10) * 200) ,0);
        super.applyTransformation(interpolatedTime, t);
    }
}