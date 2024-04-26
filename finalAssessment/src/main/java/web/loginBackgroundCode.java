package web;

import pojo.Student;
import pojo.Teacher;
import service.DatabaseConnectionPool;
import util.CreateDatabase;
import util.SearchData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/loginBackgroundCode")
public class loginBackgroundCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置服务器端字符编码为UTF-8
        req.setCharacterEncoding("UTF-8");
        //获取数据库连接
        DatabaseConnectionPool.SimpleConnectionPool();

        try {
            CreateDatabase.createDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DatabaseConnectionPool.getConnection();
            //定义sql语句
            String sql = null;
            Teacher teacher = new Teacher();
            Student student = new Student();
            if (req.getParameter("occupation") == null){
                //登录失败
                req.setAttribute("login_msg","未选择是老师或者学生！！！");
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            } else {
                if ( req.getParameter("occupation").equals("teacher")){
                    sql = "select * from teacherinformation where id = ? and password = ?;";
                    teacher = SearchData.searchTeacher(req.getParameter("id"));
                }else {
                    sql = "select * from studentinformation where id = ? and password = ?;";
                    student = SearchData.searchStudent(req.getParameter("id"));
                }
                //获取sql执行的对象
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,req.getParameter("id"));
                preparedStatement.setString(2,req.getParameter("password"));

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    //将 ID 存储到 req 域中
                    req.setAttribute("ID",req.getParameter("id"));
                    req.setAttribute("occupation",req.getParameter("occupation"));


                    //把信息存储在 Session 中，让信息共享
                    HttpSession session = req.getSession();
                    session.setAttribute("id",req.getParameter("id"));
                    session.setAttribute("occupation",req.getParameter("occupation"));
                    session.setAttribute("userName",resultSet.getString("name"));

                    //3，转发到 HomePage.jsp 中
                    req.getRequestDispatcher("/HomePage.jsp").forward(req,resp);
                    //resp.sendRedirect("/finalAssessment/HomePage.jsp");
                } else {
                    //登录失败
                    req.setAttribute("login_msg","用户名或密码错误！！！");
                    req.getRequestDispatcher("/index.jsp").forward(req,resp);
                }
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
