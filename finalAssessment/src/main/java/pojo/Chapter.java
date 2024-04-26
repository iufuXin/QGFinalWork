package pojo;

public class Chapter {
    //章节的实体类
    private String courseID;//对应的课程id
    private String courseAndChapterNumber;//对应的第几章节
    private String desc;//章节的简要描述
    private String singleTopicAnswer;//对应单选题的答案
    private String judgmentAnswer;//对应判断题的答案
    private Integer shortAnswer;//对应简答题的题目数量
    private String fileName;//章节对应的题目文件名
    private String filePath;//保存的文件路径
    private String uploadTime;//上传时间

    public Chapter() {
    }

    public Chapter(String courseID, String courseAndChapterNumber, String desc, String singleTopicAnswer, String judgmentAnswer, Integer shortAnswer, String fileName, String filePath, String uploadTime) {
        this.courseID = courseID;
        this.courseAndChapterNumber = courseAndChapterNumber;
        this.desc = desc;
        this.singleTopicAnswer = singleTopicAnswer;
        this.judgmentAnswer = judgmentAnswer;
        this.shortAnswer = shortAnswer;
        this.fileName = fileName;
        this.filePath = filePath;
        this.uploadTime = uploadTime;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseAndChapterNumber() {
        return courseAndChapterNumber;
    }

    public void setCourseAndChapterNumber(String courseAndChapterNumber) {
        this.courseAndChapterNumber = courseAndChapterNumber;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Integer getShortAnswer() {
        return shortAnswer;
    }

    public void setShortAnswer(Integer shortAnswer) {
        this.shortAnswer = shortAnswer;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }
}
