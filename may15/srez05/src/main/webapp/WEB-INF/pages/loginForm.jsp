<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 15.05.17
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
    login: <input name="username"> <br/>
    password: <input name="password"> <br/>
    <input type="submit" value="enter">
</form>
</body>
</html>
