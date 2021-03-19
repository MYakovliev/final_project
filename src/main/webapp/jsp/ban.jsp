<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 3/17/2021
  Time: 12:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title>Ban</title>
    <link href="<c:url value="/css/ban.css"/>" type="text/css" rel="stylesheet"/>
</head>
<body>
<h3>Sorry, you're banned</h3>
<a href="${pageContext.request.contextPath}/controller?command=logout">sign out</a>
</body>
</html>
