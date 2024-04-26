<%--
  Created by IntelliJ IDEA.
  User: 刘付鑫
  Date: 2024/4/24
  Time: 2:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>你发表的评论</title>
</head>
<body>

<table align="center" border="1" cellspacing="0" width="1000">
    <c:forEach items="${comments}" var="comment">
        <tr>
            <td>${comment.comment}</td>
            <td>
                <form action="deleteCommentServlet" method="post">
                    <input type="hidden" value="${comment.id}" name="commentID">
                    <input type="hidden" value="${courseID}" name="courseID">
                    <input type="submit" value="删 除">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<form action="showCommentServlet" method="post">
    <input type="hidden" value="${courseID}" name="id">
    <input type="submit" value="返回">
</form>

</body>
</html>
