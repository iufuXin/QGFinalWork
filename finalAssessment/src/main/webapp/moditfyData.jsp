<%--
  Created by IntelliJ IDEA.
  User: 刘付鑫
  Date: 2024/4/20
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改数据</title>
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1>修改数据界面</h1>
    </div>
    <form id="reg-form" action="/finalAssessment/echoDataServlet" method="post">
        <c:if test="${occupation == 'teacher'}">
            <label>
                <table>
                    <tr>
                        <td>用户名：</td>
                        <td class="inputs">
                            <input name="name" type="text" value="${teacher.name}">（此处为你的名字）
                            <br>
                        </td>
                    </tr>
                </table>
            </label>

            <label>
                <br>
                个人介绍：
                <textarea cols="20" rows="5" name="desc">${teacher.desc}</textarea>（非必填）
                <br>
            </label>

            <label>qq：</label>
            <input type="text" id="qq" name="qq" value="${teacher.qq}"><br>

            <label>email：</label>
            <input type="text" id="email" name="email" value="${teacher.email}"><br>
        </c:if>

        <c:if test="${occupation == 'student'}">
            <label>
                <table>
                    <tr>
                        <td>用户名：</td>
                        <td class="inputs">
                            <input name="name" type="text" value="${student.name}">（此处为你的名字）
                            <br>
                        </td>
                    </tr>
                </table>
            </label>

            <label>
                <br>
                个人介绍：
                <textarea cols="20" rows="5" name="desc">${student.desc}</textarea>（非必填）
                <br>
            </label>

            <label>
                <table>
                    <tr>
                        年级：
                        <select name="grade">
                            <option value="23">23级</option>
                            <option value="22">22级</option>
                            <option value="21">21级</option>
                            <option value="20">20级</option>
                        </select>
                    </tr>
                </table>
            </label>
        </c:if>

        <div class="buttons">
        <input value="修 改" type="submit">
        </div>
        <br class="clear">
    </form>
</div>

<form action="personalHomepageServlet" method="post">
    <input type="submit" value="返回个人主页">
</form>

</body>
</html>
