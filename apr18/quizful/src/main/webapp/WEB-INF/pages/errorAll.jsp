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
        <h1>У нас проблемы :(</h1>
    </div>
    <img src="${pageContext.request.contextPath}/resources/img/405.gif">
</div>

<!--
Exception:  ${exception.message}
<c:forEach items="${exception.stackTrace}" var="ste">    ${ste}
</c:forEach>
-->

<%@include file="footer.jsp"%>