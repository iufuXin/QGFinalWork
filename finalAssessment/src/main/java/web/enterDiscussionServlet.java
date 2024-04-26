package web;

import pojo.Student;
import pojo.Teacher;
import util.SearchData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/enterDiscussionServlet")
public class enterDiscussionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String courseID = req.getParameter("id");

        Teacher teacher = new Teacher();
        Student student = new Student();

        //获取当前用户的数据
        HttpSession session = req.getSession();
        Object id = session.getAttribute("id");
        Object occupation = session.getAttribute("occupation");




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
