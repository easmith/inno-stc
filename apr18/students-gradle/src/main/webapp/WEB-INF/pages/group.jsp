<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Add new student</title>
</head>
<body>

<form method="POST" action='group'>
    group id : <input type="text" name="id" readonly value="<c:out value="${group.id}" />" /> <br />
    group name : <input type="text" name="name" value="<c:out value="${group.name}" />" /> <br />
    <input type="submit" value="Submit" />
</form>
</body>
</html>