<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="page" value="${'/jsp/main.jsp'}" scope="session"/>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title><fmt:message key="main.title"/></title>
    <script src="<c:url value="/js/time_counter.js"/>"></script>
    <link href="<c:url value="/css/main.css"/>" type="text/css" rel="stylesheet"/>

</head>
<body>
<jsp:include page="support/header.jsp"/>
<div class="main_block">
    <c:forEach var="lot" items="${requestScope.lot_list}">
        <div class="lot_block"
             onclick="location.href='${pageContext.request.contextPath}/controller?command=to_lot&lot_id=${lot.id}'">
            <div class="lot_name">${lot.name}</div>
            <img src="${lot.images.get(0)}"/>
            <div class="lot_cost">${lot.currentCost}&dollar;</div>
            <p class="time">${lot.finishTime.time}</p>
        </div>
    </c:forEach>
</div>
<jsp:include page="support/footer.jsp"/>
</body>
</html>
