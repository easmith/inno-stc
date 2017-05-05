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
    <div class="row fadeIn">
        <div class="offset-md-3 col-md-6">

            <c:if test="${registerMessage != null}">
                <div class="alert alert-info" role="alert">
                        ${registerMessage}
                </div>
            </c:if>

            <!--Form with header-->
            <div class="card sticky-top">
                <div class="card-block">
                <form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
                    <!--Header-->
                    <div class="form-header purple darken-4">
                        <h3><i class="fa fa-lock"></i> Вход:</h3>
                    </div>

                    <c:if test="${error != null}">
                        <div class="error text-danger">${error}</div>
                    </c:if>

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