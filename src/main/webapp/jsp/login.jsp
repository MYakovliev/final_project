<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 2/10/2021
  Time: 11:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title><fmt:message key="login.title"/></title>
    <link href="<c:url value="/css/login.css"/>" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="login-page">
    <div class="form">
        <form class="login-form" action="login.do" method="post">
            <input type="hidden" name="command" value="login"/>
            <input type="text" placeholder="<fmt:message key="login.login"/>" name="login"/>
            <input type="password" placeholder="<fmt:message key="login.password"/>" name="password"/>
            <input type="submit" name="btn" value="<fmt:message key="login.button"/>"/>
            <p class="message"><fmt:message key="login.ask_to_signup"/> <a href="controller?command=to_registration" ><fmt:message key="login.signup"/></a></p>
            <p class="message"><fmt:message key="login.ask_to_main"/> <a href="controller?command=to_lots" ><fmt:message key="login.back_to_main"/></a></p>
        </form>
    </div>
</div>
</body>
</html>

