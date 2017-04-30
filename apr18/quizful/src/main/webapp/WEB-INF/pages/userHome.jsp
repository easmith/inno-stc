<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 24.04.17
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="header.jsp"%>

<!-- Begin page content -->
<div class="container">
    <div class="page-header">
        <h1>${userSession.name}, рады вас видеть в наших рядах!</h1>
    </div>
    <div class="row">
        <div class="offset-2 col-6">
            <a href="${pageContext.request.contextPath}/user/start/1">start 1</a>

            <table border=1>
                <thead>
                <tr>
                    <th>category id</th>
                    <th>category name</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${categories}" var="category">
                    <tr>
                        <td>${category.id}</td>
                        <td><a href="${pageContext.request.contextPath}/user/start/${category.id}">${category.name}</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <table border=1>
                <thead>
                <tr>
                    <th>result id</th>
                    <th>category_id</th>
                    <th>start_at</th>
                    <th>stop_at</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${results}" var="result">
                    <tr>
                        <td>${result.id}</td>
                        <td>${result.categoryId}</td>
                        <td><a href="${pageContext.request.contextPath}/user/quiz/${result.id}">${result.startAt}</a></td>
                        <td>${result.stopAt}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>