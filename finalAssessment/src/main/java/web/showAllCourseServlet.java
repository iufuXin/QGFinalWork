package web;

import pojo.Course;
import util.SearchData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;

@WebServlet("/showAllCourseServlet")
public class showAllCourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取全部课程
        ArrayList<Course> allCourses = SearchData.searchAllCourses();

        //获取职业
        HttpSession session = req.getSession();

        Object occupation = session.getAttribute("occupation");

        //获取在规定时间内的课程
        ArrayList<Course> courses = new ArrayList<>();
//
//        //获得当前时间
//        Date nowDate = Date.from(Instant.now());
        // 获取当前时间的毫秒数
        long currentTimeMillis = System.currentTimeMillis();
        // 使用当前时间的毫秒数创建 java.sql.Date 对象
        java.sql.Date currentDate = new Date(currentTimeMillis);

        for (Course course : allCourses) {
            if (course.getStartTime().compareTo(currentDate.toString()) < 0 && course.getEndTime().compareTo(currentDate.toString()) > 0){
                courses.add(course);
            }
        }

        //存入req域中
        req.setAttribute("courses",courses);
        req.setAttribute("occupation",occupation);

        //转发到.jsp中
        req.getRequestDispatcher("showAllCourse.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
