package util;

import service.DatabaseConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteData {
    private deleteData(){}
    public static Connection connection = null;

    public static boolean deleteCourse(String ID) throws SQLException {
        try {
            //定义sql语句
            String sql = "delete from course where id = ?;";
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //删除章节
            deleteChapterByCourseID(ID);

            //删除评论
            deleteCommentByCourseID(ID);

            //删除学生选择的课程
            deleteStudentCourseByCourseID(ID);

            //删除对应的答案
            deleteStudentAnswerBy(ID);

            preparedStatement.setString(1,ID);

            if (preparedStatement.executeUpdate() > 0){
                //删除成功
                DatabaseConnectionPool.releaseConnection(connection);
                return true;
            }else {
                //删除失败
                DatabaseConnectionPool.releaseConnection(connection);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //删除失败
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }

    /**
     * 根据每个评论的id删除对应的评论
     * @param commentID
     * @return
     * @throws SQLException
     */
    public static boolean deleteComment(String commentID) throws SQLException {
        try {
            //定义sql语句
            String sql = "delete from comment where id = ?;";
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,commentID);

            preparedStatement.executeUpdate();

            DatabaseConnectionPool.releaseConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            //删除失败
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }

    /**
     * 用于删除学生选择的课程
     * @param courseID
     * @param studentID
     * @return
     * @throws SQLException
     */
    public static boolean deleteStudentCourse(String courseID,String studentID) throws SQLException {
        try {
            //定义sql语句
            String sql = "delete from studentcourse where courseID = ? and studentID = ?;";
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,courseID);
            preparedStatement.setString(2,studentID);

            preparedStatement.executeUpdate();

            DatabaseConnectionPool.releaseConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            //删除失败
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }

    /**
     * 删除对应的课程评论
     * @param courseID
     * @return
     * @throws SQLException
     */
    public static boolean deleteCommentByCourseID(String courseID) throws SQLException {
        try {
            //定义sql语句
            String sql = "delete from comment where courseID = ? ;";
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,courseID);

            preparedStatement.executeUpdate();

            DatabaseConnectionPool.releaseConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            //删除失败
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }

    /**
     * 删除章节
     * @param courseAndChapterNumber
     * @return
     * @throws SQLException
     */
    public static boolean deleteChapter(String courseAndChapterNumber) throws SQLException {
        try {
            //定义sql语句
            String sql = "delete from chapter where course_idAndchapterNumber = ? ;";
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,courseAndChapterNumber);

            DatabaseConnectionPool.releaseConnection(connection);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            //删除失败
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }

    /**
     * 根据课程id来删除章节
     * @param courseID
     * @return
     * @throws SQLException
     */
    public static boolean deleteChapterByCourseID(String courseID) throws SQLException {
        try {
            //定义sql语句
            String sql = "delete from chapter where course_id = ? ;";
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,courseID);

            preparedStatement.executeUpdate();

            DatabaseConnectionPool.releaseConnection(connection);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            //删除失败
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }

    /**
     * 根据课程id删除对应的学生选择的课程信息
     * @param courseID
     * @return
     * @throws SQLException
     */
    public static boolean deleteStudentCourseByCourseID(String courseID) throws SQLException {
        try {
            //定义sql语句
            String sql = "delete from studentcourse where courseID = ? ;";
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,courseID);

            preparedStatement.executeUpdate();

            DatabaseConnectionPool.releaseConnection(connection);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            //删除失败
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }

    /**
     * 删除学生答案，用于删除章节时删除学生答案
     * @param courseIDChapterNumber
     * @return
     * @throws SQLException
     */
    public static boolean deleteStudentAnswer(String courseIDChapterNumber) throws SQLException {
        try {
            //定义sql语句
            String sql = "delete from studentanswer where courseIDChapterNumber = ? ;";
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,courseIDChapterNumber);

            preparedStatement.executeUpdate();
            DatabaseConnectionPool.releaseConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            //删除失败
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }

    /**
     * 根据课程id删除对应学生的答案
     * @param courseID
     * @return
     * @throws SQLException
     */
    public static boolean deleteStudentAnswerBy(String courseID) throws SQLException {
        try {
            //定义sql语句
            String sql = "delete from studentanswer where courseIDChapterNumber like ? ;";
            connection = DatabaseConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,courseID + "%");

            preparedStatement.executeUpdate();
            DatabaseConnectionPool.releaseConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }


}
