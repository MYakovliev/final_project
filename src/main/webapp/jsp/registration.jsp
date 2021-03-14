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
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Registration</title>
    <link href="<c:url value="/css/registration.css"/>" type="text/css" rel="stylesheet"/>

</head>
<body>
<div class="form">
    <form class="register-form" action="register.do" method="post">
        <input type="hidden" name="command" value="register"/>
        <input type="text" placeholder="login" name="login"/>
        <input type="text" placeholder="name" name="name"/>
        <input type="password" placeholder="password" name="password" required pattern=".{8, 40}"/>
        <input type="text" placeholder="email address" name="mail" required pattern="[^$@!]{4,20}@\w+\.\w+"/>
        <select name="role">
            <option value="" selected disabled hidden>choose role</option>
            <option name="role" value="buyer">Buyer</option>
            <option name="role" value="seller">Seller</option>
        </select>
        <input type="submit" name="btn" value="sign up"/>
        <p class="message">Already registered? <a href="controller?command=to_login">Sign In</a></p>
        <p class="message">Don't want to log in?<a href="controller?command=to_main">Back to main</a></p>
    </form>
</div>
<%--<form action="controller" method="get">--%>
<%--    <input type="hidden" name="command" value="register"/>--%>
<%--    <input type="text" name="mail" value="mail@gmail.com" required pattern="[^$@!]{4,20}@\w+\.\w+"/> <br/>--%>
<%--    <input type="password" name="password" value="password" required pattern=".{8, 40}"/><br/>--%>
<%--    <input type="text" name="name" value="name"/><br/>--%>
<%--    <input type="text" name="login" value="login"/><br/>--%>
<%--    <input type="submit" name="but" value="sign up"/>--%>
<%--</form>--%>
<%--<form action="controller" method="get">--%>
<%--    <input type="hidden" name="command" value="to_login">--%>
<%--    <input type="submit" name="but" value="sign in">--%>
<%--</form>--%>
</body>
</html>
