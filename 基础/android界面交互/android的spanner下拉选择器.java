/*
 * 下拉选项从本地读取,并且以弹框的方式展示
 **/

<string-array name="spinner_data">
    <item>北京</item>
    <item>上海</item>
    <item>深圳</item>
</string-array>

<Spinner
    android:id="@+id/spinner"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:entries="@array/spinner_data"
    android:spinnerMode="dialog"/>

/*
 * 使用BaseAdapter的方式在spinner中添加数据
 **/

private String[] ss = new String[]{
    "北京1",
    "上海1",
    "深圳1"
};

Spinner spinner = (Spinner) findViewById(R.id.spinner);

BaseAdapter adapter = new MyAdapter();
spinner.setAdapter(adapter);

private class MyAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return ss.length;   /*这里返回多少,getView就执行多少次*/
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
    public View getView(int position, View view, ViewGroup viewGroup) {  /*position代表ss的指针位置*/
        TextView textView = new TextView(ThreadActivity.this);
        textView.setText(ss[position]);
        return textView;
    }
}

/*
 * 使用list的方式，在spinner中添加数据
 **/

private List<String> list = new ArrayList<String>();
list.add("北京3");
list.add("上海3");
list.add("深圳3");
Spinner spinner = (Spinner) findViewById(R.id.spinner);
BaseAdapter adapter = new MyAdapter();
spinner.setAdapter(adapter);

private class MyAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return list.size();
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
        TextView textView = new TextView(ThreadActivity.this);
        textView.setText(list.get(position));
        return textView;
    }
}

/*
 * 使用ArrayAdapter的方式向spinner中添加数据,并将spinner的弹窗样式修改为系统的默认样式
 **/

// android.R.layout.simple_spinner_item系统默认的spinner样式

private List<String> list = new ArrayList<String>();

list.add("北京3");
list.add("上海3");
list.add("深圳3");
Spinner spinner = (Spinner) findViewById(R.id.spinner);
ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ss);
spinner.setAdapter(adapter);

/*
 * 下拉选择发生变化的事件
 **/

private List<String> list = new ArrayList<String>();

list.add("北京3");
list.add("上海3");
list.add("深圳3");
Spinner spinner = (Spinner) findViewById(R.id.spinner);
BaseAdapter adapter = new MyAdapter();
spinner.setAdapter(adapter);
spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(ThreadActivity.this,list.get(position),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
});


private class MyAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return list.size();
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
        TextView textView = new TextView(ThreadActivity.this);
        textView.setText(list.get(position));
        return textView;
    }
};