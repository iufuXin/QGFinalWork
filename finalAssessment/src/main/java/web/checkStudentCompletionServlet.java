package web;

import pojo.Chapter;
import pojo.Course;
import pojo.Student;
import util.SearchData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/checkStudentCompletionServlet")
public class checkStudentCompletionServlet extends HttpServlet {
    //查看学生完成情况
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String courseID = req.getParameter("courseID");
        String studentID = req.getParameter("studentID");

        System.out.println("checkStudentCompletionServlet里面的" + courseID);
        System.out.println("checkStudentCompletionServlet里面的" + studentID);

        Student student = SearchData.searchStudent(studentID);
        ArrayList<Chapter> chapters = SearchData.searchChapterByID(courseID);

        req.setAttribute("student",student);
        req.setAttribute("chapters",chapters);
        req.setAttribute("courseID",courseID);

        req.getRequestDispatcher("checkStudentCompletion.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
