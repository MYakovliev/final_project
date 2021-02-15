<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 13.01.2021
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="controller" method="get">
    <input type="hidden" name="command" value="register"/>
    <input type="text" name="mail" value="mail@gmail.com" required pattern="[^$@!]{4,20}@\w+\.\w+"/> <br/>
    <input type="password" name="password" value="password" required pattern=".{8, 40}"/><br/>
    <input type="text" name="name" value="name"/><br/>
    <input type="text" name="login" value="login"/><br/>
    <input type="submit" name="but" value="sign up"/>
</form>
<form action="controller" method="get">
    <input type="hidden" name="command" value="to_login">
    <input type="submit" name="but" value="sign in">
</form>
</body>
</html>
