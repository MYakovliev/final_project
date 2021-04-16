<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <input type="hidden" name="command" value="change_user_detail"/>
    <img src="${sessionScope.user.avatar}" id="img"/>
    <input type="file" name="avatar" onchange="readURL(this, 'img', null)"/>
    <input type="text" name="name" value="${sessionScope.user.name}">
    <input type="email" name="mail" value="${sessionScope.user.mail}">
    <input type="submit" name="btn" value="Submit"/>
</form>
<form action="/controller" method="post">
    <input type="hidden" name="command" value="change_password">
    <input type="password" name="old_password"/>
    <input type="password" name="new_password"/>
    <input type="submit" value="change">
</form>
<script src="<c:url value="/js/open_images.js"/>"></script>
</body>
</html>
