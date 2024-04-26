<%--
  Created by IntelliJ IDEA.
  User: 刘付鑫
  Date: 2024/4/20
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改课程</title>
    <!-- 引入时间选择器库 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/pikaday/css/pikaday.css">
    <script src="https://cdn.jsdelivr.net/npm/pikaday/pikaday.js"></script>
</head>
<body>
<h1>修改课程界面</h1>

<h2>${warning}</h2>

<form id="courseForm">
    <input type="hidden" id="id" name="id" value="${course.id}">
    <label for="courseName">课程名称：</label>
    <input type="text" id="courseName" name="courseName" value="${course.name}"><br>

    <label for="courseDescription">课程描述：</label>
    <textarea id="courseDescription" name="courseDescription">${course.desc}</textarea><br>

    <label for="startTimepicker">开始时间：</label>
    <input type="text" id="startTimepicker" name="startTime" value="${course.startTime}"><br>

    <label for="endTimepicker">结束时间：</label>
    <input type="text" id="endTimepicker" name="endTime" value="${course.endTime}"><br>

    <label for="maxParticipants">报名人数限制：</label>
    <input type="number" id="maxParticipants" name="maxParticipants" value="${course.maxEnrolment}"><br>

    <input type="submit" value="修 改">
</form>

<a href="/finalAssessment/personalHomepageServlet">返回个人主页</a>

<script>
    // 初始化开始时间选择器
    var startTimePicker = new Pikaday({
        field: document.getElementById('startTimepicker'),
        format: 'YYYY-MM-DD HH:mm', // 设定时间格式
        showTime: true, // 显示时间选择器
        showSeconds: true, // 可选择秒
        use24hour: true, // 使用24小时制
        onSelect: function(selectedDate) {
            console.log('选择的开始时间是：', selectedDate);
        }
    });

    // 初始化结束时间选择器
    var endTimePicker = new Pikaday({
        field: document.getElementById('endTimepicker'),
        format: 'YYYY-MM-DD HH:mm', // 设定时间格式
        showTime: true, // 显示时间选择器
        showSeconds: true, // 可选择秒
        use24hour: true, // 使用24小时制
        onSelect: function(selectedDate) {
            console.log('选择的结束时间是：', selectedDate);
        }
    });

    // 定义处理 AJAX 请求的函数
    function sendDataToServer(data) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "modifyCourseServlet", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                console.log('数据已成功发送到后端');

                // 检查是否存在重定向
                if (xhr.getResponseHeader("X-Redirect")) {
                    // 获取重定向的目标页面 URL
                    var redirectUrl = xhr.getResponseHeader("X-Redirect");

                    // 在前端执行页面跳转
                    window.location.href = redirectUrl;
                } else {
                    // 没有重定向，继续其他操作（如果有必要）
                    window.location.href = "personalHomepageServlet"
                }


            }
        };
        xhr.send(JSON.stringify(data));
    }

    // 在表单提交时，获取表单数据并发送到后端
    document.getElementById('courseForm').addEventListener('submit', function(event) {
        event.preventDefault(); // 阻止表单默认提交行为

        // 获取表单字段的值
        var courseName = document.getElementById('courseName').value;
        var courseDescription = document.getElementById('courseDescription').value;
        var startTime = startTimePicker.toString();
        var endTime = endTimePicker.toString();
        var maxParticipants = document.getElementById('maxParticipants').value;
        var id = document.getElementById('id').value;

        // 构建要发送到后端的数据对象
        var data = {
            id: id,
            courseName: courseName,
            courseDescription: courseDescription,
            startTime: startTime,
            endTime: endTime,
            maxParticipants: maxParticipants
        };

        // 调用函数发送数据到后端
        sendDataToServer(data);
    });
</script>

</body>
</html>
