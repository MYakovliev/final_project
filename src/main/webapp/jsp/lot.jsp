<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 2/25/2021
  Time: 9:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title><fmt:message key="lot.title"/></title>
    <script src="<c:url value="/js/show_picture.js"/>"></script>
    <script src="<c:url value="/js/magnifier.js"/>"></script>
    <link href="<c:url value="/css/lot.css"/>" type="text/css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="support/header.jsp"/>
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
        <img id="expandedImg" style="min-width: 40%; max-width: 50%;" onclick="magnify('myimage', 3)" alt="main_image">
    </div>

    <div class="content_container">
            ${lot.name}<br/>
            ${lot.description}<br/>
            ${lot.currentCost}<br/>
        <p class="time">${lot.finishTime.time}</p>
    </div>

    <input type="number" min="0.01" step="0.01" name="bid"/>
    <input type="submit" name="btn" value="make bid"/>
</div>

<script src="<c:url value="/js/time_counter.js"/>"></script>
</body>
</html>
