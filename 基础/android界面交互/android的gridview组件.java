/*
 * gridview图片列表展示
 **/

//创建图片数组
private int[] images = new int[] {
    R.drawable.a1,
    R.drawable.a2,
    R.drawable.a3,
    R.drawable.a4,
    R.drawable.a1,
    R.drawable.a2,
    R.drawable.a3,
    R.drawable.a4,
    R.drawable.a1,
    R.drawable.a2,
    R.drawable.a3,
    R.drawable.a4
};

//数据集
public class MyAdapter extends BaseAdapter {

    private Context context;
    private int[] images;

    public MyAdapter(Context context,int[] images) {
        super();
        this.context = context;
        this.images = images;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        int imageId = images[position];  //获取到图片的路径
        ImageView imageView = new ImageView(context);  //创建图片视图容器
        imageView.setImageResource(imageId);  //给照片视图容器设置图片路径
        return imageView;
    }
}

//网格视图
GridView gridView = (GridView) findViewById(R.id.gridView);
//给网格视图设置数据集
gridView.setAdapter(new MyAdapter(this,images));

/*
 * gridView图片文字混合排布
 **/

//创建图片列表
private int[] images = new int[] {
    R.drawable.a1,
    R.drawable.a2,
    R.drawable.a3,
    R.drawable.a4,
    R.drawable.a1,
    R.drawable.a2,
    R.drawable.a3,
    R.drawable.a4,
    R.drawable.a1,
    R.drawable.a2,
    R.drawable.a3,
    R.drawable.a4
};

//创建图片下对应的文字列表
private String[] bookNames = new String[] {
    "java",
    "php",
    "c#",
    "python",
    "java",
    "php",
    "c#",
    "python",
    "java",
    "php",
    "c#",
    "python"
};

GridView gridView = (GridView) findViewById(R.id.gridView);  //创建网格视图
gridView.setAdapter(new MyAdapter(this,images,bookNames));  //给网格视图设置数据集
//给网格视图的每一块都创建一个点击事件
gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(ListViewActivity.this,"您选择了:"+bookNames[i],Toast.LENGTH_SHORT).show();
    }
});

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int[] images;
    private String[] bookNames;

    public MyAdapter(Context context,int[] images,String[] bookNames) {
        super();
        this.context = context;
        this.images = images;
        this.bookNames = bookNames;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        int imageId = images[position];  //获得图片资源路径
        String bookName = bookNames[position];  //获取图片列表下对应的文字名称
        ViewGroup group = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_listitem,null);  //创建网格视图
        ImageView imageView = (ImageView) group.findViewById(R.id.imageView);  //创建图片视图
        TextView textView = (TextView) group.findViewById(R.id.textView);  //创建文字视图
        imageView.setImageResource(imageId);  //给图片视图设置对应的图片
        textView.setText(bookName);  //给文字视图设置对应的文字
        return group;  //返回网格视图
    }
}