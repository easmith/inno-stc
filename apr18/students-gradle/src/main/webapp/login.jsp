<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 18.04.2017
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="/students/login" method="post">
    <input type="text" name="login"/>
    <input type="text" name="pass"/>
    <input type="submit" value="login"/>
</form>
</body>
</html>
