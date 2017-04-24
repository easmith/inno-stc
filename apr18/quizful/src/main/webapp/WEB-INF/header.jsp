<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 24.04.17
  Time: 1:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <li ${ "index".equals(menuItem) ? " class=\"active\" " : ""}><a href="${pageContext.request.contextPath}/">Главная</a></li>
                <li ${ "login".equals(menuItem) ? " class=\"active\" " : ""}><a href="login">Логин</a></li>
                <li ${ "register".equals(menuItem) ? " class=\"active\" " : ""}><a href="register">Регистрация</a></li>
            </ul>
        </div>
    </div>
</nav>
