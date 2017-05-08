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
        <div class="offset-md-2 col-md-8">
            <h3>Добавление вопроса</h3>
            <c:choose>
                <c:when test="${fatalError != null}">
                    <p class="text-danger">${error}</p>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
            <form action="${pageContext.request.contextPath}/admin/question" method="POST">
                <div class="form-group">
                    <label for="selectCategory">Категория</label>
                    <select name="categoryId" class="form-control" id="selectCategory">
                        <option value="1">Java основы</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectType">Тип</label>
                    <select name="questionType" class="form-control" id="selectType">
                        <option value="SINGLE">Один правильный ответ</option>
                        <option value="MULTI">Несколько правильных ответов</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="textareaQuestion">Текст вопроса</label>
                    <textarea name="questionText" class="form-control" id="textareaQuestion" rows="5"></textarea>
                </div>

                <fieldset class="form-group" id="answersFieldset">
                    <legend>Ответы <a href="#" id="addAnswer">Добавить</a></legend>
                    <div class="form-check" data-num="1">
                        <label class="form-check-label">
                            <input type="checkbox" class="form-check-input" name="answerIsCorrect[1]" value="correct">
                            <textarea class="form-control" name="answer[1]" rows="5"></textarea>
                        </label>
                    </div>
                </fieldset>
                <script>
                    $().ready(function(){
                        var cloneCount = 1;
                        $("#addAnswer").click(function (){
                            console.log("click #" + cloneCount);
                            cloneCount++;
                            var elem = $("#answersFieldset .form-check:last").clone();
                            elem.find("input").attr('name', 'answerIsCorrect['+ cloneCount+']');
                            elem.find("textarea").attr('name', 'answer['+ cloneCount+']');
                            elem.appendTo("#answersFieldset");
                            return false;
                        });
                    });
                </script>
                <button type="submit" class="btn btn-primary">Добавить</button>
            </form>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>