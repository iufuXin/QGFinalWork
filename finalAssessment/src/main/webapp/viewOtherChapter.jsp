<%--
  Created by IntelliJ IDEA.
  User: 刘付鑫
  Date: 2024/4/25
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看课程信息</title>
</head>
<body>

<h1>查看章节界面</h1>

<h2>课程名字：${course.name}</h2>
<h2>课程编号：${course.id}</h2>

<c:if test="${occupation == 'student'}">
    <h5>注：每个章节的答案只能提交一次，再次提交无效</h5>
</c:if>

<table align="center" border="1" cellspacing="0" width="1000">
    <tr>
        <td>章节序号</td>
        <td>章节描述</td>
        <td>习题简答题数量</td>
        <td>习题上传时间</td>
        <td>习题下载</td>
        <c:if test="${occupation == 'student'}">
            <td>提交答案</td>
        </c:if>
    </tr>
    <c:forEach items="${chapters}" var="chapter">

        <tr>
            <td>${chapter.courseAndChapterNumber.substring(10)}</td>
            <td>${chapter.desc}</td>
            <td>${chapter.shortAnswer}</td>
            <td>${chapter.uploadTime}</td>
            <td>
                <form action="downloadServlet" method="post">
                    <input type="hidden" value="${chapter.fileName}" name="fileName">
                    <input type="hidden" value="${chapter.filePath}" name="filePath">
                    <input type="submit" value="下 载">
                </form>
            </td>
            <c:if test="${occupation == 'student'}">

                <td>
                    <form action="showAddAnswerServlet" method="post">
                        <input type="hidden" value="${chapter.courseAndChapterNumber}" name="courseAndChapterNumber">
                        <input type="hidden" value="${chapter.shortAnswer}" name="shortNumber">
                        <input type="submit" value="提交答案">
                    </form>
                </td>
                <td>
                    <form action="checkStudentAnswerServlet" method="post">
                        <input type="hidden" value="${chapter.courseAndChapterNumber}" name="courseAndChapterNumber">
                        <input type="hidden" value="${occupation}" name="occupation">
                        <input type="submit" value="查看自己答案">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>

<form action="personalHomepageServlet" method="post">
    <input type="submit" value="返回个人主页">
</form>

<c:if test="${occupation == 'student'}">
    <form action="showAllCourseServlet" method="post">
        <input type="submit" value="返 回">
    </form>
</c:if>

<form action="HomePage.jsp" method="post">
    <input type="submit" value="返回主页">
</form>

</body>
</html>
