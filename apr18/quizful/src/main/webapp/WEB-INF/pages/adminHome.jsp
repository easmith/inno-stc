<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 24.04.17
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>

<!-- Begin page content -->
<div class="container">
    <div class="page-header">
        <h1>Привет, Админ!</h1>
    </div>
    <div class="row">
        <div class="col-md-offset-2 col-md-6">
            <h3>Форма добавления вопроса</h3>

            <c:choose>
                <c:when test="${error != null}">
                    <p class="text-danger">${error}</p>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>

            <form class="form-horizontal" action="${pageContext.request.contextPath}/login" method="POST">
                <div class="form-group">
                    <label for="inputCategory" class="col-sm-2 control-label">Категория</label>
                    <div class="col-sm-8">
                        <select class="form-control" name="category" id="inputCategory">
                            <option value="1">Java основы</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputTask" class="col-sm-2 control-label">Текст вопроса</label>
                    <div class="col-sm-4">
                        <textarea name="task" class="form-control" id="inputTask"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-4">
                        <button type="submit" class="btn btn-default">Добавить</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>