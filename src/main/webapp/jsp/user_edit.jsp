<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title><fmt:message key="user_edit.title"/></title>
    <link href="<c:url value="/css/user_edit.css"/>" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <form action="${pageContext.request.contextPath}/upload/" method="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="change_user_data"/>
        <img src="${sessionScope.user.avatar}" id="img"/>
        <label for="1"><fmt:message key="user_edit.upload_image"/></label>
        <input id="1" type="file" name="avatar" onchange="readURL(this, 'img', null)"/>
        <input type="text" name="name" placeholder="<fmt:message key="user_edit.name"/>" value="${sessionScope.user.name}">
        <input type="email" name="mail" placeholder="<fmt:message key="user_edit.mail"/>" value="${sessionScope.user.mail}">
        <input type="submit" name="btn" value="<fmt:message key="user_edit.submit"/>"/>
    </form>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="change_password">
        <input type="password" name="old_password" placeholder="<fmt:message key="user_edit.old_password"/>" required/>
        <input type="password" name="new_password" placeholder="<fmt:message key="user_edit.new_password"/>" required/>
        <input type="submit" value="<fmt:message key="user_edit.change"/>">
    </form>
    <p class="message"><a href="controller?command=to_lots">
        <fmt:message key="login.back_to_main"/></a>
    </p>
</div>
</body>
<script src="<c:url value="/js/open_images.js"/>"></script>
</html>
