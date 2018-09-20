/*
 * 不同的intent之间传递对象参数进行序列化操作
 **/

//系统自带的序列化方法

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private int age;
    public User(String name,int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}

//activity跳转并传递对象参数

Intent intent = new Intent(LearnActivity02.this,LearnActivity03.class);
intent.putExtra("user",new User("张三",20));
startActivity(intent);

//获取系统序列化后的对象参数

Intent intent = getIntent();
User user = (User) intent.getSerializableExtra("user");
TextView textView = (TextView) findViewById(R.id.textView);
textView.setText(String.format("name=%s,age=%d",user.getName(),user.getAge()));

//使用android提供的Parcelable方法进行序列化参数

public class User implements Parcelable {
    private String name;
    private int age;
    public User(String name,int age) {
        this.name = name;
        this.age = age;
    }

    protected User(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in.readString(),in.readInt());
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {  //先存储数据
        parcel.writeString(getName());
        parcel.writeInt(getAge());
    }
}

Intent intent = getIntent();
User user = (User) intent.getParcelableExtra("user");
TextView textView = (TextView) findViewById(R.id.textView);
textView.setText(String.format("name=%s,age=%d",user.getName(),user.getAge()));

//Parcelable方法中数据绑定的扩展方法

public static final Creator<User> CREATOR = new Creator<User>() {
    @Override
    public User createFromParcel(Parcel in) {
        Bundle bundle = in.readBundle();
        return new User(bundle.getString("name"),bundle.getInt("age"));
    }

    @Override
    public User[] newArray(int size) {
        return new User[size];
    }
};

public void writeToParcel(Parcel parcel, int i) {
    Bundle bundle = new Bundle();
    bundle.putString("name",getName());
    bundle.putInt("age",getAge());
    parcel.writeBundle(bundle);
}

//接收返回的activity所传回的参数

Intent intent = new Intent(LearnActivity02.this,LearnActivity03.class);
intent.putExtra("user",new User("张三",20));
startActivityForResult(intent,0);

protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    textView.setText(data.getStringExtra("text"));
    Log.d("myLog",Integer.toString(requestCode));
    Log.d("myLog",Integer.toString(resultCode));
}

Intent intent = new Intent();
intent.putExtra("text",editText.getText().toString());
setResult(1,intent);
finish();