<%--
  Created by IntelliJ IDEA.
  User: 刘付鑫
  Date: 2024/4/23
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>全部课程</title>
</head>
<body>

<h1>全部课程如下</h1>

<table align="center" border="1" cellspacing="0" width="1000">
    <tr>
        <td>课程名字</td>
        <td>课程描述</td>
        <td>课程开始时间</td>
        <td>课程结束时间</td>
        <td>报名限制人数</td>
        <td>已报名人数</td>
        <td>授课老师</td>
        <c:if test="${occpuation == 'student'}">
            <td></td>
        </c:if>
        <td></td>
        <td></td>
    </tr>
    <c:forEach items="${courses}" var="course">
        <tr>
            <td>${course.name}</td>
            <td>${course.desc}</td>
            <td>${course.startTime}</td>
            <td>${course.endTime}</td>
            <td>${course.maxEnrolment}</td>
            <td>${course.enrolment}</td>
            <td>${course.teacherName}</td>
            <td>
                <form action="viewChapterServlet" method="post">
                    <input type="hidden" value="${course.id}" name="id">
                    <input type="submit" value="查看课程具体信息">
                </form>
            </td>
            <c:if test="${occupation == 'student'}">
                <td>
                    <form action="addCourseInStudentServlet" method="post">
                        <input type="hidden" value="${course.id}" name="id">
                        <input type="submit" value="添加进我的课程">
                    </form>
                </td>
            </c:if>
            <td>
                <form action="showCommentServlet" method="post">
                    <input type="hidden" value="${course.id}" name="id">
                    <input type="submit" value="进入讨论区">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<form action="HomePage.jsp" method="post">
    <input type="submit" value="返回主页">
</form>

</body>
</html>
