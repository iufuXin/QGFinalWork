<%--
  Created by IntelliJ IDEA.
  User: 刘付鑫
  Date: 2024/4/24
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看学生答题情况</title>
</head>
<body>

<h2>${studentName}的答题情况如下</h2>

<table align="center" border="1" cellspacing="0" width="1000">
    <tr>
        <td>单选题答案</td>
        <td>单选题正确率</td>
        <td>判断题答案</td>
        <td>判断题正确率</td>
        <td>简答题正确率</td>
        <td>答案上传时间</td>
        <td></td>
    </tr>
    <c:forEach items="${studentAnswers}" var="studentAnswer">
        <tr>
            <td>${studentAnswer.singleTopicAnswer}</td>
            <td>${studentAnswer.singleTopicCorrectRate}</td>
            <td>${studentAnswer.judgmentAnswer}</td>
            <td>${studentAnswer.judgmentCorrectRate}</td>
            <c:if test="${studentAnswer.shortCorrectRate == -1}">
                <td>还没评分</td>
            </c:if>
            <c:if test="${studentAnswer.shortCorrectRate != -1}">
                <td>${studentAnswer.shortCorrectRate}</td>
            </c:if>
            <td>${studentAnswer.uploadTime}</td>
            <td>
                <form action="shortAnswerServlet" method="post">
                    <input type="hidden" value="${studentID}" name="studentID">
                    <input type="hidden" value="${studentName}" name="studentName">
                    <input type="hidden" value="${studentAnswer.shortAnswer}" name="shortAnswer">
                    <input type="hidden" value="${courseAndChapterNumber}" name="courseAndChapterNumber">
                    <input type="hidden" value="${studentAnswer.shortCorrectRate}" name="shortCorrectRate">
                    <input type="submit" value="查看简答题答案">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${occupation == 'teacher'}">
    <form action="checkStudentCompletionServlet" method="post">
        <input type="hidden" value="${studentID}" name="studentID">
        <input type="hidden" value="${courseAndChapterNumber.substring(0,10)}" name="courseID">
        <input type="submit" value="返 回">
    </form>
</c:if>

<c:if test="${occupation == 'student'}">
    <form action="viewChapterServlet" method="post">
        <input type="hidden" value="${courseAndChapterNumber.substring(0,10)}" name="id">
        <input type="hidden" value="${occupation}" name="occupation">
        <input type="submit" value="返 回">
    </form>
</c:if>

</body>
</html>
