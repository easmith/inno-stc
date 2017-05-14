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

    <div class="offset-3 col-md-6">

        <!--Form with header-->
        <div class="card">
            <div class="card-block">

                <!--Header-->
                <div class="form-header blue-gradient">
                    <h3><i class="fa fa-user"></i> Register:</h3>
                </div>
                <form action="${pageContext.request.contextPath}/register" method="post" id="formRegister">

                <c:if test="${registerError != null}">
                    <div class="alert alert-danger" role="alert">
                        ${registerError}
                    </div>
                </c:if>
                <!--Body-->
                <div class="md-form ${error_name != null ? "has-danger" : ""}">
                    <i class="fa fa-user prefix"></i>
                    <input name="name" type="text" id="inputName" class="form-control" value="${registerForm.getName()}">
                    <label for="inputName">Ваше имя</label>
                    <c:if test="${error_name != null}">
                        <small class="form-control-feedback">${error_name}</small>
                    </c:if>
                </div>
                <div class="md-form ${error_login != null ? "has-danger" : ""}">
                    <i class="fa fa-envelope prefix"></i>
                    <input name="login" type="text" id="inputLogin" class="form-control" value="${registerForm.getLogin()}">
                    <label for="inputLogin">Ваш логин</label>
                    <c:if test="${error_login != null}">
                        <small class="form-control-feedback">${error_login}</small>
                    </c:if>
                </div>

                <div class="md-form ${error_password1 != null ? "has-danger" : ""}">
                    <i class="fa fa-lock prefix"></i>
                    <input name="password1" type="password" id="inputPassword1" class="form-control">
                    <label for="inputPassword1">Ваш пароль</label>
                    <c:if test="${error_password1 != null}">
                        <small class="form-control-feedback">${error_password1}</small>
                    </c:if>
                </div>

                <div class="md-form ${error_notEqualPass != null || error_password2 != null ? "has-danger" : ""}">
                    <i class="fa fa-lock prefix"></i>
                    <input name="password2" type="password" id="inputPassword2" class="form-control">
                    <label for="inputPassword2">Повтор пароля</label>
                    <c:if test="${error_password2 != null}">
                        <small class="form-control-feedback">${error_password2}</small>
                    </c:if>
                    <c:if test="${error_notEqualPass != null}">
                        <small class="form-control-feedback">${error_notEqualPass}</small>
                    </c:if>
                </div>

                <div class="text-center">
                    <button class="btn btn-indigo">Зарегистрировать</button>
                </div>
                </form>
            </div>
        </div>
        <script>
            $().ready(function() {
                $("#formRegister input").keyup(function(){
                    $(this).parent().removeClass("has-danger");
                    $(this).parent().find(".form-control-feedback").remove();
                })
            });
        </script>
        <!--/Form with header-->

    </div>


</div>

<%@include file="footer.jsp"%>