<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 24.04.17
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="header.jsp" %>

<!-- Begin page content -->
<div class="container">
    <div class="page-header">
        <h1>${userSession.name}, рады вас видеть в наших рядах!</h1>
    </div>
    <div class="row">
        <div class="offset-2 col-6">
            <c:forEach items="${categories}" var="category">
                <!--Card-->
                <div class="card" style="width:200px; display:inline-block;">
                    <!--Card content-->
                    <div class="card-block">
                        <!--Title-->
                        <h4 class="card-title">${category.name}</h4>
                        <!--Text-->
                        <p class="card-text">Тест уровня ${category.name}</p>
                        <a href="${pageContext.request.contextPath}/user/quiz/${category.id}" class="btn btn-primary">Начать</a>
                    </div>
                    <!--/.Card content-->
                </div>
                <!--/.Card-->
            </c:forEach>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>