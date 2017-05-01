<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Группы</title>
</head>
<body>
<h3>Группы</h3>
<table border=1>
    <thead>
    <tr>
        <th>Name</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${groups}" var="group">
        <tr>
            <td><c:out value="${group.name}" /></td>
            <td><a href="group?action=edit&id=<c:out value="${group.getId()}"/>">Update</a></td>
            <td><a href="group?action=delete&id=<c:out value="${group.getId()}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="group?action=insert">Добавить Группу</a></p>
</body>
</html>