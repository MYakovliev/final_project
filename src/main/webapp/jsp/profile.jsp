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
</head>
<body>
<jsp:include page="support/header.jsp"/>
<div class="content">
    <img src="${user.avatar}"/>
    <span class="name">
        ${user.name}
    </span>
    <c:if test="${user.id eq sessionScope.user.id}">
        <a class="edit" href="${pageContext.request.contextPath}/controller?command=to_user_edit">Edit</a>
    </c:if>
    <span class="mail">${user.mail}</span><br/>
    <div class="block">
        <span class="won_lots">15${won_lots}</span>
        <span class="compete_lots">20${compete_lots}</span>
    </div>
</div>
</body>
</html>
