<%--
  Created by IntelliJ IDEA.
  User: 刘付鑫
  Date: 2024/4/25
  Time: 1:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生简答题答案</title>
</head>
<body>

<table align="center" border="1" cellspacing="0" width="1000">
    <tr>
        <td>${shortAnswer}</td>
        <td>
            <form action="addShortScoreServlet" method="post">
                <c:if test="${shortCorrectRate == '-1.0'}">
                    <c:if test="${occupation == 'teacher'}">
                        <label>
                            该简答题的总分：<input type="text" name="total">
                        </label><br>
                        <label>
                            该简答题的评分：<input type="text" name="score">
                        </label><br>
                        <input type="hidden" value="${studentID}" name="studentID">
                        <input type="hidden" value="${studentName}" name="studentName">
                        <input type="hidden" value="${shortAnswer}" name="shortAnswer">
                        <input type="hidden" value="${courseAndChapterNumber}" name="courseAndChapterNumber">
                        <input type="submit" value="提交评分">
                    </c:if>
                    <c:if test="${occupation == 'student'}">
                        老师未评分
                    </c:if>
                </c:if>
                <c:if test="${shortCorrectRate != '-1.0'}">
                    已评分！
                </c:if>
            </form>
        </td>
    </tr>

</table>

<form action="checkStudentAnswerServlet" method="post">
    <input type="hidden" value="${studentID}" name="studentID">
    <input type="hidden" value="${studentName}" name="studentName">
    <input type="hidden" value="${courseAndChapterNumber}" name="courseAndChapterNumber">
    <input type="submit" value="返 回">
</form>

</body>
</html>
