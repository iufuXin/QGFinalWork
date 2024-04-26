package web;

import util.incomingData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addAnswerServlet")
public class addAnswerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //添加答案
        try {
            incomingData.incomeStudentAnswer(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        //获取课程id
        String courseID = req.getParameter("courseIDChapterNumber").substring(0, 10);

        req.setAttribute("course_id",courseID);

        req.getRequestDispatcher("viewChapterServlet").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
