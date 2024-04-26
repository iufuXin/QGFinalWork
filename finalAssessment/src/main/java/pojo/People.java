package pojo;

public class People {
    //名字
    private String name;
    //个人描述
    private String desc;
    //老师编号或者学生学号
    private String id;
    //账号密码
    private String password;
    public People() {
    }
    public People(String name, String desc, String id, String password) {
        this.name = name;
        this.desc = desc;
        this.id = id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
