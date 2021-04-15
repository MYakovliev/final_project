<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="page" value="${'/jsp/profile.jsp'}" scope="session"/>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title><fmt:message key="profile.title"/></title>
</head>
<body>
<img src="${user.avatar}"/>
${user.name}
<c:if test="${user.id eq sessionScope.user.id}"><a href="${pageContext.request.contextPath}/controller?command='to_edit_user'">Edit</a></c:if>
${user.mail}
${won_lots}      ${compete_lots}
</body>
</html>
