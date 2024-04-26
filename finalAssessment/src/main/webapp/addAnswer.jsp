<%--
  Created by IntelliJ IDEA.
  User: 刘付鑫
  Date: 2024/4/24
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>提交答案</title>
</head>
<body>


<form action="addAnswerServlet" method="post">
    <input type="hidden" value="${courseAndChapterNumber}" name="courseIDChapterNumber">
    <input type="hidden" value="${shortNumber}" name="shortNumber">
    选择题答案：<input type="text" name="singleTopicAnswer">请按顺序输入答案<br>
    判断题答案：<input type="text" name="judgmentAnswer">请按顺序输入答案<br>
    <c:if test="${shortNumber != 0}">
        <c:forEach begin="1" end="${shortNumber}" varStatus="loop">
            <label>
                简答题第${loop.index}题：
            <textarea cols="20" rows="5" name="textBox${loop.index}"></textarea><br>
            </label>
        </c:forEach>
    </c:if>
    <input type="submit" value="提 交">
</form>

<form method="post" action="viewChapterServlet">
    <input type="hidden" value="" name="id">
    <input type="submit" value="返 回">
</form>

</body>
</html>
