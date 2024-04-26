package web;

import pojo.Student;
import pojo.Teacher;
import util.SearchData;
import util.modifyData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/moditfyDataServlet")
public class moditfyDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置服务器端字符编码为UTF-8
        req.setCharacterEncoding("UTF-8");
        //获取 Session 对象来查看当前是老师还是学生
        HttpSession session = req.getSession();

        if ( session.getAttribute("occupation").equals("teacher")){
            //如果是老师的话
            Teacher teacher = SearchData.searchTeacher(session.getAttribute("id").toString());

            //把老师数据存回req域中
            req.setAttribute("teacher",teacher);
            req.setAttribute("occupation","teacher");

        }else {
            //是学生的话
            Student student = SearchData.searchStudent(session.getAttribute("id").toString());

            //把老师数据存回req域中
            req.setAttribute("student",student);
            req.setAttribute("occupation","student");
        }

        //转发到 moditfyData.jsp中
        req.getRequestDispatcher("/moditfyData.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
