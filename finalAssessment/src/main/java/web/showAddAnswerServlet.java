package web;

import pojo.Course;
import util.SearchData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/showAddAnswerServlet")
public class showAddAnswerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String courseAndChapterNumber = req.getParameter("courseAndChapterNumber");
        String shortNumber = req.getParameter("shortNumber");

        Course course = SearchData.searchCoursesByID(courseAndChapterNumber.substring(0, 10));


        // 获取当前时间的毫秒数
        long currentTimeMillis = System.currentTimeMillis();
        // 使用当前时间的毫秒数创建 java.sql.Date 对象
        java.sql.Date currentDate = new Date(currentTimeMillis);

        if (course.getStartTime().compareTo(currentDate.toString()) < 0 && course.getEndTime().compareTo(currentDate.toString()) > 0){
            req.setAttribute("courseAndChapterNumber",courseAndChapterNumber);
            req.setAttribute("shortNumber",shortNumber);

            req.getRequestDispatcher("addAnswer.jsp").forward(req,resp);
        }else {
            req.setAttribute("course_id",courseAndChapterNumber.substring(0,10));

            req.getRequestDispatcher("viewChapterServlet").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
