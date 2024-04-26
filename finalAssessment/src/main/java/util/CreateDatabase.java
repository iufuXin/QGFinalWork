package util;

import service.DatabaseConnectionPool;

import java.sql.*;

public class CreateDatabase {
    public static Connection connection;
    public static final String schemaName = "qgfinalassessment";

    public static void createDatabase() throws SQLException {
        createCourse();
        createComment();
        createStudentAnswer();
        createStudentInformation();
        createChapter();
        createTeacherInformation();
        createStudentCourse();
    }

    /**
     * 创建表StudentAnswer
     * @throws SQLException
     */
    public static void createStudentAnswer() throws SQLException {
        connection = DatabaseConnectionPool.getConnection();

        // SQL 创建表的语句
        String createTableSQL = "CREATE TABLE IF NOT EXISTS studentanswer ("
                + "singleTopicAnswer VARCHAR(70) comment '单选题答案', "
                + "judgmentAnswer VARCHAR(70) comment '判断题答案',"
                + "shortAnswer VARCHAR(1500) comment '简答题答案', "
                + "studentID VARCHAR(15) comment '学生id', "
                + "studentName VARCHAR(10) comment '学生名字', "
                + "courseIDChapterNumber VARCHAR(20) comment '对应的课程id及章节号', "
                + "singleTopicCorrectRate DOUBLE null comment '单选题正确率',"
                + "judgmentCorrectRate DOUBLE null comment '判断题正确率',"
                + "shortCorrectRate DOUBLE comment '简答题正确率', "
                + "uploadTime DATE null comment '上传时间'"
                + ")"
                + "comment '存放学生答案'";

        DatabaseMetaData metaData = connection.getMetaData();
        String tableName = "studentanswer";
        if (tableExistsInSchema(metaData,tableName)){
            System.out.println("已经存在表" + tableName);
            DatabaseConnectionPool.releaseConnection(connection);
        }else {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableSQL);
            statement.close();
            DatabaseConnectionPool.releaseConnection(connection);
        }
    }

    /**
     * 创建学生信息的表
     * @throws SQLException
     */
    public static void createStudentInformation() throws SQLException {
        connection = DatabaseConnectionPool.getConnection();

        // SQL 创建表的语句
        String createTableSQL = "CREATE TABLE IF NOT EXISTS studentinformation (" +
                "    id VARCHAR(15), " +
                "    name VARCHAR(10), " +
                "    description varchar(30), " +
                "    password VARCHAR(20)," +
                "    grade VARCHAR(10)" +
                ")" +
                "comment '存放学生信息'";

        DatabaseMetaData metaData = connection.getMetaData();
        String tableName = "studentinformation";
        if (tableExistsInSchema(metaData,tableName)){
            System.out.println("已经存在表" + tableName);
            DatabaseConnectionPool.releaseConnection(connection);
        }else {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableSQL);
            statement.close();
            DatabaseConnectionPool.releaseConnection(connection);
        }
    }

    /**
     * 创建老师的信息表
     * @throws SQLException
     */
    public static void createTeacherInformation() throws SQLException {
        connection = DatabaseConnectionPool.getConnection();

        // SQL 创建表的语句
        String createTableSQL = "CREATE TABLE IF NOT EXISTS teacherinformation (" +
                "    id VARCHAR(20), " +
                "    description varchar(10)," +
                "    name VARCHAR(30)," +
                "    password VARCHAR(15)," +
                "    qq VARCHAR(10)," +
                "    email VARCHAR(20)" +
                ")" +
                "comment '存放老师信息'";

        DatabaseMetaData metaData = connection.getMetaData();
        String tableName = "teacherinformation";
        if (tableExistsInSchema(metaData,tableName)){
            System.out.println("已经存在表" + tableName);
            DatabaseConnectionPool.releaseConnection(connection);
        }else {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableSQL);
            statement.close();
            DatabaseConnectionPool.releaseConnection(connection);
        }
    }

    /**
     * 创建存放学生所选择的课程
     * @throws SQLException
     */
    public static void createStudentCourse() throws SQLException {
        connection = DatabaseConnectionPool.getConnection();

        // SQL 创建表的语句
        String createTableSQL = "CREATE TABLE IF NOT EXISTS studentcourse (" +
                "    studentID VARCHAR(20) , " +
                "    courseID VARCHAR(20)" +
                ")" +
                "comment '存放学生所选择的课程'";

        DatabaseMetaData metaData = connection.getMetaData();
        String tableName = "studentcourse";
        if (tableExistsInSchema(metaData,tableName)){
            System.out.println("已经存在表" + tableName);
            DatabaseConnectionPool.releaseConnection(connection);
        }else {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableSQL);
            statement.close();
            DatabaseConnectionPool.releaseConnection(connection);
        }
    }

    /**
     * 创建课程表
     * @throws SQLException
     */
    public static void createCourse() throws SQLException {
        connection = DatabaseConnectionPool.getConnection();

        // SQL 创建表的语句
        String createTableSQL = "CREATE TABLE IF NOT EXISTS course (" +
                "    id VARCHAR(15) comment '课程编号，用于以后提交文件时可以找到对应的课程', " +
                "    name VARCHAR(10) comment '课程名字', " +
                "    description VARCHAR(50) comment '课程描述', " +
                "    startTime DATE comment '课程开始时间', " +
                "    endTime DATE comment '课程结束时间', " +
                "    maxEnrolment INT comment '报名限制人数', " +
                "    enrolment INT comment '已报名人数', " +
                "    teacherid VARCHAR(15) comment '开设老师编号', " +
                "    teacherName VARCHAR(10) comment '老师名字'" +
                ")" +
                "comment '存放课程'";

        DatabaseMetaData metaData = connection.getMetaData();
        String tableName = "course";
        if (tableExistsInSchema(metaData,tableName)){
            System.out.println("已经存在表" + tableName);
            DatabaseConnectionPool.releaseConnection(connection);
        }else {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableSQL);
            statement.close();
            DatabaseConnectionPool.releaseConnection(connection);
        }
    }

    /**
     * 创建存放评论的表
     * @throws SQLException
     */
    public static void createComment() throws SQLException {
        connection = DatabaseConnectionPool.getConnection();

        // SQL 创建表的语句
        String createTableSQL = "CREATE TABLE IF NOT EXISTS comment (" +
                "    userID VARCHAR(15) comment '评论者id', " +
                "    occupation VARCHAR(15) comment '评论者职业', " +
                "    comment VARCHAR(600) comment '评论内容', " +
                "    id VARCHAR(15) comment '每条评论特有的id', " +
                "    userName VARCHAR(15) comment '评论者名字', " +
                "    courseID VARCHAR(15) comment '课程的id'" +
                ")" +
                "comment '存放评论'";

        DatabaseMetaData metaData = connection.getMetaData();
        String tableName = "comment";
        if (tableExistsInSchema(metaData,tableName)){
            System.out.println("已经存在表" + tableName);
            DatabaseConnectionPool.releaseConnection(connection);
        }else {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableSQL);
            statement.close();
            DatabaseConnectionPool.releaseConnection(connection);
        }
    }

    /**
     * 创建存放章节的表
     * @throws SQLException
     */
    public static void createChapter() throws SQLException {
        connection = DatabaseConnectionPool.getConnection();

        // SQL 创建表的语句
        String createTableSQL = "CREATE TABLE IF NOT EXISTS chapter (" +
                "    course_id VARCHAR(20) comment '对应的课程id', " +
                "    course_idAndchapterNumber VARCHAR(20) comment '对应的第几章节', " +
                "    description VARCHAR(400) comment '章节的简要描述', " +
                "    singleTopicAnswer VARCHAR(50) comment '对应单选题的答案', " +
                "    judgmentAnswer VARCHAR(50) comment '对应判断题的答案', " +
                "    shortAnswer INT comment '对应简答题的题目数量', " +
                "    fileName VARCHAR(255) comment '章节对应的题目文件名', " +
                "    filePath VARCHAR(200) comment '保存的文件路径', " +
                "    uploadTime DATE comment '上传时间'" +
                ")" +
                "comment '存放章节'";

        DatabaseMetaData metaData = connection.getMetaData();
        String tableName = "chapter";
        if (tableExistsInSchema(metaData,tableName)){
            System.out.println("已经存在表" + tableName + "在架构" + schemaName);
            DatabaseConnectionPool.releaseConnection(connection);
        }else {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableSQL);
            statement.close();
            DatabaseConnectionPool.releaseConnection(connection);
        }
    }

    /**
     * 用于检查是否存在该表
     * @param metaData
     * @param tableName
     * @return
     * @throws SQLException
     */
    private static boolean tableExistsInSchema(DatabaseMetaData metaData, String tableName) throws SQLException {
        ResultSet tables = metaData.getTables(null, schemaName, tableName, null);
        return tables.next();
    }


}
