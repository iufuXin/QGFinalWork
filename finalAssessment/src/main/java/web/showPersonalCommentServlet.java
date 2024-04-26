package web;

import pojo.Comment;
import util.SearchData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/showPersonalCommentServlet")
public class showPersonalCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        ArrayList<Comment> comments;

        HttpSession session = req.getSession();
        Object id = session.getAttribute("id");

        if ( req.getParameter("courseID") == null){
            //从servlet传过来的数据
            comments = SearchData.searchCommentByUserIDAndCourseID(id.toString(),(String) req.getAttribute("courseID"));
        }else {
            //是从.jsp传输过来的
            comments = SearchData.searchCommentByUserIDAndCourseID(id.toString(),req.getParameter("courseID"));
        }

        req.setAttribute("comments",comments);

        req.setAttribute("courseID",req.getParameter("courseID"));

        req.getRequestDispatcher("showPersonalComment.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
