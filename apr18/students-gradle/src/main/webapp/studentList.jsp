<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All Users</title>
</head>
<body>
<h3>Студенты</h3>
<table border=1>
    <thead>
    <tr>
        <th>Name</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${students}" var="student">
        <tr>
            <td><c:out value="${student.getName()}" /></td>
            <td><a href="student?action=edit&id=<c:out value="${student.getId()}"/>">Edit</a></td>
            <td><a href="student?action=delete&id=<c:out value="${student.getId()}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="student?action=insert">Добавить студента</a></p>
</body>
</html>