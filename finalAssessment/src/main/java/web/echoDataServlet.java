package web;

import pojo.Student;
import pojo.Teacher;
import util.SearchData;
import util.modifyData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/echoDataServlet")
public class echoDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置服务器端字符编码为UTF-8
        req.setCharacterEncoding("UTF-8");
        //获取 Session 对象来查看当前是老师还是学生
        HttpSession session = req.getSession();

        //修改session里面的值
        session.setAttribute("userName",req.getParameter("name"));
        try {
            //修改评论里面的名字
            modifyData.modifyCommentData(session.getAttribute("id").toString(),session.getAttribute("userName").toString());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if ( session.getAttribute("occupation").equals("teacher")){
            try {
                if (modifyData.modifyTeacherData(session.getAttribute("id").toString(),req,resp)){
                    //修改课程里面对应的老师名字
                    modifyData.modifyCourseData(session.getAttribute("userName").toString(),session.getAttribute("id").toString());
                    System.out.println("修改了老师的名字");
                    //修改完数据后重定向到个人主页
                    resp.sendRedirect("/finalAssessment/personalHomepageServlet");
                }else {
                    //否则的话则返回到登录界面，但是应该不会
                    resp.sendRedirect("/finalAssessment/index.jsp");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                if (modifyData.modifyStudentData(session.getAttribute("id").toString(),req,resp)){
                    //修改完数据后重定向到个人主页
                    resp.sendRedirect("/finalAssessment/personalHomepageServlet");
                }else {
                    //否则的话则返回到登录界面，但是应该不会
                    resp.sendRedirect("/finalAssessment/index.jsp");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
