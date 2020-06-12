<%--
  Created by IntelliJ IDEA.
  User: spring_zhang
  Date: 2020/4/15
  Time: 8:50 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath() + "/";
%>
<base href="<%=basePath%>"/>
<html>
<head>
    <title>Title</title>
    <script src="system/js/jquery.js"></script>
</head>
<body>
aaa
<button onclick="toLogin()">登录</button>
</body>
<script>
    $(function () {
        var basePath = "<%=basePath%>";
        console.log(basePath)
    })

    function toLogin() {
        $.ajax({
            url:  "home/login",
            type: "POST",
            async: false,
            success: function (data) {
                console.log(data)
                if (data == "000000") {
                    window.location.href = "home/hello";
                }
            }
        });
    }
</script>
</html>
