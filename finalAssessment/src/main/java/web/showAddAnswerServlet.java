package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showAddAnswerServlet")
public class showAddAnswerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String courseAndChapterNumber = req.getParameter("courseAndChapterNumber");
        String shortNumber = req.getParameter("shortNumber");

        System.out.println(courseAndChapterNumber);

        req.setAttribute("courseAndChapterNumber",courseAndChapterNumber);
        req.setAttribute("shortNumber",shortNumber);

        req.getRequestDispatcher("addAnswer.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
