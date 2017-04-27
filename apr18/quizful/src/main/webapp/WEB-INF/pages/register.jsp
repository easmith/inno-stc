<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 24.04.17
  Time: 3:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>

<div class="container">

    <div class="col-md-offset-3 col-md-6">
        <h3>Регистрация</h3>

        <c:choose>
            <c:when test="${error != null}">
                <p class="text-danger">${error}</p>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>

        <form class="form-horizontal" action="${pageContext.request.contextPath}/register" method="POST">
            <div class="form-group">
                <label for="inputName" class="col-sm-2 control-label">Имя</label>
                <div class="col-sm-4">
                    <input type="text" name="name" class="form-control" id="inputName" placeholder="Имя">
                </div>
            </div>
            <div class="form-group">
                <label for="inputLogin" class="col-sm-2 control-label">Логин</label>
                <div class="col-sm-4">
                    <input type="text" name="login" class="form-control" id="inputLogin" placeholder="Логин">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword1" class="col-sm-2 control-label">Пароль</label>
                <div class="col-sm-4">
                    <input type="password" name="password1" class="form-control" size="12" min="6" id="inputPassword1" placeholder="Пароль">
                </div>
            </div>

            <div class="form-group">
                <label for="inputPassword2" class="col-sm-2 control-label">Повторите пароль</label>
                <div class="col-sm-4">
                    <input type="password" name="password2" class="form-control" size="12" min="6" id="inputPassword2" placeholder="Повторите пароль">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-4">
                    <button type="submit" class="btn btn-default">Войти</button>
                </div>
            </div>
        </form>
    </div>


</div>

<%@include file="footer.jsp"%>