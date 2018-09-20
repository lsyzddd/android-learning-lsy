/*
 * EditText的一些属性和方法
 **/

android:ems //限制中文汉字的格式，一个汉字4个字节
android:maxLength //限制输入框输入内容的最大长度
android:singleLine //是否限制单行展示
android:hint //在没填写任何内容时文字提示
android:textColorHint //在没填写任何内容时文字提示所展示的颜色
android:digits //只允许输入的内容
android:numeric // 1.Integer(整数), 2.signed(负数) 3.decimal(小数)

/*
 * 监听编辑器的输入内容变化
 **/

TextView editText = (TextView) findViewById(R.id.editText);

editText.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable text) {
        String inputText = text.toString();
        int index = inputText.indexOf("4");
        if(index!=-1) {
            inputText = inputText.substring(0,index);
        }
        Toast.makeText(ThreadActivity.this,inputText,Toast.LENGTH_SHORT).show();
    }
});

/*
 * 编辑器的过滤器
 */

TextView editText = (TextView) findViewById(R.id.editText);

editText.setFilters(new InputFilter[]{
    new InputFilter.AllCaps(),  /*自动转换为大写*/
    new InputFilter.LengthFilter(10), /*只限定输入10个字符*/
    new InputFilter() {
        @Override   //charSequence是当前新输入的内容, spanned是原先已经输入的内容
        public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
            Toast.makeText(ThreadActivity.this,"现在的输入内容:"+charSequence+"原先的输入内容:"+spanned,Toast.LENGTH_SHORT).show();
            return null;
        }
    }
});

/*
 * 过滤指定字符，使得指定字符不在编辑框中展示
 **/

editText.setFilters(new InputFilter[]{
        new InputFilter.AllCaps(),
        new InputFilter.LengthFilter(10),
        new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                if(charSequence.toString().indexOf("4")!=-1) {
                    return "";   /*过滤字符则返回为空*/
                }
                else {
                    return null;  /*不进行过滤则返回为null*/
                }
            }
        }
});