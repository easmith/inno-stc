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
    <div class="row">
        <div class="col-sm-3">
            <ul class="nav nav-tabs md-pills pills-primary flex-column" role="tablist">
                <c:forEach items="${resultTasks}" var="resultTask" varStatus="loop">
                    <li class="nav-item">
                        <a class="nav-link ${loop.first ? "active" : ""}" data-toggle="tab" href="#panel${resultTask.id}" role="tab">
                            <i class="fa fa-circle-o"></i> ${resultTask.id}
                        </a>
                    </li>
                </c:forEach>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#panelFinish" role="tab">
                        <i class="fa fa-stop"></i> Завершить
                    </a>
                </li>
            </ul>
        </div>
        <div class="col-sm-9">
            <!-- Tab panels -->
            <div class="tab-content">
                <c:forEach items="${resultTasks}" var="resultTask" varStatus="loop">
                    <div class="tab-pane fade in show ${loop.first ? "active" : ""}" id="panel${resultTask.id}" role="tabpanel">
                        <pre>${resultTask.task.text}</pre>
                        <ul>
                            <c:forEach items="${resultTask.task.answers}" var="answer">
                                <li>${answer.text}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:forEach>
                <div class="tab-pane fade in show" id="panelFinish" role="tabpanel">
                    <button class="btn btn-success">Завершить тест</button>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>