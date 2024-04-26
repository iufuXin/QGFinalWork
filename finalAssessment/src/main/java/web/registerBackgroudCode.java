package web;

import util.checkData;
import util.incomingData;
import service.DatabaseConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/registerBackgroudCode")

public class registerBackgroudCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //设置服务器端字符编码为UTF-8
        req.setCharacterEncoding("UTF-8");
        //获取数据库连接
        DatabaseConnectionPool.SimpleConnectionPool();

        if ( req.getParameter("password").equals(req.getParameter("confirmPassword"))){
            //检查两次密码输入是否一样，一样再的话再进来
            //定义编号
            String ID = "";
            if ( req.getParameter("occupation").equals("teacher") ){
                System.out.println(Objects.equals(req.getParameter("name"), ""));
                if (Objects.equals(req.getParameter("name"), "") || Objects.equals(req.getParameter("password"), "") || Objects.equals(req.getParameter("email"), "") || Objects.equals(req.getParameter("qq"), "") || !checkData.checkQQ(req.getParameter("qq"))){
                    //用户输入空信息
                    System.out.println("用户输入空信息");
                    req.getRequestDispatcher("/registrationFails.jsp").forward(req,resp);
                }else {
                    if (checkData.checkEmail(req.getParameter("email"))) {
                        ID = incomingData.incomeTeacherData(req, resp);
                    } else {
                        req.getRequestDispatcher("/registrationFails.jsp").forward(req,resp);
                    }
                }
                //判断是教师还是学生
//                if (checkData.checkEmail(req.getParameter("email")) && checkData.checkQQ(req.getParameter("qq"))){
//                    //检验email 和 qq是否符合格式


                //System.out.println("email和qq不符合");
                //resp.sendRedirect("/finalAssessment/registrationFails.jsp");
            } else {
                if (Objects.equals(req.getParameter("name"), "") || Objects.equals(req.getParameter("password"), "") || Objects.equals(req.getParameter("grade"), "")){
                    //用户输入空信息
                    req.getRequestDispatcher("/registrationFails.jsp").forward(req,resp);
                }else {
                    ID = incomingData.incomeStudentData(req, resp);
                    System.out.println(ID);
                }
            }
            if (Objects.equals(ID, "")){
                System.out.println("密码太简单");
                System.out.println(ID);
                //因为密码太简单导致的返回 null
                req.getRequestDispatcher("/registrationFails.jsp");
            }else {
                //2，将ID存储到req域中
                req.setAttribute("ID",ID);

                //3，转发到 showID.jsp中
                req.getRequestDispatcher("/showID.jsp").forward(req,resp);
            }
        }else {
            resp.sendRedirect("/finalAssessment/registrationFails.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
