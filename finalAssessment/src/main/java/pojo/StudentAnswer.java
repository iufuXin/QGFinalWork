package pojo;

import java.util.ArrayList;

public class StudentAnswer {
    private String singleTopicAnswer;//单选题答案
    private String judgmentAnswer;//判断题答案
    private String shortAnswer;//简答题答案
    private String studentID;//学生id
    private String studentName;//学生名字
    private String courseIDChapterNumber;//对应的课程id及章节号
    private double singleTopicCorrectRate;//单选题正确率
    private double judgmentCorrectRate;//判断题正确率
    private double shortCorrectRate;//简答题正确率
    private String uploadTime;//上传时间
    public StudentAnswer() {
    }

    public StudentAnswer(String singleTopicAnswer, String judgmentAnswer, String shortAnswer, String studentID, String studentName, String courseIDChapterNumber, double singleTopicCorrectRate, double judgmentCorrectRate, double shortCorrectRate, String uploadTime) {
        this.singleTopicAnswer = singleTopicAnswer;
        this.judgmentAnswer = judgmentAnswer;
        this.shortAnswer = shortAnswer;
        this.studentID = studentID;
        this.studentName = studentName;
        this.courseIDChapterNumber = courseIDChapterNumber;
        this.singleTopicCorrectRate = singleTopicCorrectRate;
        this.judgmentCorrectRate = judgmentCorrectRate;
        this.shortCorrectRate = shortCorrectRate;
        this.uploadTime = uploadTime;
    }

    public String getSingleTopicAnswer() {
        return singleTopicAnswer;
    }

    public void setSingleTopicAnswer(String singleTopicAnswer) {
        this.singleTopicAnswer = singleTopicAnswer;
    }

    public String getJudgmentAnswer() {
        return judgmentAnswer;
    }

    public void setJudgmentAnswer(String judgmentAnswer) {
        this.judgmentAnswer = judgmentAnswer;
    }

    public String getShortAnswer() {
        return shortAnswer;
    }

    public void setShortAnswer(String shortAnswer) {
        this.shortAnswer = shortAnswer;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseIDChapterNumber() {
        return courseIDChapterNumber;
    }

    public void setCourseIDChapterNumber(String courseIDChapterNumber) {
        this.courseIDChapterNumber = courseIDChapterNumber;
    }

    public double getSingleTopicCorrectRate() {
        return singleTopicCorrectRate;
    }

    public void setSingleTopicCorrectRate(double singleTopicCorrectRate) {
        this.singleTopicCorrectRate = singleTopicCorrectRate;
    }

    public double getJudgmentCorrectRate() {
        return judgmentCorrectRate;
    }

    public void setJudgmentCorrectRate(double judgmentCorrectRate) {
        this.judgmentCorrectRate = judgmentCorrectRate;
    }

    public double getShortCorrectRate() {
        return shortCorrectRate;
    }

    public void setShortCorrectRate(double shortCorrectRate) {
        this.shortCorrectRate = shortCorrectRate;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }
}
