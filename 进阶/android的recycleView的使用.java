/*
 * recycleView的使用以及扩展
 **/

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        recyclerView = new RecyclerView(this);
        setContentView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  //设置布局管理器
        recyclerView.setAdapter(new RecyclerView.Adapter() {  //给recycleView设置数据集

            private String[] data = new String[] {
                "北京",
                "上海",
                "广州",
                "深圳"
            };

            class ViewHolder extends RecyclerView.ViewHolder {

                private TextView tv;

                public ViewHolder(TextView itemView) {
                    super(itemView);
                    tv = itemView;
                }

                public TextView getTv() {
                    return tv;
                }
            }

            @NonNull
            @Override
            //映射item layout id，创建vh并返回
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                //对于getContext的解释：这个是View类中提供的方法，在继承了View的类中才可以调用，返回的是当前
                //View运行在哪个Activity Context
                return new ViewHolder(new TextView(viewGroup.getContext()));  //返回ViewHolder对象
            }
            //为holder设置指定数据
            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                ViewHolder vh = (ViewHolder) viewHolder;  //创建ViewHolder对象
                vh.getTv().setText(data[i]);  //给textView设置文本
            }
            //返回item的个数
            @Override
            public int getItemCount() {
                return data.length;
            }
        });
    }
}

/*
 * 使用资源文件自定义列表项
 **/

//recycleViewActivity

private RecyclerView recyclerView;

public void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    recyclerView = new RecyclerView(this);
    setContentView(recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(new MyAdapter());
}

//MyAdapter.java

class MyAdapter extends RecyclerView.Adapter {

    private CellData[] data = new CellData[] {
        new CellData("标题1","内容1"),
        new CellData("标题2","内容2")
    };

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_list_cell,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder vh = (ViewHolder) viewHolder;
        CellData cd = data[i];
        vh.getTvTitle().setText(cd.title);
        vh.tvContent.setText(cd.content);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private View root;
        TextView tvTitle,tvContent;

        public ViewHolder(View root) {
            super(root);
            tvTitle = (TextView) root.findViewById(R.id.title);
            tvContent = (TextView) root.findViewById(R.id.content);
        }

        public TextView getTvTitle() {
            return tvTitle;
        }

        public TextView getTvContent() {
            return tvContent;
        }

    }

}

//CellData.java

public class CellData {

    public String title;
    public String content;

    public CellData(String title,String content) {
        this.title = title;
        this.content = content;
    }
}

//修改为水平排布

recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

//修改为水平翻转排布

recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));

//修改为表格布局 n 列

recyclerView.setLayoutManager(new GridLayoutManager(this,3));