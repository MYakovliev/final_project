<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 4/25/2021
  Time: 7:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title><fmt:message key="404.title"/></title>
    <link href="<c:url value="/css/ban.css"/>" type="text/css" rel="stylesheet"/>
</head>
<body>
<h3><fmt:message key="404.sorry"/></h3>
<a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="404.to_main"/></a>
</body>
</html>
