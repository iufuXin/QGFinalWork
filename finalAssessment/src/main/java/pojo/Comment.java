package pojo;

public class Comment {
    private String userID;//评论者id
    private String userName;//评论者名字
    private String occupation;//评论者职业
    private String comment;//评论内容
    private String id;//每条评论特有的id
    private String courseID;//课程的id

    public Comment() {
    }

    public Comment(String userID, String userName, String occupation, String comment, String id, String courseID) {
        this.userID = userID;
        this.userName = userName;
        this.occupation = occupation;
        this.comment = comment;
        this.id = id;
        this.courseID = courseID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
}
