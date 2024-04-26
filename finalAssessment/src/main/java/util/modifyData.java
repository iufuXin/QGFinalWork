package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.runner.Request;
import pojo.Course;
import pojo.Teacher;
import service.DatabaseConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class modifyData {
    //私有化构造器
    private modifyData(){}
    public static Connection connection = null;

    /**
     * 修改老师数据
     * @param ID
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public static boolean modifyTeacherData(String ID, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        connection = DatabaseConnectionPool.getConnection();
        //通过Session对象获取id
        //HttpSession session = request.getSession();

        //定义sql语句
        String sql = "update teacherinformation set name = ?,description = ?,qq = ?,email = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,request.getParameter("name"));
        preparedStatement.setString(2,request.getParameter("desc"));
        preparedStatement.setString(3,request.getParameter("qq"));
        preparedStatement.setString(4,request.getParameter("email"));
        preparedStatement.setString(5,ID);
        //进行修改
        if (preparedStatement.executeUpdate() > 0){
            System.out.println(preparedStatement.executeUpdate());
            DatabaseConnectionPool.releaseConnection(connection);
            return true;
        }else {
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }

    /**
     * 修改学生数据
     * @param ID
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public static boolean modifyStudentData(String ID, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        connection = DatabaseConnectionPool.getConnection();
        //通过Session对象获取id
        //HttpSession session = request.getSession();

        //定义sql语句
        String sql = "update studentinformation set name = ?,description = ?,grade = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,request.getParameter("name"));
        preparedStatement.setString(2,request.getParameter("desc"));
        preparedStatement.setString(3,request.getParameter("grade"));
        preparedStatement.setString(4,ID);
        //进行修改
        if (preparedStatement.executeUpdate() > 0){
            System.out.println(preparedStatement.executeUpdate());
            DatabaseConnectionPool.releaseConnection(connection);
            return true;
        }else {
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }

    /**
     * 修改课程数据
     * @param course
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public static boolean modifyCourseData(Course course) throws SQLException, IOException {
        connection = DatabaseConnectionPool.getConnection();

        //定义sql语句
        String sql = "update course set name = ?,description = ?,starttime = ?,endtime = ?,maxEnrolment = ? where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,course.getName());
        preparedStatement.setString(2,course.getDesc());
        preparedStatement.setString(3,course.getStartTime());
        preparedStatement.setString(4,course.getEndTime());
        preparedStatement.setInt(5,course.getMaxEnrolment());
        preparedStatement.setString(6,course.getId());
        //进行修改
        if (preparedStatement.executeUpdate() > 0){
            System.out.println(preparedStatement.executeUpdate());
            DatabaseConnectionPool.releaseConnection(connection);
            return true;
        }else {
            System.out.println(false);
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }

    /**
     * 用于在学生添加课程时让课程里面的报名人数加一
     * @param course
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public static boolean modifyCourseEnrolment(Course course,boolean b) throws SQLException, IOException {
        if (course.getEnrolment() >= course.getMaxEnrolment()){
            //已经大于报名人数
            return false;
        }
        //传入true就是增加，传入false就是减少
        connection = DatabaseConnectionPool.getConnection();

        //定义sql语句
        String sql = "update course set  enrolment = ? where id = ?";

        int number = course.getEnrolment();
        if (b){
            number++;
        }else {
            number--;
        }

        course.setEnrolment(number);

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,course.getEnrolment());
        preparedStatement.setString(2,course.getId());
        //进行修改
        if (preparedStatement.executeUpdate() > 0){
            //修改成功
            DatabaseConnectionPool.releaseConnection(connection);
            return true;
        }else {
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }

    /**
     * 在修改个人数据时也要修改评论
     * @param userID
     * @param userName
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public static boolean modifyCommentData(String userID,String userName) throws SQLException, IOException {
        connection = DatabaseConnectionPool.getConnection();

        //定义sql语句
        String sql = "update comment set userName = ? where userID = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,userName);
        preparedStatement.setString(2,userID);
        //进行修改
        if (preparedStatement.executeUpdate() > 0){
            DatabaseConnectionPool.releaseConnection(connection);
            return true;
        }else {
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }

    /**
     * 在修改个人数据时也要修改课程对应老师开设的课程中的老师名字
     * @param userName
     * @param teacherID
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public static boolean modifyCourseData(String userName,String teacherID) throws SQLException, IOException {
        connection = DatabaseConnectionPool.getConnection();

        System.out.println( "modify里面的"+ userName + teacherID);

        //定义sql语句
        String sql = "update course set teacherName = ? where teacherid = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,userName);
        preparedStatement.setString(2,teacherID);
        //进行修改
        if (preparedStatement.executeUpdate() > 0){
            DatabaseConnectionPool.releaseConnection(connection);
            return true;
        }else {
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }

    /**
     * 用于修改简答题的正确率，用于老师的评分
     * @param studentID
     * @param courseIDChapterNumber
     * @param shortCorrectRate
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public static boolean modifyShortCorrectRate(String studentID,String courseIDChapterNumber,Double shortCorrectRate) throws SQLException, IOException {
        connection = DatabaseConnectionPool.getConnection();

        //定义sql语句
        String sql = "update studentanswer set shortCorrectRate = ? where studentID = ? and courseIDChapterNumber = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1,shortCorrectRate);
        preparedStatement.setString(2,studentID);
        preparedStatement.setString(3,courseIDChapterNumber);
        //进行修改
        if (preparedStatement.executeUpdate() > 0){
            DatabaseConnectionPool.releaseConnection(connection);
            return true;
        }else {
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }



}
