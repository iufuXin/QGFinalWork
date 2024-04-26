package web;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@WebServlet("/downloadServlet")
public class downloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //1，获取要下载的文件名
        String downloadFileName = req.getParameter("fileName");

        System.out.println(downloadFileName);
        //2，读取要下载的文件内容（通过servletContext对象可以读取）
        ServletContext servletContext = getServletContext();
        //获取要下载的文件类型
        String mimeType = servletContext.getMimeType("/uploadingFiles/" + downloadFileName);
        //String mimeType = servletContext.getMimeType(req.getParameter("filePath"));
        System.out.println("下载的文件类型：" + mimeType);

        //在回传前，通过响应头告诉客户端返回的数据类型
        resp.setContentType(mimeType);
        //5，还要告诉客户端收到的数据是用于下载使用的，（还是使用响应头）
        //Content-Disposition响应头，表示收到的数据怎么处理
        //attachment表示附件，表示下载使用
        //filename= 表示指定的下载文件名
        //url编码是把汉字转换成为%xx%xx的格式
        resp.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(downloadFileName,"UTF-8"));

        /**
         * /斜杠被服务器解析表示地址为http//ip:port/工程名/ 映射到代码的web目录
         */
        InputStream resourceAsStream = servletContext.getResourceAsStream("/uploadingFiles/" + downloadFileName);
        //InputStream resourceAsStream = servletContext.getResourceAsStream(req.getParameter("filePath"));
        //获取响应的输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        //读取输入流中全部的数据，复制给输出流，输出给客户端
        IOUtils.copy(resourceAsStream,outputStream);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
