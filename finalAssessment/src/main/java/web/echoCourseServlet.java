package web;

import pojo.Course;
import util.SearchData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/echoCourseServlet")
public class echoCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置服务器端字符编码为UTF-8
        req.setCharacterEncoding("UTF-8");

        //获取课程 id
        String ID = "";
        if (req.getParameter("id") == null){
            //从servlet发送过来的
            ID = (String) req.getAttribute("id");
        } else {
            ID = req.getParameter("id");
        }

        System.out.println(req.getParameter("id"));

        //获取课程数据并回显
        Course course = SearchData.searchCoursesByID(ID);

        //将数据存入 req域中
        req.setAttribute("course",course);

        //转发到 modifyCourse.jsp
        req.getRequestDispatcher("/modifyCourse.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
