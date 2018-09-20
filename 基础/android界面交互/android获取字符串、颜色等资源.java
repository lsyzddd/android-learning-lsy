/*
 * android获取颜色资源
 **/

int color = ContextCompat.getColor(ThreadActivity.this,R.color.orange);

Toast.makeText(ThreadActivity.this,""+color,Toast.LENGTH_LONG).show();

/*
 * android动态设置当前activity的背景色,当前的activity名称是ThreadActivity
 */

ThreadActivity.this.getWindow().setBackgroundDrawableResource(R.color.orange);

/*
 * 获取字符串资源，修改按钮文字颜色,其中的buttonDrag必须是final类型的
 **/

String str = ThreadActivity.this.getString(R.string.welcome);
Toast.makeText(ThreadActivity.this,str,Toast.LENGTH_LONG).show();
buttonDrag.setText(R.string.welcome);

/*
 * 在dimens下添加资源,用dimens下的资源给按钮设置宽度和高度
 **/

<?xml version="1.0" encoding="utf-8"?>
<resources>
    <dimen name="text_width">150px</dimen>
    <dimen name="text_height">100px</dimen>
    <dimen name="button_width">30mm</dimen>
    <dimen name="button_height">10mm</dimen>
</resources>

final Button buttonDrag = (Button) findViewById(R.id.buttonMove);

buttonDrag.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        float width = ThreadActivity.this.getResources().getDimension(R.dimen.text_width);
        float height = ThreadActivity.this.getResources().getDimension(R.dimen.text_height);
        buttonDrag.setWidth((int)width);
        buttonDrag.setHeight((int)height);

    }
});