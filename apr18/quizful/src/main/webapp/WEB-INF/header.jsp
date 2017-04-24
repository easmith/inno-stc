<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 24.04.17
  Time: 1:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="project quiz">
    <meta name="author" content="eku">
    <link rel="icon" href="favicon.ico">

    <title>Project QUIZ</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/sticky-footer-navbar.css" rel="stylesheet">

</head>

<body>

<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div id="navbar" class="collapse navbar-collapse">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Project QUIZ</a>
            <ul class="nav navbar-nav navbar-right">
                <li ${ "index".equals(menuItem) ? " class=\"active\" " : ""}><a
                        href="${pageContext.request.contextPath}/">Главная</a></li>
                <%
                    String userLogin = null;
                    Boolean userIsAdmin = null;
                    if (session != null) {
                        userLogin = (String) session.getAttribute("userLogin");
                        userIsAdmin = (Boolean) session.getAttribute("userIsAdmin");
                    }
                %>
                <c:choose>
                    <c:when test="${userLogin != null}">
                        <c:choose>
                            <c:when test="${userIsAdmin}">
                                <li><a href="${pageContext.request.contextPath}/admin">Админка</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${pageContext.request.contextPath}/user">Личный кабинет</a></li>
                            </c:otherwise>
                        </c:choose>
                        <li><a href="${pageContext.request.contextPath}/user/logout">Выйти (${userLogin})</a></li>
                    </c:when>
                    <c:otherwise>
                        <li ${ "login".equals(menuItem) ? " class=\"active\" " : ""}><a href="login">Логин</a></li>
                        <li ${ "register".equals(menuItem) ? " class=\"active\" " : ""}><a href="register">Регистрация</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
