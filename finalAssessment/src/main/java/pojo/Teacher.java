package pojo;

public class Teacher extends People{
    private String email;
    private String qq;

    public Teacher() {
    }
    public Teacher(String email, String qq) {
        this.email = email;
        this.qq = qq;
    }
    public Teacher(String name, String desc, String id, String password, String email, String qq) {
        super(name, desc, id, password);
        this.email = email;
        this.qq = qq;
    }
    public Teacher(String name, String desc, String id, String password) {
        super(name, desc, id, password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + super.getName() + '\'' +
                ", desc='" + super.getDesc() + '\'' +
                ", id='" + super.getId() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                '}';
    }
}
