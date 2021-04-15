<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/custom.tld" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="page" value="${'/jsp/lots.jsp'}" scope="session"/>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title><fmt:message key="lots.title"/></title>
    <link href="<c:url value="/css/lots.css"/>" type="text/css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="support/header.jsp"/>
<tag:access role="seller"><a href="${pageContext.request.contextPath}/controller?command=to_lot_edit">Add
    Lot</a></tag:access>
<div class="main_block">
    <c:forEach var="lot" items="${lot_list}">
        <div class="lot_block"
             onclick="location.href='${pageContext.request.contextPath}/controller?command=to_lot&lot_id=${lot.id}'">
            <div class="lot_name">${lot.name}</div>
            <img src="${lot.images.get(0)}" alt="image"/>
            <div class="lot_cost">${lot.currentCost}&dollar;</div>
            <p class="time">${lot.finishTime.time}</p>
        </div>
    </c:forEach>
</div>
</table>
<nav aria-label="Navigation for countries">
    <c:if test="${lot_page_amount} mt 1 ">
        <ul class="pagination">
            <c:if test="${lot_active_page != 1}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/controller?command=${command}&lot_page=${lot_active_page-1}">Previous</a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${lot_page_amount}" var="i">
                <c:choose>
                    <c:when test="${lot_active_page eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="${pageContext.request.contextPath}/controller?command=${command}&lot_page=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${lot_active_page lt lot_page_amount}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/controller?command=${command}&lot_page=${lot_active_page+1}">Next</a>
                </li>
            </c:if>
        </ul>
    </c:if>
</nav>
<script src="<c:url value="/js/time_counter.js"/>"></script>
<jsp:include page="support/footer.jsp"/>
</body>
</html>
