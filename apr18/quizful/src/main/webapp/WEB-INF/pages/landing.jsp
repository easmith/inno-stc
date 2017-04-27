<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 19.04.17
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>

<main>

  <!--Main layout-->
  <div class="container">
    <!--First row-->
    <div class="row wow fadeIn" data-wow-delay="0.2s">
      <div class="col-md-12">
        <div class="jumbotron">
          <h2 class="h2-responsive">Информационная система тестирования ${menuItem}</h2>
          <br>
          <p>Сотни заданий различной сложности с одним или несколькими правильными вариантами ответа</p>
          <hr>
          <p>Зарегистрируйтесь и проверьте свои знания</p>
          <a href="${pageContext.request.contextPath}/register" class="btn btn-mdb btn-stc">Зарегистрироваться <i class="fa fa-user-plus right"></i></a>
          <a href="${pageContext.request.contextPath}/login" class="btn btn-unique btn-ptc">Войти <i class="fa fa-sign-in right"></i></a>
        </div>
      </div>
    </div>
    <!--/.First row-->

    <hr class="extra-margins">

  </div>
  <!--/.Main layout-->

</main>
<%@include file="footer.jsp"%>
