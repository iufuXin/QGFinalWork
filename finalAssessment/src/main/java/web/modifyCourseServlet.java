package web;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pojo.Course;
import pojo.Student;
import pojo.Teacher;
import service.DatabaseConnectionPool;
import util.SearchData;
import util.modifyData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.regex.Pattern;

import static util.incomingData.changeTimeFormat;

@WebServlet("/modifyCourseServlet")
public class modifyCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置服务器端字符编码为UTF-8
        req.setCharacterEncoding("UTF-8");
        // 设置响应类型为 JSON
        resp.setContentType("application/json");
        // 获取teacherID
        HttpSession session = req.getSession();

        // 获取请求体中的 JSON 数据
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();

        // 解析 JSON 数据
        JsonObject json = JsonParser.parseString(requestBody).getAsJsonObject();
        String id = json.get("id").getAsString();
        String courseName = json.get("courseName").getAsString();
        String courseDescription = json.get("courseDescription").getAsString();
        String startTime = json.get("startTime").getAsString();
        String endTime = json.get("endTime").getAsString();
        int maxParticipants = json.get("maxParticipants").getAsInt();

        //用于匹配相应的时间格式
        String regex = "^\\b(?:Sun|Mon|Tue|Wed|Thu|Fri|Sat)\\b\\s(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s\\d{1,2}\\\\s(20(?:0[0-9]|1[0-9]|2[0-2]))$";
        Pattern pattern = Pattern.compile(regex);
        //检验时间是否符合格式
        if (!pattern.matcher(startTime).matches() || !pattern.matcher(endTime).matches() || maxParticipants <= 0 || courseName.isEmpty()){
            //不符合格式的话
            req.setAttribute("id",id);
            //输入警告
            req.setAttribute("warning","时间输入格式有误！！！");
            //转发到 modifyCourse.jsp中
            //resp.sendRedirect("/finalAssessment/echoCourseServlet");
            req.getRequestDispatcher("/echoCourseServlet").forward(req,resp);
        }

        Course course = new Course();
        course.setTeacherID(session.getAttribute("id").toString());
        course.setId(id);
        course.setName(courseName);
        course.setDesc(courseDescription);
        course.setStartTime(changeTimeFormat(startTime).toString());
        course.setEndTime(changeTimeFormat(endTime).toString());
        course.setMaxEnrolment(maxParticipants);

        System.out.println(course.getStartTime());

        System.out.println(id);
        try {
            if (modifyData.modifyCourseData(course)){
                //修改完数据后重定向到个人主页
                resp.sendRedirect("/finalAssessment/personalHomepageServlet");
            } else {
                //否则的话则返回到登录界面，但是应该不会
                resp.sendRedirect("/finalAssessment/index.jsp");
            }

            //转发到 moditfyData.jsp中
            //req.getRequestDispatcher("/moditfyData.jsp").forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
