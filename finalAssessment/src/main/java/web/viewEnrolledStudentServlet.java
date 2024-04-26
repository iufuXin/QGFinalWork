package web;

import pojo.Student;
import util.SearchData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/viewEnrolledStudentServlet")
public class viewEnrolledStudentServlet extends HttpServlet {
    //查看报名学生情况
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println("课程的id为" + req.getParameter("id"));
        //获取报名了这个课程的学生id
        ArrayList<String> studentIDs = SearchData.searchStudentCourseByCourseID(req.getParameter("id"));
        ArrayList<Student> students;
        try {
            students = SearchData.searchStudent(studentIDs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        req.setAttribute("courseID",req.getParameter("id"));
        req.setAttribute("students",students);
        if (req.getParameter("courseName") == null){
            req.setAttribute("courseName",SearchData.searchCoursesByID(req.getParameter("id")).getName());
        }else {
            req.setAttribute("courseName",req.getParameter("courseName"));
        }

        req.getRequestDispatcher("viewEnrolledStudents.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }






}
