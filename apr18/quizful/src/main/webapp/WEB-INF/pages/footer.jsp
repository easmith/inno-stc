<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 24.04.17
  Time: 1:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--Footer-->
<footer class="page-footer blue center-on-small-only">

    <!--Footer Links-->
    <div class="container-fluid">
        <div class="row">

            <!--First column-->
            <div class="col-md-6">
                <h5 class="title">Система тестирования программистов</h5>
                <p>Курсовой проект для изучения Java</p>
            </div>
            <!--/.First column-->

            <!--Second column-->
            <div class="col-md-6">
                <h5 class="title">Сделано с использованием</h5>
                <ul>
                    <li><a href="#!">Java EE</a></li>
                    <li><a href="#!">Spring IoC</a></li>
                    <li><a href="#!">Spring MVC</a></li>
                    <li><a href="#!">Bootstrap</a></li>
                </ul>
            </div>
            <!--/.Second column-->
        </div>
    </div>
    <!--/.Footer Links-->

    <!--Copyright-->
    <div class="footer-copyright">
        <div class="container-fluid">
            © 2017 Copyright: <a href="https://easmith.github.io/"> Евгений Кузнецов </a>

        </div>
    </div>
    <!--/.Copyright-->

</footer>
<!--/.Footer-->

<script src="${pageContext.request.contextPath}/resources/js/jquery-2.2.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/tether.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/mdb.min.js"></script>
<script>
    new WOW().init();
</script>
</body>
</html>
