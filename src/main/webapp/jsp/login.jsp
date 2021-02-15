<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 2/10/2021
  Time: 11:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="controller" method="get">
    <input type="hidden" name="command" value="login"/>
    login:<br/>
    <label>
        <input type="text" name="login" value="login"/>
    </label> <br/>
    Password:<br/>
    <label>
        <input type="password" name="password" value="password" required pattern=".{3, 40}" />
    </label> <br/>
    <input type="submit" name="but" value="log in"/>
</form>
<form action="controller" method="post">
    <input type="hidden" name="command" value="to_registration">
    <input type="submit" name="but" value="register"/>
</form>
</body>
</html>

