package pojo;

public class Student extends People{
    private String grade;//年级

    public Student() {
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Student(String grade) {
        this.grade = grade;
    }

    public Student(String name, String desc, String id, String password, String grade) {
        super(name, desc, id, password);
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + super.getName() + '\'' +
                ", desc='" + super.getDesc() + '\'' +
                ", id='" + super.getId() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
