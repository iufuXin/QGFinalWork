package pojo;

public class Course {
    private String id;//课程编号，用于以后提交文件时可以找到对应的课程
    private String name;//课程名字
    private String teacherID;//开设老师编号
    private String desc;//课程描述
    private String startTime;//课程开始时间
    private String endTime;//课程结束时间
    private Integer maxEnrolment;//报名限制人数
    private Integer enrolment;//已报名人数
    private String teacherName;//老师名字
    public Course() {
    }

    public Course(String id, String name, String teacherID, String desc, String startTime, String endTime, Integer maxEnrolment, Integer enrolment, String teacherName) {
        this.id = id;
        this.name = name;
        this.teacherID = teacherID;
        this.desc = desc;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxEnrolment = maxEnrolment;
        this.enrolment = enrolment;
        this.teacherName = teacherName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getMaxEnrolment() {
        return maxEnrolment;
    }

    public void setMaxEnrolment(Integer maxEnrolment) {
        this.maxEnrolment = maxEnrolment;
    }

    public Integer getEnrolment() {
        return enrolment;
    }

    public void setEnrolment(Integer enrolment) {
        this.enrolment = enrolment;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
