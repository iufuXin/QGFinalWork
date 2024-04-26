<%--
  Created by IntelliJ IDEA.
  User: 刘付鑫
  Date: 2024/4/18
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主页</title>
</head>
<body>

<h1>欢迎使用QG在线学习平台</h1>

<form action="showAllCourseServlet" method="post">
    <input type="submit" value="查看全部课程">
</form>

<form action="/finalAssessment/personalHomepageServlet" method="post">
    <input type="hidden" name="id" value="${ID}">
    <input type="hidden" name="occupation" value="${occupation}">
    <input type="submit" value="个人主页">
</form>


</body>
</html>
