package web;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import util.incomingData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet("/addCourseServlet")
public class addCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应类型为 JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        Object id = session.getAttribute("id");

        String ID = "";
        ID = incomingData.incomeCourseData(req, resp,id.toString());//将数据传入添加的函数中

        System.out.println(ID);

        if (ID == null){
            //是null的话就是输入格式有问题，重定向到addFailureCourse.html
            // 设置重定向响应头
            resp.setHeader("X-Redirect", "/finalAssessment/addFailureCourse.html");
            //resp.sendRedirect("/finalAssessment/addFailureCourse.html");
        } else {
            req.setAttribute("ID",ID);
//        System.out.println(ID);
            req.getRequestDispatcher("/personalHomepageServlet").forward(req,resp);
            //resp.sendRedirect("/finalAssessment/personalHomepageServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
