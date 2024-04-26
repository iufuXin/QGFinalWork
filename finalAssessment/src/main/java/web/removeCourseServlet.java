package web;

import util.SearchData;
import util.deleteData;
import util.modifyData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/removeCourseServlet")
public class removeCourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        Object id = session.getAttribute("id");

        String courseID = req.getParameter("courseID");

        try {
            deleteData.deleteStudentCourse(courseID,id.toString());
            //修改课程里面已报名人数
            modifyData.modifyCourseEnrolment(SearchData.searchCoursesByID(courseID),false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("personalHomepageServlet").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
