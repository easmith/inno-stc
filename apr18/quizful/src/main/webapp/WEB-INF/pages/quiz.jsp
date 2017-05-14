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
    <form action="" method="post" role="form">
        <div class="row">
            <div class="offset-md-1 col-10">
                <!--Jumbotron-->
                <div class="jumbotron">
                    <h1 class="h1-responsive">${category.name}</h1>
                    <p class="lead">${question.text}</p>
                    <hr class="my-2">
                    <p>
                    <ul class="list-group">
                        <c:forEach items="${question.answers}" var="answer" varStatus="loop">
                            <li class="nav-item list-group-item">
                                <fieldset class="form-group">
                                <input type="radio" name="answer[]" value="${answer.id}" id="answer${answer.id}"
                                       class="answer" data-id="${answer.id}">
                                <label for="answer${answer.id}">${answer.text}</label>
                                </fieldset>
                            </li>
                        </c:forEach>
                    </ul>
                    </p>
                    <p class="lead">
                        <a class="btn btn-success btn-lg" role="button" id="checkResult">Проверить</a>
                        <a href="${pageContext.request.contextPath}/user/quiz/${category.id}"
                           class="btn btn-primary btn-lg" role="button">Пропустить</a>
                    </p>

                    <script>
                        $().ready(function () {
                            $("#checkResult").click(function () {

                                var checked = [];
                                $("input[name='answer[]']:checked").each(function () {
                                    checked.push(parseInt($(this).val()));
                                });
                                console.log(checked);


                                $.ajax({
                                    url: "${pageContext.request.contextPath}/user/quiz/check/${question.id}",
                                    method: "post",
                                    contentType: 'application/json; charset=utf-8',
                                    dataType: 'json',
                                    data: JSON.stringify({answers: checked})
                                }).done(function (data) {
                                    console.log(data);
                                    if (data.answers) {
                                        $("input.answer").each(function () {
                                            $(this).parent().parent().removeClass("list-group-item-danger", "list-group-item-success");
                                            var answerId = $(this).data('id');
                                            var inArray = data.answers.indexOf(answerId) > -1;
                                            if (inArray) {
                                                $(this).parent().parent().addClass("list-group-item-success");
                                            }
                                            if ($(this).is(":checked") && !inArray) {
                                                $(this).parent().parent().addClass("list-group-item-danger");
                                            }
                                        })
                                    }
                                });
                            })
                        })
                    </script>
                </div>
                <!--/.Jumbotron-->
            </div>
        </div>
    </form>
</div>

<%@include file="footer.jsp" %>