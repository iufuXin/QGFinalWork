package web;

import service.DatabaseConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/testHttpConnection")

public class TestHttpConnection extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        //设置服务器端字符编码为UTF-8
//        req.setCharacterEncoding("UTF-8");
//        //获取数据库连接
//        DatabaseConnectionPool.SimpleConnectionPool();
//        if ( req.getParameter("password").equals(req.getParameter("confirmPassword"))){
//            //检查两次密码输入是否一样，一样再的话再进来
//            if ( req.getParameter("occupation").equals("teacher")){
//                //判断是教师还是学生
//                incomingData.incomeTeacherData(req, resp);
//            } else {
//                incomingData.incomeStudentData(req, resp);
//            }
//            resp.sendRedirect("/finalAssessment/index.html");
//        }else {
//            System.out.println("密码不一致，请重新输入");
//            if ( req.getParameter("occupation").equals("teacher")){
//                //判断是教师还是学生
//                resp.sendRedirect("/finalAssessment/registerTeacher.html");
//            }else {
//                resp.sendRedirect("/finalAssessment/registerStudent.html");
//            }
//        }
            //设置服务器端字符编码为UTF-8
            req.setCharacterEncoding("UTF-8");
            //获取数据库连接
            DatabaseConnectionPool.SimpleConnectionPool();
            try {
                Connection connection = DatabaseConnectionPool.getConnection();
                //定义sql语句
                String sql = null;
                if ( req.getParameter("occupation").equals("teacher")){
                    sql = "select * from teacherinformation where id = ? and password = ?;";
                }else {
                    sql = "select * from studentinformation where id = ? and password = ?;";
                }
                //获取sql执行的对象
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,req.getParameter("id"));
                preparedStatement.setString(2,req.getParameter("password"));

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    resp.sendRedirect("/finalAssessment/test.html");
                } else {
                    resp.sendRedirect("/finalAssessment/index.html");
                }

                DatabaseConnectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
