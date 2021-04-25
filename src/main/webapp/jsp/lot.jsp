<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/custom.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 2/25/2021
  Time: 9:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title><fmt:message key="lot.title"/></title>
    <link href="<c:url value="/css/lot.css"/>" type="text/css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="support/header.jsp"/>
<div class="content_container">
    <jsp:useBean id="today" class="java.util.Date"/>

    <c:if test="${lot.finishTime.before(today) and lot.buyerId ne 0}">
        <tag:access role="admin">
        <a class="submitbtn" href="${pageContext.request.contextPath}/controller?command=submit_winner&lot_id=${lot.id}">&#10004;</a>
        </tag:access>
    </c:if>
    <a href="${pageContext.request.contextPath}/controller?command=to_profile&user_id=${lot.sellerId}">
        <fmt:message key="lot.to_seller"/>
    </a>
    <c:if test="${error ne null}">
        <p class="error"><fmt:message key="${error}"/></p>
    </c:if>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="make_bid"/>
        <input type="hidden" name="lot_id" value="${lot.id}">
        <span id="lot_name">${lot.name}</span><br/>
        ${lot.description}<br/><br/><br/>
        <span id="lot_cost">${lot.currentCost}&dollar;</span><br/>
        <p class="time">${lot.finishTime.time}</p>
        <input type="number" min="0.01" step="0.01" name="bid"/>
        <input type="submit" name="btn" value="<fmt:message key="lot.make_bid"/>"/>
    </form>
    <c:if test="${lot.buyerId ne 0}">
        <a href="${pageContext.request.contextPath}/controller?command=to_profile&user_id=${lot.buyerId}">
            <fmt:message key="lot.to_buyer"/>
        </a>
    </c:if>
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
<c:if test="${user_list.size() ne 0}">
    <table>
        <tr>
            <th>
                <fmt:message key="lot.buyer_history"/>
            </th>
        </tr>
        <c:forEach var="user" items="${user_list}">
            <tr>
                <td>
                        ${user.name}
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<script src="<c:url value="/js/time_counter.js"/>"></script>
<script src="<c:url value="/js/magnifier.js"/>"></script>
<script src="<c:url value="/js/show_picture.js"/>"></script>
</body>
</html>
