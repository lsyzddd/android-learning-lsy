/*
 * 简单的listview
 **/

// activity_listview.xml

//listView的几个属性  android:divider="颜色值", android:dividerHeight="分割线的高度"

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ListView
        android:id="@+id/listView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

</RelativeLayout>

private String[] ss = new String[]{
    "北京",
    "上海",
    "深圳",
    "广州",
    "北京",
    "上海",
    "深圳",
    "广州",
    "北京",
    "上海",
    "深圳",
    "广州",
    "北京",
    "上海",
    "深圳",
    "广州"
};

ListView listView = (ListView) findViewById(R.id.listView);
ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListViewActivity.this,android.R.layout.simple_list_item_1,ss);
listView.setAdapter(adapter);

/*
 * 在滚动区域中添加滚动的头部和尾部
 **/

ListView listView = (ListView) findViewById(R.id.listView);
ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListViewActivity.this,android.R.layout.simple_list_item_1,ss);
View scrollerHeader = getLayoutInflater().inflate(R.layout.activity_scrollerheader,null);  //创建滚动区域头部视图
View scrollerFooter = getLayoutInflater().inflate(R.layout.activity_scrollerfooter,null);  //创建滚动区域尾部视图
listView.addHeaderView(scrollerHeader);
listView.addFooterView(scrollerFooter);
listView.setAdapter(adapter);

/*
 * 给listview
 **/

private String[] ss = new String[]{
    "北京","上海","深圳","广州",
    "北京","上海","深圳","广州",
    "北京","上海","深圳","广州",
    "北京","上海","深圳","广州"
};

final ListView listView = (ListView) findViewById(R.id.listView);  //找到listview组件
//以ss字符串数组创建数据集
ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListViewActivity.this,android.R.layout.simple_list_item_1,ss);
//创建滚动头顶部的viewGroup
ViewGroup group = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_scrollerheader,null);
//获取到按钮组件
Button button = (Button) group.findViewById(R.id.headerButton);
//获取到文本编辑器
final EditText editText = (EditText) group.findViewById(R.id.headerEditText);
//给按钮创建点击事件
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String indexString = editText.getText().toString(); //获取到填写在文本编辑器中的文字那内容
        listView.setSelection(Integer.valueOf(indexString)); //将文本编辑器中的文字转换成整形数字,并将listview的位置定位到指定位置
    }
});

listView.addHeaderView(group); //给listView添加头部内容
listView.setAdapter(adapter);  //给listView设置数据集

/*
 * 使用BaseAdapter创建数据的ListView
 **/

// MyAdapter.java

public class MyAdapter extends BaseAdapter {
    private Context context;  //创建BaseAdapter上下文环境
    private String[] ss;  //创建存储数据的数组
    public MyAdapter(Context context, String[] ss) {
        super();
        this.context = context;
        this.ss = ss;
    }
    //getCount 方法获取数据的个数，这里返回多少就执行多少次的getView方法
    @Override
    public int getCount() {
        return ss.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    //getView 需要构建一个View的对象，来展示数据当中的对象，每次都将getView所获得的视图返回给listView的每一行
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        String str = ss[position];  //根据索引获取到当前的字符串
        TextView textView = new TextView(context);  //创建文本显示组件
        textView.setText(str);  //在文本组件中设置当前文字
        return textView;  //返回文本内容
    }
}


ListView listView = (ListView) findViewById(R.id.listView);  //获取listView组件
MyAdapter adapter = new MyAdapter(this,ss);  //创建adapter对象
listView.setAdapter(adapter);  //给listview设置adapter数据

/*
 * 比较复杂的，每行显示三个字段的listview
 **/

// activity_listview.xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <TextView
        android:id="@+id/textName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1"/>
    <TextView
        android:id="@+id/textPhone"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1"/>
    <TextView
        android:id="@+id/textAge"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1"/>
</LinearLayout>

// MyAdapter.java
public class MyAdapter extends BaseAdapter {
    private Context context;  //上下文
    private List<User> list;  //user列表
    public MyAdapter(Context context, List<User> list) {
        super();
        this.context = context;
        this.list = list;
    }
    //getCount 方法获取数据的个数
    @Override
    public int getCount() {
        return list.size();  //user列表的长度
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    //getView 需要构建一个View的对象，来展示数据当中的对象
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        User user = list.get(position);  //获取到第position个user对象
        //创建布局服务
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //创建group视图容器
        ViewGroup group = (ViewGroup) inflater.inflate(R.layout.user_item,null);
        //此处不能直接使用findViewById方法，因为无法在MyAdapter类中直接拿到布局，所以无法拿到视图
        TextView textView1 = (TextView) group.findViewById(R.id.textName);
        textView1.setText(user.getName());
        TextView textView2 = (TextView) group.findViewById(R.id.textPhone);
        textView2.setText(user.getPhone());
        TextView textView3 = (TextView) group.findViewById(R.id.textAge);
        textView3.setText(String.valueOf(user.getAge()));
        return group;  //返回视图到listview的每行当中去
    }
}

// User.java


//自定义一个User类，在类中创建需要的字段和构造方法进行默认赋值
//创建给字段赋值和获取字段对应值的方法
public class User {
    private String name;
    private String phone;
    private Integer age;

    public User(String name,String phone,Integer age) {
        super();
        this.name = name;
        this.phone = phone;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
//当前页面的java文件
private List<User> list = new ArrayList<User>();

ListView listView = (ListView) findViewById(R.id.listView);  //创建listView
for(int i = 0; i < 10; i++) { //通过10次循环，在每一次循环中，将user对象添加到list列表中
    User user = new User("gao"+i,"12313",33);
    list.add(user); //在list内部形成了list[0],list[1]等数组元素
}
// 给创建的listView视图设置数据集,MyAdapter内部将数据结构化
listView.setAdapter(new MyAdapter(this,list));

/*
 * listview中simpleAdapter的使用方法
 **/

//user_item.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <TextView
        android:id="@+id/textName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1"/>
    <TextView
        android:id="@+id/textPhone"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1"/>
    <TextView
        android:id="@+id/textAge"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1"/>
</LinearLayout>

ListView listView = (ListView) findViewById(R.id.listView);
/*
 * 第一个参数this,当前activity对象
 * 第二个参数data,固定格式的数据集合 List<Map<String,Object>>
 * 第三个参数resource,一个layout布局文件的索引
 * 第四个参数from,展示字段顺序的数组
 * 第五个参数to,按顺序容纳字段的布局组件数组
 **/

//map列表,其中列表中的每一项的key和value都是String类型
List<Map<String,String>> data = new ArrayList<Map<String,String>>();
for(int i = 0; i< 10; i++) {  //10次循环，每次循环都创建一个map对象，使用put方法给map对象添加字段
    Map<String,String> map = new HashMap<String,String>();
    map.put("name","gao"+i);
    map.put("phone","1234134421");
    map.put("age","123");
    data.add(map);  //给data列表添加map对象
}
//创建一个的简单的simpleAdapter对象
SimpleAdapter adapter = new SimpleAdapter(
    ListViewActivity.this,
    data,
    R.layout.user_item,
    new String[]{
        "age","phone","name"
    },
    new int[]{
        R.id.textName,
        R.id.textPhone,
        R.id.textAge
    }
);
listView.setAdapter(adapter);