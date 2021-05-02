<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 13.01.2021
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title><fmt:message key="registration.title"/></title>
    <link href="<c:url value="/css/registration.css"/>" type="text/css" rel="stylesheet"/>

</head>
<body>
<div class="form">
    <c:if test="${error ne null}">
        <p class="error"><fmt:message key="${error}"/></p>
    </c:if>
    <form class="register-form" action="register.do" method="post">
        <input type="hidden" name="command" value="register"/>
        <input type="text" placeholder="<fmt:message key="registration.login"/>" title="<fmt:message key="registration.login_title"/>" name="login" required pattern="[\wА-Яа-я0-9]{4,35}"/>
        <input type="text" placeholder="<fmt:message key="registration.name"/>" title="<fmt:message key="registration.name_title"/>" name="name" required pattern="[\wА-Яа-я]{1,35}"/>
        <input type="password" placeholder="<fmt:message key="registration.password"/>" title="<fmt:message key="registration.password_title"/>" name="password" required pattern=".{8, 40}"/>
        <input type="text" placeholder="<fmt:message key="registration.email"/>" name="mail" required pattern="([^$@!]{4,20}@\w+\.\w+){,45}"/>
        <select name="role" required>
            <option value="" selected disabled hidden><fmt:message key="registration.choose_role"/></option>
            <option name="role" value="buyer"><fmt:message key="registration.buyer_role"/></option>
            <option name="role" value="seller"><fmt:message key="registration.seller_role"/></option>
        </select>
        <input type="submit" name="btn" value="<fmt:message key="registration.button"/>"/>
        <p class="message"><fmt:message key="registration.ask_to_sign_in"/> <a href="controller?command=to_login"><fmt:message key="registration.sign_in"/></a></p>
        <p class="message"><fmt:message key="registration.ask_to_main"/> <a href="controller?command=to_lots"><fmt:message key="registration.back_to_main"/></a></p>
    </form>
</div>
</body>
</html>
