package web;

import util.deleteData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 删除课程
 */
@WebServlet("/deleteCourseServlet")
public class deleteCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取课程id
        String courseID = req.getParameter("id");

        //传入课程id来执行删除操作
        try {
            if (deleteData.deleteCourse(courseID)){
                //删除成功，重新更新个人主页
                resp.sendRedirect("/finalAssessment/personalHomepageServlet");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
