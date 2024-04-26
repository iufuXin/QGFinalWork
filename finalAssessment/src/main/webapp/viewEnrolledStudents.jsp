<%--
  Created by IntelliJ IDEA.
  User: 刘付鑫
  Date: 2024/4/24
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看报名学生</title>
</head>
<body>

<h1>已经报名了${courseName}的学生如下</h1>

<table align="center" border="1" cellspacing="0" width="1000">
    <tr>
        <td>学生名字</td>
        <td>学生id</td>
        <td>学生年级</td>
        <td></td>
    </tr>
    <c:forEach items="${students}" var="student">

        <tr>
            <td>${student.name}</td>
            <td>${student.id}</td>
            <td>${student.grade}</td>
            <td>
                <form action="checkStudentCompletionServlet" method="post">
                    <input type="hidden" value="${courseID}" name="courseID">
                    <input type="hidden" value="${student.id}" name="studentID">
                    <input type="submit" value="查看学生完成情况">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<form action="personalHomepageServlet" method="post">
    <input type="submit" value="返 回">
</form>

</body>
</html>
