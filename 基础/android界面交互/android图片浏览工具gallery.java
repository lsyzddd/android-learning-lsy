/*
 * gallery简单的图片浏览工具
 **/

// activity_gallery.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <Gallery
        android:id="@+id/gallery"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </Gallery>

</LinearLayout>

// GalleryActivity.java


private int[] images = new int[] { //创建图片列表
    R.drawable.pic1,
    R.drawable.pic2,
    R.drawable.pic3,
    R.drawable.pic4
};

Gallery gallery = (Gallery) findViewById(R.id.gallery);  //创建gallery画廊
MyAdapter adapter = new MyAdapter(images,this);  //创建数据集
gallery.setAdapter(adapter);  //给gallery画廊设置数据集

private class MyAdapter extends BaseAdapter {

    private int[] images;
    private Context context;

    public MyAdapter(int[] images,Context context) {
        super();
        this.images = images;
        this.context = context;
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
        ImageView imageView = new ImageView(context);  //创建图片视图
        imageView.setImageResource(images[position]);  //给图片视图设置图片内容
        return imageView;  //返回图片视图
    }
}

/*
 * 点击缩略图，切换大图
 **/

//activity_gallery.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:layout_weight="3"/>

    <Gallery
        android:id="@+id/gallery"
        android:layout_width="600dp"
        android:layout_height="200dp"
        android:layout_marginTop="0dp"
        android:padding="0dp"
        android:layout_weight="1"/>

</LinearLayout>

//GalleryActivity.java

private int[] images = new int[] {  //图片数组
    R.drawable.a1,
    R.drawable.a2,
    R.drawable.a3,
    R.drawable.a4
};

final ImageView imageView = (ImageView) findViewById(R.id.imageView);  //找到大图片的视图容器
Gallery gallery = (Gallery) findViewById(R.id.gallery);  //找到图片画廊组件
MyAdapter adapter = new MyAdapter(images,this);   //创建图片数据集
gallery.setAdapter(adapter);   //给画廊组件设置图片数据集
gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {  //给画廊组件添加点击事件
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        imageView.setImageResource(images[position]);  //通过点击动态设置大图的图片资源路径
    }
});

private class MyAdapter extends BaseAdapter {

    private int[] images;
    private Context context;

    public MyAdapter(int[] images,Context context) {
        super();
        this.images = images;
        this.context = context;
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
        ImageView imageView = new ImageView(context);   //创建图片视图
        imageView.setAdjustViewBounds(true);  //图片视图设置自适应视图绑定
        imageView.setLayoutParams(new Gallery.LayoutParams(200,200));  //给图片视图设置参数,参数中指定画廊组件的每张图片的长和宽
        imageView.setImageResource(images[position]);   //给图片视图设置图片地址
        return imageView;  //返回图片视图给gallery组件
    }
}