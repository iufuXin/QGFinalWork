package web;

import util.deleteData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteCommentServlet")
public class deleteCommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String commentID = req.getParameter("commentID");
        String courseID = req.getParameter("courseID");

        try {
            deleteData.deleteComment(commentID);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //设置要传输的文件
        req.setAttribute("courseID",courseID);

        //回到个人评论展示界面
        req.getRequestDispatcher("/showPersonalCommentServlet").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
