<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
        <link href="<c:url value="/css/ban.css"/>" type="text/css" rel="stylesheet"/>
    </head>
    <body>
    <h3>Hello and Welcome</h3>
    <a href="${pageContext.request.contextPath}/controller?command=to_lots">See the lots</a>
    </body>
</html>