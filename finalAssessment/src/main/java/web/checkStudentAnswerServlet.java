package web;

import pojo.StudentAnswer;
import util.SearchData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/checkStudentAnswerServlet")
public class checkStudentAnswerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String studentID = "";
        String studentName = "";
        String courseAndChapterNumber =  req.getParameter("courseAndChapterNumber");

        if (req.getParameter("studentID") == null){
            HttpSession session = req.getSession();

            studentID = session.getAttribute("id").toString();
            studentName = SearchData.searchStudent(studentID).getName();
        }else {
            studentID = req.getParameter("studentID");
            studentName = req.getParameter("studentName");
        }

        ArrayList<StudentAnswer> studentAnswers = SearchData.searchStudentAnswer(studentID,courseAndChapterNumber);

        System.out.println(studentAnswers);

        req.setAttribute("studentName",studentName);
        req.setAttribute("studentAnswers",studentAnswers);
        req.setAttribute("studentID",studentID);
        req.setAttribute("courseAndChapterNumber",courseAndChapterNumber);
        req.setAttribute("occupation",req.getAttribute("occupation"));

        req.getRequestDispatcher("checkStudentAnswer.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
