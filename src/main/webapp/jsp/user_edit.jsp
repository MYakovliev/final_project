<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 4/8/2021
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/upload/" method="post" enctype="multipart/form-data">
    <img src="${user.avatar}"/>
    <input type="file" name="avatar"/>
    <input type="hidden" name="command" value="change_user"/>
    <input type="text" name="name" value="${user.name}">
    <input type="email" name="mail" value="${user.mail}">
    <input type="password" name="old_password"/>
    <input type="password" name="new_password"/>
    <input type="submit" name="btn" value="Submit"/>
</form>
</body>
</html>
