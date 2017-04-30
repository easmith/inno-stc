<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 19.04.17
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>

  <!--Main layout-->
  <div class="container">
    <!--Section: Features v.1-->
    <section class="section feature-box">

      <!--Section heading-->
      <h1 class="section-heading">Информационная система тестирования программистов</h1>
      <!--Section sescription-->
      <p class="section-description lead">Сотни заданий различной сложности с одним или несколькими правильными вариантами ответа доступные всегда и везде, где бы вы ни были</p>
      <p class="section-description">Бесплатная регистрация</p>
      <p class="section-description">
      <a href="${pageContext.request.contextPath}/register" class="btn btn-mdb btn-stc">Зарегистрироваться <i class="fa fa-user-plus right"></i></a>
      <a href="${pageContext.request.contextPath}/login" class="btn btn-unique btn-ptc">Войти <i class="fa fa-sign-in right"></i></a>
      </p>

      <!--First row-->
      <div class="row features-big">
        <!--First column-->
        <div class="col-md-4 mb-r">
          <i class="fa fa-area-chart blue-text"></i>
          <h4 class="feature-title">Рейтинг</h4>
          <p class="grey-text">Ты можешь отслеживать уровень своих знаний по результатам тестов</p>
        </div>
        <!--/First column-->

        <!--Second column-->
        <div class="col-md-4 mb-r">
          <i class="fa fa-book green-text"></i>
          <h4 class="feature-title">Изучай</h4>
          <p class="grey-text">Развернутые пояснения к вопросам позволяют восполнить пробелы знаний</p>
        </div>
        <!--/Second column-->

        <!--Third column-->
        <div class="col-md-4 mb-r">
          <i class="fa fa-coffee red-text"></i>
          <h4 class="feature-title">Наслаждайся</h4>
          <p class="grey-text">Все задания абсолютно бесплатны и доступны в любое время</p>
        </div>
        <!--/Third column-->
      </div>
      <!--/First row-->

    </section>
    <!--/Section: Features v.1-->

  </div>
  <!--/.Main layout-->
<%@include file="footer.jsp"%>
