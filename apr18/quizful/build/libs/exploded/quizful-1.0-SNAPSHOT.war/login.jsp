<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 20.04.17
  Time: 1:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="WEB-INF/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <h3>Введите свой логин и пароль</h3>

            <c:choose>
                <c:when test="${error != null}">
                    <p class="text-danger">${error}</p>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>

            <form class="form-horizontal" action="${pageContext.request.contextPath}/login" method="POST">
                <%
                    String cookieLogin = null;
                    Cookie[] cookies = request.getCookies();
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("login")) cookieLogin = cookie.getValue();
                        }
                    }
                %>
                <div class="form-group">
                    <label for="inputLogin" class="col-sm-2 control-label">Логин</label>
                    <div class="col-sm-4">
                        <input type="text" name="login" class="form-control" id="inputLogin" placeholder="Логин"
                               value="${cookieLogin}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-sm-2 control-label">Пароль</label>
                    <div class="col-sm-4">
                        <input type="password" name="password" class="form-control" size="12" min="6" id="inputPassword"
                               placeholder="Пароль">
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

</div>

<%@include file="WEB-INF/footer.jsp" %>