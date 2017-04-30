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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <title>Project QUIZ</title>

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.0/css/font-awesome.min.css">

    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <%--<link href="${pageContext.request.contextPath}/resources/css/sticky-footer-navbar.css" rel="stylesheet">--%>

    <%--<link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.3.0/css/mdb.min.css" rel="stylesheet">--%>
    <link href="${pageContext.request.contextPath}/resources/css/mdb.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/compiled.min.css?ver=4.3.2" rel="stylesheet">

</head>
<body>

<script src="${pageContext.request.contextPath}/resources/js/jquery-2.2.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/tether.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/mdb.min.js"></script>
<%--<header>--%>
<!-- Fixed navbar -->
<nav class="navbar navbar-toggleable-md fixed-top navbar-dark bg-primary">
    <div class="container">
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                data-target="#navbarNav1" aria-controls="navbarNav1" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">
            <i id="icon" class="fa fa-mortar-board quiz-logo"></i>
            <strong>Project Quiz</strong>
        </a>
        <div class="collapse navbar-collapse" id="navbarNav1">
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item ${ "index".equals(menuItem) ? "active" : ""}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">Главная</a>
                </li>
                <c:choose>
                    <c:when test="${userSession != null}">
                        <c:choose>
                            <c:when test="${userSession.getAdmin()}">
                                <li class="nav-item ${ "register".equals(menuItem) ? "isAdmin" : ""}">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/admin">Админка</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item ${ "register".equals(menuItem) ? "user" : ""}">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/user">
                                        Личный кабинет
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                                Выйти (${userSession.getLogin()})
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item ${ "login".equals(menuItem) ? "active" : ""}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login">Логин</a>
                        </li>
                        <li class="nav-item ${ "register".equals(menuItem) ? "active" : ""}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/register">Регистрация</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
<!--/.Navbar-->
<%--</header>--%>

<main class="main">
<c:if test="${fatalError != null}">
    <div class="container">
        <div class="row">
            <div class="alert alert-danger" role="alert">
                    ${fatalError}
            </div>
        </div>
    </div>
</c:if>