<%--
  Created by IntelliJ IDEA.
  User: 刘付鑫
  Date: 2024/4/18
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>个人主页</title>
</head>
<body>

<c:if test="${occupation == 'teacher'}">
    <h1 align="center">欢迎${name}老师</h1>
</c:if>

<c:if test="${occupation == 'student'}">
    <h1 align="center">欢迎${name}学生</h1>
</c:if>

<h4 align="center">编号：${ID}

个人描述：
<c:if test="${desc == ''}">
    这个人很懒，什么都没有留下
</c:if>

${desc}
</h4>

<c:if test="${occupation == 'teacher'}">
    <p align="center">你的QQ：${qq}
    你的email：${email}
    你现在开设的课程：</p>

    <table align="center" border="1" cellspacing="0" width="1000">
        <tr>
            <td>课程名字</td>
            <td>课程描述</td>
            <td>课程开始时间</td>
            <td>课程结束时间</td>
            <td>报名限制人数</td>
            <td>已报名人数</td>
            <td></td>
            <td></td>
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
            <td>
                <form action="echoCourseServlet" method="post">
                    <input type="hidden" value="${course.id}" name="id">
                    <input type="submit" value="修改课程信息">
                </form>
            </td>
            <td>
                <form action="deleteCourseServlet" method="post">
                    <input type="hidden" value="${course.id}" name="id">
                    <input type="submit" value="删除课程">
                </form>
            </td>
                <td>
                    <form action="viewEnrolledStudentServlet" method="post">
                        <input type="hidden" value="${course.id}" name="id">
                        <input type="hidden" value="${course.name}" name="courseName">
                        <input type="submit" value="查看报名学生">
                    </form>
                </td>
                <td>
                    <form action="viewChapterServlet" method="post">
                        <input type="hidden" value="${course.id}" name="id">
                        <input type="submit" value="查看课程章节">
                    </form>
                </td>
            </tr>
    </c:forEach>
    </table>
</c:if>

<c:if test="${occupation == 'student'}">
    <h4 align="center">你的年级：${grade}级
        你选择的课程：
    </h4>
    <table align="center" border="1" cellspacing="0" width="1000">
        <tr>
            <td>课程名字</td>
            <td>课程描述</td>
            <td>课程开始时间</td>
            <td>课程结束时间</td>
            <td>报名限制人数</td>
            <td>已报名人数</td>
            <td>授课老师</td>
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
                <td>
                    <form action="removeCourseServlet" method="post">
                        <input type="hidden" value="${course.id}" name="courseID">
                        <input type="submit" value="移 除">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</c:if>

<form action="/finalAssessment/moditfyDataServlet" method="post">
    <input type="submit" value="修改个人信息">
</form>

<c:if test="${occupation == 'teacher'}">
    <form action="addCourse.html" method="post">
        <input type="submit" value="创建新课程">
    </form>
</c:if>

<form action="HomePage.jsp" method="post">
    <input type="submit" value="返回主页">
</form>

<form action="index.jsp" method="post">
    <input type="submit" value="退出登录">
</form>



</body>
</html>
