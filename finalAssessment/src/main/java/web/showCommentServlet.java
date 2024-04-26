package web;

import pojo.Comment;
import pojo.Course;
import util.SearchData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/showCommentServlet")
public class showCommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String courseID = "";

        if (req.getParameter("id") != null){
            //是从.jsp文件转发过来的
            courseID = req.getParameter("id");
        } else {
            //从servlet转发过来的
            courseID = (String) req.getAttribute("id");

        }
        System.out.println(courseID);
        //从session 获取对应的用户id和职业
        HttpSession session = req.getSession();
        Object id = session.getAttribute("id");
        Object occupation = session.getAttribute("occupation");

        //存入req域中
        req.setAttribute("id",id.toString());
        req.setAttribute("occupation",occupation.toString());

        Course course = SearchData.searchCoursesByID(courseID);


        //把课程信息存入req域中
        req.setAttribute("course",course);

        //再通过课程id获取评论区
        ArrayList<Comment> comments = SearchData.searchCommentByCourseID(course.getId());

        //把评论信息存入req域中
        req.setAttribute("comments",comments);

        //转发到showComment.jsp中
        req.getRequestDispatcher("showComment.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
