<%--
  Created by IntelliJ IDEA.
  User: eku
  Date: 19.04.17
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<% String message = request.getAttribute("value").toString(); %>

<%=message%>

${value}
${param.value}

</body>
</html>
