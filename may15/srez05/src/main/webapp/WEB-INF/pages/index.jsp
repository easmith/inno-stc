<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 24.04.17
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<div>
    Home dir<br/>
    <sec:authorize access="hasRole('ROLE_USER')">
        <a href="only_for_user">only_for_user</a><br/>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a href="only_for_admin">only_for_admin</a><br/>
    </sec:authorize>
</div>
</body>
</html>