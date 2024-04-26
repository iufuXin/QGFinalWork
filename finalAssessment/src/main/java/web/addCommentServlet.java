package web;

import util.incomingData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addCommentServlet")
public class addCommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String comment = "";

        System.out.println("字符长度为" + req.getParameter("comment").length());
        System.out.println(req.getParameter("comment").length() <= 150);

        if (req.getParameter("comment") != null && req.getParameter("comment").length() <= 150){
            //传进来的评论不是空的
            comment = req.getParameter("comment");

            //添加进库
            incomingData.incomeComment(req,resp);
        } else {
            //传进来的评论是空的
            req.setAttribute("id",req.getParameter("courseID"));
//            //重新转发到showCommentServlet
//            req.getRequestDispatcher("/showCommentServlet").forward(req,resp);
        }

        req.setAttribute("id",req.getParameter("courseID"));
        //转发回showCommentServlet
        req.getRequestDispatcher("/showCommentServlet").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
