package web;

import pojo.Chapter;
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

@WebServlet("/viewChapterServlet")
public class viewChapterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置服务器端字符编码为UTF-8
        req.setCharacterEncoding("UTF-8");
        String courseID = "";
        HttpSession session = req.getSession();
        Object id = session.getAttribute("id");
        Object occupation = session.getAttribute("occupation");

        req.setAttribute("occupation",occupation);

        //获取课程id
        if (req.getParameter("id") == null){
            //从servlet中获取数据
            courseID = (String) req.getAttribute("course_id");
            //req.setAttribute("warning",req.getAttribute("warning"));
        }else {
            //从req域中获取数据
            courseID = req.getParameter("id");
        }
        //根据课程id搜索相应的章节
        ArrayList<Chapter> chapters = SearchData.searchChapterByID(courseID);

        //根据课程id搜索相应和课程
        Course course = SearchData.searchCoursesByID(courseID);

        //把章节传入域中
        req.setAttribute("chapters",chapters);

        //再把课程传入req域中
        req.setAttribute("course",course);

        System.out.println("viewChapterServlet" + course.getTeacherID().equals(id));

        //用来查看是在哪个页面点的，以防其他老师删除别人的章节
        if (course.getTeacherID().equals(id)){
            //转发到viewChapter.jsp中
            req.getRequestDispatcher("/viewChapter.jsp").forward(req,resp);
        }else {
            req.getRequestDispatcher("/viewOtherChapter.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
