//声明HashMap的数组
ArrayList<HashMap<String, Object>> meumList = new ArrayList<HashMap<String, Object>>();

for(int i = 1;i < 10;i++)
{
	//声明hashmap的对象
    HashMap<String, Object> map = new HashMap<String, Object>();
    //添加hashmap对象的属性:ItemImage和ItemText
    map.put("ItemImage", R.drawable.ic_launcher_background);
    map.put("ItemText", ""+i);
    //向meumList数组中添加对象
    meumList.add(map);
}