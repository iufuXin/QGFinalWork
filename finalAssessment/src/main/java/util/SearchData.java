package util;

import pojo.*;
import service.DatabaseConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchData {
    //私有化构造器
    private SearchData(){}

    public static Connection connection = null;

    /**
     * 搜索老师信息
     * @param ID
     * @return
     */
    public static Teacher searchTeacher(String ID){
        Teacher teacher = new Teacher();
        //定义sql语句
        String sql = "select * from teacherinformation where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = DatabaseConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,ID);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                //teacher.setId(ID);
                teacher.setId(rs.getString("id"));
                teacher.setName(rs.getString("name"));
                teacher.setDesc(rs.getString("description"));
                teacher.setPassword(rs.getString("password"));
                teacher.setQq(rs.getString("qq"));
                teacher.setEmail(rs.getString("email"));
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return teacher;
    }

    /**
     * 搜索学生信息
     * @param ID
     * @return
     */
    public static Student searchStudent(String ID){
        Student student = new Student();
        //定义sql语句
        String sql = "select * from studentinformation where id = ?";
        try {
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,ID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setDesc(rs.getString("description"));
                student.setPassword(rs.getString("password"));
                student.setGrade(rs.getString("grade"));
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return student;
    }

    /**
     * 通过一堆学生的id号来查看学生，并返回一个学生集合，用于老师查找选择其课程下的学生
     * @param IDs
     * @return
     * @throws SQLException
     */
    public static ArrayList<Student> searchStudent(ArrayList<String> IDs) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        //定义sql语句
        String sql = "select * from studentinformation where id = ?";
        connection = DatabaseConnectionPool.getConnection();
        for (int i = 0; i < IDs.size(); i++) {
            String ID = IDs.get(i);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,ID);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    Student student = new Student();
                    student.setId(rs.getString("id"));
                    student.setName(rs.getString("name"));
                    student.setDesc(rs.getString("description"));
                    student.setPassword(rs.getString("password"));
                    student.setGrade(rs.getString("grade"));
                    students.add(student);
                }
                rs.close();
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        DatabaseConnectionPool.releaseConnection(connection);
        return students;
    }

    /**
     * 根据老师id来查找课程，用于显示在个人主页的课程查找
     * @param teacherID
     * @return
     */
    public static ArrayList<Course> searchCoursesByTeacherID(String teacherID){
        ArrayList<Course> courses = new ArrayList<>();
        //定义sql语句
        String sql = "select * from course where teacherid = ?";
        try {
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,teacherID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Course course = new Course();
                course.setId(rs.getString("id"));
                course.setName(rs.getString("name"));
                course.setDesc(rs.getString("description"));
                course.setStartTime(rs.getString("startTime"));
                course.setEndTime(rs.getString("endTime"));
                course.setMaxEnrolment(rs.getInt("maxEnrolment"));
                course.setEnrolment(rs.getInt("enrolment"));
                course.setTeacherID(rs.getString("teacherid"));
                courses.add(course);
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return courses;
    }

    /**
     * 查找课程信息用于显示在学生主页里面的
     * @param courseID
     * @return
     */
    public static ArrayList<Course> searchCoursesByIDStudent(String courseID){
        ArrayList<Course> courses = new ArrayList<>();
        //定义sql语句
        String sql = "select * from course where id = ?";
        try {
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,courseID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Course course = new Course();
                course.setId(rs.getString("id"));
                course.setName(rs.getString("name"));
                course.setDesc(rs.getString("description"));
                course.setStartTime(rs.getString("startTime"));
                course.setEndTime(rs.getString("endTime"));
                course.setMaxEnrolment(rs.getInt("maxEnrolment"));
                course.setEnrolment(rs.getInt("enrolment"));
                course.setTeacherID(rs.getString("teacherid"));
                courses.add(course);
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return courses;
    }

    /**
     * 通过学生id和课程章节号获得对应的学生答案
     * @param studentID
     * @param courseIDChapterNumber
     * @return
     */
    public static ArrayList<StudentAnswer> searchStudentAnswer(String studentID,String courseIDChapterNumber){
        ArrayList<StudentAnswer> studentAnswers = new ArrayList<>();
        //定义sql语句
        String sql = "select * from studentanswer where studentID = ? and courseIDChapterNumber = ?";
        try {
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,studentID);
            preparedStatement.setString(2,courseIDChapterNumber);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                StudentAnswer studentAnswer = new StudentAnswer();
                studentAnswer.setSingleTopicAnswer(rs.getString("singleTopicAnswer"));
                studentAnswer.setJudgmentAnswer(rs.getString("judgmentAnswer"));
                studentAnswer.setShortAnswer(rs.getString("shortAnswer"));
                studentAnswer.setStudentID(rs.getString("studentID"));
                studentAnswer.setStudentName(rs.getString("studentName"));
                studentAnswer.setCourseIDChapterNumber(rs.getString("courseIDChapterNumber"));
                studentAnswer.setSingleTopicCorrectRate(rs.getDouble("singleTopicCorrectRate"));
                studentAnswer.setJudgmentCorrectRate(rs.getDouble("judgmentCorrectRate"));
                studentAnswer.setShortCorrectRate(rs.getDouble("shortCorrectRate"));
                studentAnswer.setUploadTime(rs.getDate("uploadTime").toString());
                studentAnswers.add(studentAnswer);
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return studentAnswers;
    }

    /**
     * 用于查找是否有一样的答案，防止学生第二次提交
     * @param studentID
     * @param courseIDChapterNumber
     * @return
     */
    public static boolean searchSameStudentAnswer(String studentID,String courseIDChapterNumber){
        //定义sql语句
        String sql = "select * from studentanswer where studentID = ? and courseIDChapterNumber = ?";
        try {
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,studentID);
            preparedStatement.setString(2,courseIDChapterNumber);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                rs.close();
                DatabaseConnectionPool.releaseConnection(connection);
                //有相同的答案，既是第二次提交
                return true;
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        //没有相同的答案，既是第一次提交
        return false;
    }

    /**
     * 用于查找指定的课程，用于课程的回显
     * @param ID
     * @return
     */
    public static Course searchCoursesByID(String ID){
        Course course = new Course();
        //定义sql语句
        String sql = "select * from course where id = ?";
        try {
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,ID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                course.setId(rs.getString("id"));
                course.setName(rs.getString("name"));
                course.setDesc(rs.getString("description"));
                course.setStartTime(rs.getString("startTime"));
                course.setEndTime(rs.getString("endTime"));
                course.setMaxEnrolment(rs.getInt("maxEnrolment"));
                course.setEnrolment(rs.getInt("enrolment"));
                course.setTeacherID(rs.getString("teacherid"));
                course.setTeacherName(rs.getString("teacherName"));
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return course;
    }

    /**
     * 根据课程id查找对应的章节
     * @param ID
     * @return
     */
    public static ArrayList<Chapter> searchChapterByID(String ID){
        ArrayList<Chapter> chapters = new ArrayList<>();
        //定义sql语句
        String sql = "select * from chapter where course_id = ?";
        try {
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,ID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Chapter chapter = new Chapter();
                chapter.setCourseID(rs.getString("course_id"));
                chapter.setCourseAndChapterNumber(rs.getString("course_idAndchapterNumber"));
                chapter.setDesc(rs.getString("description"));
                chapter.setSingleTopicAnswer(rs.getString("singleTopicAnswer"));
                chapter.setJudgmentAnswer(rs.getString("judgmentAnswer"));
                chapter.setShortAnswer(rs.getInt("shortAnswer"));
                chapter.setFileName(rs.getString("fileName"));
                chapter.setFilePath(rs.getString("filePath"));
                chapter.setUploadTime(rs.getDate("uploadTime").toString());
                chapters.add(chapter);
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return chapters;
    }

    /**
     * 通过course_idAndchapterNumber来查找对应的章节
     * @param ID
     * @return
     */
    public static Chapter searchChapterBycourse_idAndchapterNumber(String ID){
        Chapter chapter = new Chapter();
        //定义sql语句
        String sql = "select * from chapter where course_idAndchapterNumber = ?";
        try {
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,ID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                chapter.setCourseID(rs.getString("course_id"));
                chapter.setCourseAndChapterNumber(rs.getString("course_idAndchapterNumber"));
                chapter.setDesc(rs.getString("description"));
                chapter.setSingleTopicAnswer(rs.getString("singleTopicAnswer"));
                chapter.setJudgmentAnswer(rs.getString("judgmentAnswer"));
                chapter.setShortAnswer(rs.getInt("shortAnswer"));
                chapter.setFileName(rs.getString("fileName"));
                chapter.setFilePath(rs.getString("filePath"));
                chapter.setUploadTime(rs.getDate("uploadTime").toString());
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return chapter;
    }

    /**
     * 根据 course_idAndchapterNumber 来查找是否有一样的章节
     * @param course_idAndchapterNumber
     * @param i
     * @return
     */
    public static boolean searchChapterByID(String course_idAndchapterNumber,int i){
        //定义sql语句
        String sql = "select * from chapter where course_idAndchapterNumber = ?;";
        //定义查找到的个数
        try {
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,course_idAndchapterNumber);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                DatabaseConnectionPool.releaseConnection(connection);
                //有相同的
                return true;
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        //没有相同的
        return false;
    }

    /**
     * 查找全部的课程
     * @return
     */
    public static ArrayList<Course> searchAllCourses(){
        ArrayList<Course> courses = new ArrayList<>();

        //定义sql语句
        String sql = "select * from course";
        try {
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Course course = new Course();
                course.setId(rs.getString("id"));
                course.setName(rs.getString("name"));
                course.setDesc(rs.getString("description"));
                course.setStartTime(rs.getString("startTime"));
                course.setEndTime(rs.getString("endTime"));
                course.setMaxEnrolment(rs.getInt("maxEnrolment"));
                course.setEnrolment(rs.getInt("enrolment"));
                course.setTeacherID(rs.getString("teacherid"));
                course.setTeacherName(rs.getString("teacherName"));
                courses.add(course);
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return courses;
    }

    /**
     * 用于查找是否有重复的
     * @param studentCourse
     * @return
     */
    public static boolean searchStudentCourse(StudentCourse studentCourse){
        //定义sql语句
        String sql = "select * from studentcourse where studentID = ? and courseID = ?";
        try {
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentCourse.getStudentID());
            preparedStatement.setString(2, studentCourse.getCourseID());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                //找到相同的
                rs.close();
                DatabaseConnectionPool.releaseConnection(connection);
                return false;
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 通过学生id来查找其选择的课程
     * @param studentID
     * @return
     */
    public static ArrayList<String> searchStudentCourse(String studentID){
        ArrayList<String> courseIDs = new ArrayList<>();

        //定义sql语句
        String sql = "select * from studentcourse where studentID = ? ";
        try {
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                courseIDs.add(rs.getString("courseID"));
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return courseIDs;
    }

    /**
     * 通过课程id来寻找对应的学生id，用于老师查看其课程报名学生情况
     * @param courseID
     * @return
     */
    public static ArrayList<String> searchStudentCourseByCourseID(String courseID){
        ArrayList<String> courseIDs = new ArrayList<>();

        //定义sql语句
        String sql = "select * from studentcourse where courseID = ? ";
        try {
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, courseID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                courseIDs.add(rs.getString("studentID"));
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return courseIDs;
    }

    /**
     * 通过课程id来查找对应的评论信息
     * @param ID
     * @return
     */
    public static ArrayList<Comment> searchCommentByCourseID(String ID){
        ArrayList<Comment> comments = new ArrayList<>();
        //定义sql语句
        String sql = "select * from comment where courseID = ?";
        try {
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,ID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Comment comment = new Comment();
                comment.setCourseID(ID);
                comment.setUserID(rs.getString("userID"));
                comment.setOccupation(rs.getString("occupation"));
                comment.setComment(rs.getString("comment"));
                comment.setId(rs.getString("id"));
                comment.setUserName(rs.getString("userName"));
                comments.add(comment);
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return comments;
    }

    /**
     * 通过课程id跟用户id来获取其在对应课程下的评论
     * @param userID
     * @param courseID
     * @return
     */
    public static ArrayList<Comment> searchCommentByUserIDAndCourseID(String userID,String courseID){
        ArrayList<Comment> comments = new ArrayList<>();

        //定义sql语句
        String sql = "select * from comment where userID = ? and courseID = ?";
        try {
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userID);
            preparedStatement.setString(2,courseID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Comment comment = new Comment();
                comment.setCourseID(rs.getString("courseID"));
                comment.setUserID(rs.getString("userID"));
                comment.setOccupation(rs.getString("occupation"));
                comment.setComment(rs.getString("comment"));
                comment.setId(rs.getString("id"));
                comment.setUserName(rs.getString("userName"));
                System.out.println(comment);
                comments.add(comment);
            }
            rs.close();
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return comments;
    }


}
