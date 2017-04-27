<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 27.04.17
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>

<!-- Begin page content -->
<div class="container">
    <div class="page-header">
        <h1>404 - Страница не найдена</h1>
    </div>
    <p>Можно вернуться <a href="${pageContext.request.contextPath}/">на главную</a></p>
    <img src="${pageContext.request.contextPath}/resources/img/404.gif">
</div>

<%@include file="footer.jsp"%>