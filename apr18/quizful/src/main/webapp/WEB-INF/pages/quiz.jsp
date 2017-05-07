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
    <div class="page-header">
        <h1>Тест начался!</h1>
    </div>

    <!-- Nav tabs -->
    <form action="" method="post" role="form">
    <div class="row">
        <div class="col-sm-3">
            id: ${question.id} <br/>
            categoryId: ${question.categoryId} <br/>
            text: ${question.text} <br/>
            <ul>
            <c:forEach items="${question.answers}" var="answer" varStatus="loop">
                <li class="nav-item">
                    <i class="fa fa-circle-o"></i> ${answer.id} ${answer.text} ${answer.correct}
                </li>
            </c:forEach>
            </ul>
        </div>
    </div>
    </form>
</div>

<%@include file="footer.jsp" %>