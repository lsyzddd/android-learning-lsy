/*
 * 在界面上展示xml文件中的数据
 **/

// user.xml 如果标签体中的内容为空的话，需要把下面标签改为<user />的形式

<?xml version="1.0" encoding="utf-8" ?>
<resources>
    <user username="xiaogao" phone="123456" />
    <user username="laogao" phone="456789"/>
</resources>

final Button buttonDrag = (Button) findViewById(R.id.buttonMove);

buttonDrag.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String text = "";
        XmlResourceParser xrp = ThreadActivity.this.getResources().getXml(R.xml.users);  /*获取xml索引对象*/
        try {
            while(xrp.getEventType()!=XmlResourceParser.END_DOCUMENT) { /*是否检索到文档末尾*/
                if(xrp.getEventType() == XmlResourceParser.START_TAG) { /*是否检索到标签的开始*/
                    String tagname = xrp.getName();  /*获取标签名称*/
                    if(tagname.equals("user")) {
                        String uname = xrp.getAttributeValue(0);   /*第一个字段*/
                        Log.d("myLog","姓名:"+uname);
                        String phone = xrp.getAttributeValue(1);   /*第二个字段*/
                        Log.d("myLog","电话:"+phone);
                        text += "姓名:" + uname + ";电话" + phone + ";\n";
                    }
                }
                try {
                    xrp.next();  /*检索下一个*/
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(text);
    }
});