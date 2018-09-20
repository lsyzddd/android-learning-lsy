/*
 * 获取单选选中的内容
 **/

<RadioGroup
    android:id="@+id/radioGroup"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content">
    <RadioButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="php"/>
    <RadioButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="java"/>
    <RadioButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="c#"/>
</RadioGroup>

RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(checkedId);
        String selectedText = (String) radioButton.getText();
        Toast.makeText(ThreadActivity.this,selectedText,Toast.LENGTH_SHORT).show();
    }
});

/*
 * 获取单选单选内容所对应的值
 **/

String selectedText = (String) radioButton.getTag();

/*
 * 获取多选按钮被选中的值和取消的值
 */

<CheckBox
    android:id="@+id/checkbox1"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="php"
    android:tag="1"/>

<CheckBox
    android:id="@+id/checkbox2"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_toRightOf="@+id/checkbox1"
    android:layout_marginLeft="10dp"
    android:text="java"
    android:tag="2"/>

<CheckBox
    android:id="@+id/checkbox3"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_toRightOf="@+id/checkbox2"
    android:layout_marginLeft="10dp"
    android:text="c#"
    android:tag="3"/>

CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkbox3);

checkBox1.setOnCheckedChangeListener(myListener);
checkBox2.setOnCheckedChangeListener(myListener);
checkBox3.setOnCheckedChangeListener(myListener);

private CheckBox.OnCheckedChangeListener myListener = new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(isChecked) {
            Toast.makeText(ThreadActivity.this,compoundButton.getText()+"被选中了",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ThreadActivity.this,compoundButton.getText()+"被取消了",Toast.LENGTH_SHORT).show();
        }
    }
};