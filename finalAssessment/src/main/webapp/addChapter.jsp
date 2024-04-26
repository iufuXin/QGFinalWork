<%--
  Created by IntelliJ IDEA.
  User: 刘付鑫
  Date: 2024/4/22
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加章节</title>
</head>
<body>

<h1>添加章节界面</h1>

<h2>${warning}</h2>

<form action="addChapterServlet" method="post" id="chapterForm" enctype="multipart/form-data">
    <label for="course_id"></label>
    <input type="hidden" id="course_id" name="course_id" value="${course_id}">

    <label for="chapterNumber">章节序号：
    <input type="text" id="chapterNumber" name="chapterNumber">注：只支持整数<br>
    </label>

    <label for="desc">章节描述：
    <textarea id="desc" name="desc"></textarea><br>
    </label>

    <label for="file">选择文件：
    <input type="file" id="file" name="file"><br>
    </label>

    <label for="singleTopicAnswer">选择题答案：
    <input type="text" id="singleTopicAnswer" name="singleTopicAnswer">注：目前只支持单选题，请按顺序填写答案，且字母为大写，最多支持50道题<br>
    </label>

    <label for="judgmentAnswer">判断题答案：
    <input type="text" id="judgmentAnswer" name="judgmentAnswer">注：请按顺序填写答案，且0对应错，1对应是对，最多支持50道题<br>
    </label>

    <label for="shortNumber">简答题数量：
    <input type="number" id="shortNumber" name="shortNumber"><br>
    </label>

    <input type="submit" value="提 交">
</form>

<form action="viewChapterServlet">
    <input type="hidden" value="${course_id}" name="id">
    <input type="submit" value="返回查看章节界面">
</form>

</body>
</html>
