package web;

import util.deleteData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteChapterServlet")
public class deleteChapterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String courseAndChapterNumber = req.getParameter("courseAndChapterNumber");

        try {
            //删除章节
            deleteData.deleteChapter(courseAndChapterNumber);

            //删除答案
            deleteData.deleteStudentAnswer(courseAndChapterNumber);

            String ID = courseAndChapterNumber.substring(0,10);

            req.setAttribute("course_id",ID);

            req.getRequestDispatcher("/viewChapterServlet").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
