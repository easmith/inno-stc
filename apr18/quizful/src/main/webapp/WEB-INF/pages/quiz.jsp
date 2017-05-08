<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 24.04.17
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>

<!-- Begin page content -->
<div class="container">
    <form action="" method="post" role="form">
        <div class="row">
            <div class="offset-md-2 coll-8">
                <!--Jumbotron-->
                <div class="jumbotron">
                    <h1 class="h1-responsive">${category.name}</h1>
                    <p class="lead">${question.text}</p>
                    <hr class="my-2">
                    <p>
                    <ul>
                        <c:forEach items="${question.answers}" var="answer" varStatus="loop">
                            <li class="nav-item">
                                <input type="radio" value="${answer.id}" id="answer${answer.id}">
                                <label for="answer${answer.id}">${answer.text}</label>
                            </li>
                        </c:forEach>
                    </ul>
                    </p>
                    <p class="lead">
                        <a class="btn btn-success btn-lg" role="button">Проверить</a>
                        <a href="${pageContext.request.contextPath}/user/quiz/${category.id}"
                           class="btn btn-primary btn-lg" role="button">Пропустить</a>
                    </p>
                </div>
                <!--/.Jumbotron-->
            </div>
        </div>
    </form>
</div>

<%@include file="footer.jsp" %>