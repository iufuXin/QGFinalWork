<%--
  Created by IntelliJ IDEA.
  User: 刘付鑫
  Date: 2024/4/24
  Time: 0:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>评论区</title>
</head>
<body>

<h1>${course.name}课程评论区如下</h1>

<table align="center" border="1" cellspacing="0" width="1000">
    <c:forEach items="${comments}" var="comment">
        <tr>
            <td>${comment.userName}
                <c:if test="${comment.occupation == 'teacher'}">老师
                </c:if>
                <c:if test="${comment.occupation == 'student'}">学生
                </c:if>：${comment.comment}</td>
        </tr>
    </c:forEach>
</table>

<p align="center">
    <form action="addCommentServlet" method="post">
        <input type="hidden" value="${id}" name="userID">
        <input type="hidden" value="${occupation}" name="occupation">
        <input type="hidden" value="${course.id}" name="courseID">
        <input type="hidden" value="${userName}" name="userName">
        <textarea rows="5" cols="30" name="comment"></textarea>注：不要超过100个字
        <input type="submit" value="发表评论">
    </form>
</p>

<form action="showPersonalCommentServlet" method="post">
    <input type="hidden" value="${id}" name="userID">
    <input type="hidden" value="${course.id}" name="courseID">
    <input type="submit" value="删除我的部分评论">
</form>


<form action="showAllCourseServlet" method="post">
    <input type="submit" value="返 回">
</form>


</body>
</html>
