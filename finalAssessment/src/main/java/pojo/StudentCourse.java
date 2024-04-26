package pojo;

public class StudentCourse {
    //用于记录学生所选的课程
    private String studentID;
    private String courseID;

    public StudentCourse() {
    }

    public StudentCourse(String studentID, String courseID) {
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
}
