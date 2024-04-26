package web;

import pojo.Course;
import pojo.Student;
import pojo.Teacher;
import service.DatabaseConnectionPool;
import util.SearchData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet("/personalHomepageServlet")
public class personalHomepageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置服务器端字符编码为UTF-8
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Object id = session.getAttribute("id");
        Object occupation = session.getAttribute("occupation");

        if ( occupation.toString().equals("teacher")){
           //如果是教师的话
            Teacher teacher = SearchData.searchTeacher(id.toString());
            ArrayList<Course> courses;
            if (teacher != null){
                courses = SearchData.searchCoursesByTeacherID(teacher.getId());
                //将数据存储到 req 域中
                req.setAttribute("ID",teacher.getId());
                req.setAttribute("name",teacher.getName());
                req.setAttribute("desc",teacher.getDesc());
                req.setAttribute("qq",teacher.getQq());
                req.setAttribute("email",teacher.getEmail());
                req.setAttribute("occupation","teacher");
                req.setAttribute("courses",courses);
                //转发到 personalHomePage.jsp 中
                req.getRequestDispatcher("/personalHomePage.jsp").forward(req,resp);
            }else {
                //如果是空的话则返回到登录界面，但是应该不会
                resp.sendRedirect("/finalAssessment/index.jsp");
            }
        }else {
            Student student = SearchData.searchStudent(id.toString());
            if (student != null){
                student.setId(student.getId());
                //将数据存储到 req 域中
                req.setAttribute("ID",student.getId());
                req.setAttribute("name",student.getName());
                req.setAttribute("desc",student.getDesc());
                req.setAttribute("grade",student.getGrade());
                req.setAttribute("occupation","student");

                //查找学生对应的课程id
                ArrayList<String> courseIDs = SearchData.searchStudentCourse(student.getId());

                ArrayList<Course> courses = new ArrayList<>();

                for (String courseID : courseIDs) {
                    //再通过id查找课程的具体信息
                    Course course = new Course();
                    course = SearchData.searchCoursesByID(courseID);
                    courses.add(course);
                }

                req.setAttribute("courses",courses);

                //转发到 personalHomePage.jsp 中
                req.getRequestDispatcher("/personalHomePage.jsp").forward(req,resp);
            }else {
                //如果是空的话则返回到登录界面，但是应该不会
                resp.sendRedirect("/finalAssessment/index.html");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
