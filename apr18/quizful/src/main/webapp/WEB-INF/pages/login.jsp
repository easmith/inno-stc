<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 20.04.17
  Time: 1:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>

<div class="container">
    <div class="row">
        <div class="offset-md-3 col-md-6">

            <c:choose>
                <c:when test="${error != null}">
                    <p class="text-danger">${error}</p>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>

            <!--Form with header-->
            <div class="card single-card">
                <div class="card-block">
                <form action="" method="post">
                    <!--Header-->
                    <div class="form-header  purple darken-4">
                        <h3><i class="fa fa-lock"></i> Вход:</h3>
                    </div>

                    <!--Body-->
                    <div class="md-form">
                        <i class="fa fa-envelope prefix"></i>
                        <input name="login" type="text" id="inputLogin" class="form-control">
                        <label for="inputLogin">Ваш логин</label>
                    </div>

                    <div class="md-form">
                        <i class="fa fa-lock prefix"></i>
                        <input name="password" type="password" id="inputPassword" class="form-control">
                        <label for="inputPassword">Ваш пароль</label>
                    </div>

                    <div class="text-center">
                        <button class="btn btn-deep-purple" type="submit">Войти</button>
                    </div>
                </form>
                </div>
            </div>
            <!--/Form with header-->
        </div>
    </div>

</div>

<%@include file="footer.jsp" %>