<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 2/25/2021
  Time: 9:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title><fmt:message key="lot.title"/></title>
    <link href="<c:url value="/css/lot.css"/>" type="text/css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="support/header.jsp"/>
<c:out value="fff${error}"/>
<c:if test="${error ne null}">
<fmt:message key="lot.${error}"/>
</c:if>
<div class="content_container">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="make_bid"/>
        <input type="hidden" name="lot_id" value="${lot.id}">
        ${lot.name}<br/>
        ${lot.description}<br/>
        ${lot.currentCost}&dollar;<br/>
        <p class="time">${lot.finishTime.time}</p>
        <input type="number" min="0.01" step="0.01" name="bid"/>
        <input type="submit" name="btn" value="make bid"/>
    </form>
</div>
<div class="row">
    <c:forEach var="picture" items="${lot.images}">
        <div class="column">
            <img src="${picture}" style="width: 50%" onclick="showPicture(this);"/>
        </div>
    </c:forEach>
</div>

<div class="main_block">
    <div class="container">
        <span onclick="this.parentElement.style.display='none'" class="closebtn">&times;</span>
        <img id="expandedImg" style="min-width: 40%; max-width: 100%;" onclick="magnify('expandedImg', 3)"
             alt="main_image">
    </div>
</div>
<script src="<c:url value="/js/time_counter.js"/>"></script>
<script src="<c:url value="/js/magnifier.js"/>"></script>
<script src="<c:url value="/js/show_picture.js"/>"></script>
</body>
</html>
