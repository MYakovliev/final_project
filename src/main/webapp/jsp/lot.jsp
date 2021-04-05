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
    <script src="<c:url value="/js/time_counter.js"/>"></script>
    <script src="<c:url value="/js/show_picture.js"/>"></script>
    <script src="<c:url value="/js/magnifier.js"/>"></script>
    <link href="<c:url value="/css/lot.css"/>" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="row">
    <c:forEach var="picture" items="${lot.images}">
        <div class="column">
            <img src="${picture}" style="width:50%" onclick="showPicture(this);"/>
        </div>
    </c:forEach>
</div>
<div class="container">
    <span onclick="this.parentElement.style.display='none'" class="closebtn">&times;</span>
    <img id="expandedImg" style="width:50%" onclick="magnify('myimage', 3)" alt="main_image">
</div>
</body>
</html>
