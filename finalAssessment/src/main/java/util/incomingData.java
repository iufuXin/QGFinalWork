package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pojo.Chapter;
import pojo.StudentCourse;
import pojo.Teacher;
import service.DatabaseConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class incomingData {
    //私有化构造器
    private incomingData(){}

    public static Connection connection = null;


    //传入一样的数据
    public static String incomeTeacherData(HttpServletRequest request, HttpServletResponse response) {

        //定义sql语句
        String sql = "insert into teacherinformation (name,description,id,password,qq,email) values (?,?,?,?,?,?);";
        //定义编号
        String ID = "";
        if (!checkData.checkPassword(request.getParameter("password"))){
            //检验密码是否过短
            System.out.println("密码简单");
            return null;
        }
        try {
            connection = DatabaseConnectionPool.getConnection();
            //获取sql执行的对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,request.getParameter("name"));
            if ( request.getParameter("desc") == null){
                //没填描述的话
                preparedStatement.setString(2,"这个人很懒，什么都没有留下");
            }else {
                preparedStatement.setString(2,request.getParameter("desc"));
            }
            ID = generateRandomId();
            while (!compareForTheSame(ID,"teacher")){
                //相同的话返回false，然后加个!使其循环
                ID = generateRandomId();
            }
            preparedStatement.setString(3,ID);
            preparedStatement.setString(4,request.getParameter("password"));
            preparedStatement.setString(5,request.getParameter("qq"));
            preparedStatement.setString(6,request.getParameter("email"));
            int count = preparedStatement.executeUpdate();
            if ( count > 0){
                System.out.println("添加成功！！！");
            }
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ID;
    }
    public static String incomeStudentData(HttpServletRequest request, HttpServletResponse response){
        //定义sql语句
        String sql = "insert into studentinformation (name,description,id,password,grade) values (?,?,?,?,?);";
        //定义编号
        String ID = "";
        if (!checkData.checkPassword(request.getParameter("password"))){
            //检验密码是否过短
            System.out.println("密码简单");
            return "";
        }
        try {
            connection = DatabaseConnectionPool.getConnection();
            //获取sql执行的对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,request.getParameter("name"));
            if ( request.getParameter("desc") == null){
                //没填描述的话
                String desc = "这个人很懒，什么都没有留下";
                preparedStatement.setString(2,desc);
            }else {
                preparedStatement.setString(2,request.getParameter("desc"));
            }
            //preparedStatement.setString(2,request.getParameter("desc"));
            ID = generateRandomId();
            while (!compareForTheSame(ID,"student")){
                //相同的话返回false，然后加个!使其循环
                ID = generateRandomId();
            }
            preparedStatement.setString(3,ID);
            preparedStatement.setString(4,request.getParameter("password"));
            preparedStatement.setString(5,request.getParameter("grade"));
            int count = preparedStatement.executeUpdate();
            if ( count > 0){
                System.out.println("添加成功！！！");
            }
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ID;
    }
    public static String incomeCourseData(HttpServletRequest request, HttpServletResponse response,String teacherID) throws IOException {
        //定义sql语句
        String sql = "insert into course (name,description,id,starttime,endtime,maxEnrolment,enrolment,teacherid,teacherName) values (?,?,?,?,?,?,?,?,?);";
        //定义编号
        String ID = "";

        // 设置响应类型为 JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 获取请求体中的 JSON 数据
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();

        // 解析 JSON 数据
        JsonObject json = JsonParser.parseString(requestBody).getAsJsonObject();
        String courseName = json.get("courseName").getAsString();
        String courseDescription = json.get("courseDescription").getAsString();
        String startTime = json.get("startTime").getAsString();
        String endTime = json.get("endTime").getAsString();
        int maxParticipants = json.get("maxParticipants").getAsInt();
        //通过老师id查找老师姓名
        Teacher teacher = SearchData.searchTeacher(teacherID);
        //用于匹配相应的时间格式
        String regex = "^\\b(?:Sun|Mon|Tue|Wed|Thu|Fri|Sat)\\b\\s(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s\\d{1,2}\\s\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        //检验时间是否符合格式
        if (!pattern.matcher(startTime).matches() || !pattern.matcher(endTime).matches() || maxParticipants <= 0 || courseName.isEmpty()){
            //不符合格式的话就返回null
            return null;
        }
        try {
            connection = DatabaseConnectionPool.getConnection();
            //获取sql执行的对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,courseName);
            if (Objects.equals(courseDescription, "")){
                //没填描述的话
                String desc = "这个人很懒，什么都没有留下";
                preparedStatement.setString(2,desc);
            }else {
                preparedStatement.setString(2,courseDescription);
            }
            //preparedStatement.setString(2,request.getParameter("desc"));
            ID = generateRandomId();
            while (!compareForTheSame(ID,"course")){
                //相同的话返回false，然后加个!使其循环
                ID = generateRandomId();
            }
            preparedStatement.setString(3,ID);
            preparedStatement.setDate(4, changeTimeFormat(startTime));
            preparedStatement.setDate(5,changeTimeFormat(endTime));
            preparedStatement.setInt(6,maxParticipants);
            preparedStatement.setInt(7,0);
            preparedStatement.setString(8,teacherID);
            preparedStatement.setString(9,teacher.getName());
            int count = preparedStatement.executeUpdate();
            if ( count > 0){
                System.out.println("添加成功！！！");
            }
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // 发送响应
        PrintWriter out = response.getWriter();
        out.println("课程数据已接收");
        out.flush();
        return ID;
    }

    /**
     * 添加章节
     * @param request
     * @param response
     * @return
     */
    public static String addChapter(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        //定义sql语句
        String sql = "insert into chapter (course_id,course_idAndchapterNumber,description,singleTopicAnswer,judgmentAnswer,shortAnswer,fileName,filePath,uploadTime) values (?,?,?,?,?,?,?,?,?);";
        connection = DatabaseConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //定义ID
        String ID = "";
        //定义章节和课程id
        String course_idAndchapterNumber = "";
        //循环数量
        int i = 1;
        //文件的存储路径
        String filePath = "C:\\Users\\刘付鑫\\IdeaProjects\\finalAssessment\\src\\main\\webapp\\uploadingFiles\\";
        //获取数据，并判断上传的文件是否是多做的数据，才是文件上传的
        if (ServletFileUpload.isMultipartContent(request)) {
//      创建FileItemFactory工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的工具类ServletFileUpload类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                //解析上传的数据，得到第一个表单项FileItem
                List<FileItem> list = servletFileUpload.parseRequest(request);
                //循环判断，第一个表单项，是普通类型还是上传的文件
                for (FileItem fileItem : list) {
                    if (fileItem.isFormField()) {
                        //普通表单项
                        if (fileItem.getString().isEmpty() || fileItem.getString().length() >= 60){
                            //判断是否传入空信息
                            DatabaseConnectionPool.releaseConnection(connection);
                            return ID;
                        } else {
                            if (i == 1){
                                ID = fileItem.getString("UTF-8");
                                preparedStatement.setString(1,ID);
                            } else if (i == 2){
                                course_idAndchapterNumber = ID + fileItem.getString("UTF-8");
                                //查找是否有一样的
                                if (SearchData.searchChapterByID(course_idAndchapterNumber,0)){
                                    //有一样的就只返回ID
                                    return ID;
                                }
                                preparedStatement.setString(i,course_idAndchapterNumber);
                            }else {
                                preparedStatement.setString(i,fileItem.getString("UTF-8"));
                            }
                            i++;
                        }
                    } else {
                        //上传的文件
                        System.out.println("表单项的name属性值：" + fileItem.getFieldName());
                        System.out.println("上传的文件名：：" + fileItem.getName());
                        if (fileItem.getName().equals("")){
                            //判断其是否有上传文件，若没有的话就不做任何处理
                            DatabaseConnectionPool.releaseConnection(connection);
                            return ID;
                        }else {
                            //若有，则存进对应的路径中
                            preparedStatement.setString(7,fileItem.getName());
                            fileItem.write(new File(filePath + fileItem.getName()));
                            preparedStatement.setString(8,filePath + fileItem.getName());
                        }
                    }
                }
                // 获取当前时间的毫秒数
                long currentTimeMillis = System.currentTimeMillis();
                // 使用当前时间的毫秒数创建 java.sql.Date 对象
                Date currentDate = new Date(currentTimeMillis);
                preparedStatement.setDate(9,currentDate);
                int count = preparedStatement.executeUpdate();
                if (count > 0){
                    System.out.println("添加成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DatabaseConnectionPool.releaseConnection(connection);
            return course_idAndchapterNumber;
        }else {
            DatabaseConnectionPool.releaseConnection(connection);
            return course_idAndchapterNumber;
        }

    }

    /**
     * 把Tue Apr 02 2024这类的时间格式转换成 date类
     * @param time
     * @return
     */
    public static Date changeTimeFormat(String time){
        // 定义日期时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd yyyy", Locale.ENGLISH);

        // 解析字符串为 LocalDate 类型
        LocalDate localDate = LocalDate.parse(time, formatter);

        // 将LocalDate转换为java.sql.Date
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        // 将java.sql.Date转换为Date
        Date date = new Date(sqlDate.getTime());

        return date;
    }

    /**
     * 添加评论进数据库
     * @param request
     * @param response
     * @return
     */
    public static void incomeComment(HttpServletRequest request, HttpServletResponse response) {
        //定义sql语句
        String sql = "insert into comment (userID,occupation,id,comment,userName,courseID) values (?,?,?,?,?,?);";
        //定义编号
        String ID = "";

        //获取评论者id及身份信息
        HttpSession session = request.getSession();
        Object id = session.getAttribute("id");
        Object occupation = session.getAttribute("occupation");
        Object userName = session.getAttribute("userName");

        try {
            connection = DatabaseConnectionPool.getConnection();
            //获取sql执行的对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id.toString());
            preparedStatement.setString(2,occupation.toString());

            ID = generateRandomId();

            preparedStatement.setString(3,ID);
            preparedStatement.setString(4,request.getParameter("comment"));
            preparedStatement.setString(5,userName.toString());
            preparedStatement.setString(6,request.getParameter("courseID"));
            int count = preparedStatement.executeUpdate();
            if ( count > 0){
                System.out.println("添加成功！！！");
            }
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return ;
    }

    /**
     * 生成十位数的id号
     * @return
     */
    public static String generateRandomId(){
        StringBuilder ID = new StringBuilder("3123");
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            int temp = r.nextInt(10);
            ID.append(temp);
        }
        return ID.toString();
    }
    //比较ID号跟数据库里面有没有重复
    public static boolean compareForTheSame(String ID,String name) throws SQLException {
        connection = DatabaseConnectionPool.getConnection();
        //定义sql语句
        String sql = "";
        try {
            if (name.equals("teacher")){
                //根据传进来的字符来查询相应的表
                sql = "select * from teacherinformation where id = ?;";
            }else if (name.equals("student")){
                sql = "select * from studentinformation where id = ?;";
            } else if (name.equals("course")) {
                sql = "select * from course where id = ?;";
            }
            //获取sql执行的对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if ( resultSet.next()){
                DatabaseConnectionPool.releaseConnection(connection);
                return false;
            }else {
                DatabaseConnectionPool.releaseConnection(connection);
                return true;
            }
        } catch (Exception e) {
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
    }

    /**
     * 添加学生课程id进数据库
     * @param studentCourse
     * @return
     */
    public static boolean incomeStudentCourseData(StudentCourse studentCourse) {
        //定义sql语句
        String sql = "insert into studentcourse (studentID, courseID) values (?,?);";
        try {
            connection = DatabaseConnectionPool.getConnection();
            //获取sql执行的对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,studentCourse.getStudentID());
            preparedStatement.setString(2,studentCourse.getCourseID());

            if (!SearchData.searchStudentCourse(studentCourse)){
                //查找是否有相同的
                DatabaseConnectionPool.releaseConnection(connection);
                return false;
            }
            int count = preparedStatement.executeUpdate();
            if ( count > 0){
                System.out.println("添加成功！！！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DatabaseConnectionPool.releaseConnection(connection);
            return false;
        }
        //添加成功
        DatabaseConnectionPool.releaseConnection(connection);
        return true;
    }

    /**
     * 添加学生上传的答案进数据库
     * @param request
     * @param response
     * @return
     */
    public static void incomeStudentAnswer(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Object id = session.getAttribute("id");
        Object userName = session.getAttribute("userName");

        if (SearchData.searchSameStudentAnswer(id.toString(),request.getParameter("courseIDChapterNumber"))){
            //request.setAttribute("warning","你已经提交过一次了");
            return;
        }
        connection = DatabaseConnectionPool.getConnection();
        //定义sql语句
        String sql = "insert into studentanswer (singleTopicAnswer,judgmentAnswer,shortAnswer,studentID,studentName,courseIDChapterNumber,singleTopicCorrectRate,judgmentCorrectRate,shortCorrectRate,uploadTime) values (?,?,?,?,?,?,?,?,?,?);";

        StringBuilder shortAnswer = new StringBuilder();



        //获取正确率
        ArrayList<Double> doubles = checkData.checkCorrectRate(request.getParameter("singleTopicAnswer"),request.getParameter("judgmentAnswer"),request.getParameter("courseIDChapterNumber"));

        // 获取当前时间的毫秒数
        long currentTimeMillis = System.currentTimeMillis();
        // 使用当前时间的毫秒数创建 java.sql.Date 对象
        Date currentDate = new Date(currentTimeMillis);

        try {
            connection = DatabaseConnectionPool.getConnection();
            //获取sql执行的对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,request.getParameter("singleTopicAnswer"));

            preparedStatement.setString(2,request.getParameter("judgmentAnswer"));

            for (int i = 1; i <= Integer.parseInt(request.getParameter("shortNumber")); i++) {
                //获取简答题信息
                shortAnswer.append(i).append(".").append(request.getParameter("textBox" + i)).append("\n");
            }
            if (shortAnswer.toString().length() >= 500){
                DatabaseConnectionPool.releaseConnection(connection);
                return;
            }

            preparedStatement.setString(3, shortAnswer.toString());
            preparedStatement.setString(4,id.toString());
            preparedStatement.setString(5,userName.toString());
            preparedStatement.setString(6,request.getParameter("courseIDChapterNumber"));
            preparedStatement.setDouble(7,doubles.get(0));
            preparedStatement.setDouble(8,doubles.get(1));
            preparedStatement.setDouble(9,-1);
            preparedStatement.setDate(10,currentDate);
            int count = preparedStatement.executeUpdate();
            if ( count > 0){
                System.out.println("添加成功！！！");
            }
            DatabaseConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
