package web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.SearchData;
import util.incomingData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet("/addChapterServlet")
public class addChapterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String ID = "";
        String course_idAndchapterNumber = "";
        if (req.getParameter("add") != null){
            //用于判断是viewChapter.jsp发送的请求
            ID = req.getParameter("course_id");

            req.setAttribute("course_id",ID);

            req.getRequestDispatcher("/addChapter.jsp").forward(req,resp);
        } else {
            //否则就是addChapter发送的
            try {
                course_idAndchapterNumber = incomingData.addChapter(req, resp);

                if (course_idAndchapterNumber.length() == 10){
                    //只返回了课程id号，证明添加出错
                    req.setAttribute("warning","添加失败，可能该章节已经存在或者输入错误信息");

                    req.setAttribute("course_id",course_idAndchapterNumber);

                    req.getRequestDispatcher("addChapter.jsp").forward(req,resp);

                } else {
                    ID = course_idAndchapterNumber.substring(0,10);

                    req.setAttribute("course_id",ID);

                    req.getRequestDispatcher("/viewChapterServlet").forward(req,resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }


    }
}
