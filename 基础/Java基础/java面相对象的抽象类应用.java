class Person {
    private String name;
    private int age;
    public Person(String name,int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}

class Student extends Person {
    private float score = 0.0f;
    public Student(String name,int age,float score) {
        super(name,age);
        this.score = score;
    }
    public float getScore() {
        return this.score;
    }
    public void setScore(float score) {
        this.score = score;
    }
}

class Worker extends Person {
    private float money = 0.0f;
    public Worker(String name,int age,float money) {
        super(name,age);
        this.money = money;
    }
    public float getMoney() {
        return this.money;
    }
    public void setMoney(float money) {
        this.money = money;
    }
}

public void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    setContentView(R.layout.activity_learnjava02);

    Student s = new Student("张三",20,100);
    Worker p = new Worker("李四",30,12000);
    Log.d("myLog","姓名:"+s.getName()+" 年龄:"+s.getName()+" 分数:"+s.getScore());
    Log.d("myLog","姓名:"+p.getName()+" 年龄:"+p.getAge()+" 工资:"+p.getMoney());
}