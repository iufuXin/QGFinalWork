<%--
  Created by IntelliJ IDEA.
  User: 刘付鑫
  Date: 2024/4/20
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>

<div id="loginDiv">
    <form action="/finalAssessment/loginBackgroundCode" method="post" id="form">
        <h1 id="loginMsg">登录</h1>

        <h2><p>${login_msg}</p></h2>

        <label>
            <p>账号:<input id="id" name="id" type="text">（此处输入系统分配的10位数字）</p>
        </label>

        <label>
            <p>密码:<input id="password" name="password" type="password"></p>
        </label>

        <label>
            <input type="radio" name="occupation" value="teacher">
            教师
        </label>

        <label>
            <input type="radio" name="occupation" value="student">
            学生
        </label>

        <div id="subDiv">
            <input type="submit" class="button" value="登录">
            <input type="reset" class="button" value="重置">&nbsp;
            <a href="teacherOrStudent.html">没有账号？点击注册</a>
        </div>
    </form>
</div>


</body>
</html>
