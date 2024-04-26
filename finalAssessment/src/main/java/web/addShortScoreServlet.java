package web;

import util.modifyData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addShortScoreServlet")
public class addShortScoreServlet extends HttpServlet {
    //提交正确率
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String total = req.getParameter("total");
        String score = req.getParameter("score");
        String studentID = req.getParameter("studentID");
        String courseAndChapterNumber = req.getParameter("courseAndChapterNumber");
        String studentName = req.getParameter("studentName");
        String shortAnswer = req.getParameter("shortAnswer");

        Double shortCorrectRate = Double.parseDouble(score)/Double.parseDouble(total);

        //录入简答题正确率
        try {
            modifyData.modifyShortCorrectRate(studentID,courseAndChapterNumber,shortCorrectRate);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("studentID",studentID);
        req.setAttribute("studentName",studentName);
        req.setAttribute("shortAnswer",shortAnswer);
        req.setAttribute("courseAndChapterNumber",courseAndChapterNumber);

        req.getRequestDispatcher("shortAnswerServlet").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
