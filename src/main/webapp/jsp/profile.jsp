<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="page" value="${'/jsp/profile.jsp'}" scope="session"/>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title><fmt:message key="profile.title"/></title>
    <link href="<c:url value="/css/profile.css"/>" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>
<body>
<jsp:include page="support/header.jsp"/>
<div class="container">
    <img src="${user.avatar}"/>
    <span class="name">
        ${user.name}
    </span>
    <c:if test="${user.id eq sessionScope.user.id}">
        <a class="edit" href="${pageContext.request.contextPath}/controller?command=to_user_edit"><fmt:message key="profile.edit"/></a>
    </c:if>
    <span class="mail">${user.mail}</span><br/>
</div>
</body>
</html>
