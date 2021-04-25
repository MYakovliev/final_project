<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 3/15/2021
  Time: 12:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title><fmt:message key="footer.title"/></title>
    <link href="<c:url value="/css/footer.css"/>" type="text/css" rel="stylesheet"/>
</head>
<body>
<footer class="footer">
    <div class="footer__addr">
        <h1 class="footer__logo"><fmt:message key="footer.thanks"/></h1><br/>
        <c:choose>
            <c:when test="${sessionScope.user eq null}">
                <fmt:message key="GUEST"/>
            </c:when>
            <c:otherwise>
                <fmt:message key="${sessionScope.user.userRole}"/>
            </c:otherwise>
        </c:choose>
        <br/>
        <fmt:message key="footer.contact"/> support@auction.by
    </div>
</footer>
</body>
</html>
