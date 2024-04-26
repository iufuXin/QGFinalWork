package web;

import pojo.Course;
import pojo.Student;
import pojo.StudentCourse;
import util.SearchData;
import util.incomingData;
import util.modifyData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addCourseInStudentServlet")
public class addCourseInStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("UTF-8");

        //获取学生id
        HttpSession session = req.getSession();
        Object studentID = session.getAttribute("id");

        //获取课程id
        String courseID = req.getParameter("id");

        StudentCourse studentCourse = new StudentCourse(studentID.toString(),courseID);

        //获取课程信息
        Course course = SearchData.searchCoursesByID(studentCourse.getCourseID());
        if (course.getEnrolment() >= course.getMaxEnrolment()){
            //已经到达最大报名人数
        }else {
            //未到达最大报名人数
            try {
                //存进数据库里面
                if (incomingData.incomeStudentCourseData(studentCourse)){
                    modifyData.modifyCourseEnrolment(course,true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        req.getRequestDispatcher("showAllCourseServlet").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
