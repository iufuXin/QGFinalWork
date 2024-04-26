<%--
  Created by IntelliJ IDEA.
  User: 刘付鑫
  Date: 2024/4/24
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看学生完成情况</title>
</head>
<body>

<h2>这是${student.name}的完成情况</h2>

<table align="center" border="1" cellspacing="0" width="1000">
    <tr>
        <td>章节序号</td>
        <td>章节描述</td>
        <td>章节简答题数量</td>
        <td>章节上传时间</td>
        <td></td>
    </tr>
    <c:forEach items="${chapters}" var="chapter">
        <tr>
            <td>${chapter.courseAndChapterNumber.substring(10)}</td>
            <td>${chapter.desc}</td>
            <td>${chapter.shortAnswer}</td>
            <td>${chapter.uploadTime}</td>
            <td>
                <form action="checkStudentAnswerServlet" method="post">
                    <input type="hidden" value="${student.id}" name="studentID">
                    <input type="hidden" value="${student.name}" name="studentName">
                    <input type="hidden" value="${chapter.courseAndChapterNumber}" name="courseAndChapterNumber">
                    <input type="submit" value="查看答题情况">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<form action="viewEnrolledStudentServlet" method="post">
    <input type="hidden" value="${courseID}" name="id">
    <input type="submit" value="返 回">
</form>

</body>
</html>
