package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/shortAnswerServlet")
public class shortAnswerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Object occupation = session.getAttribute("occupation");

        String studentID = req.getParameter("studentID");
        String studentName = req.getParameter("studentName");
        String shortAnswer = req.getParameter("shortAnswer");
        String courseAndChapterNumber = req.getParameter("courseAndChapterNumber");
        String shortCorrectRate = req.getParameter("shortCorrectRate");

        req.setAttribute("occupation",occupation);
        req.setAttribute("studentID",studentID);
        req.setAttribute("studentName",studentName);
        req.setAttribute("shortAnswer",shortAnswer);
        req.setAttribute("courseAndChapterNumber",courseAndChapterNumber);
        req.setAttribute("shortCorrectRate",shortCorrectRate);

        req.getRequestDispatcher("shortAnswer.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
