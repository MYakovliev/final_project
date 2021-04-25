<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="page" value="${'/jsp/admin.jsp'}" scope="session"/>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <link rel="stylesheet" href="<c:url value="/css/admin.css"/>" type="text/css"/>
    <script src="<c:url value="/js/open_tab.js"/>"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">

    <title><fmt:message key="admin.title"/></title>
</head>
<body>
<jsp:include page="support/header.jsp"/>
<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'Users')"><fmt:message key="admin.users"/></button>
    <button class="tablinks" onclick="openTab(event, 'Lots')"><fmt:message key="admin.lots"/></button>
</div>
<div id="Users" class="tabcontent">
    <form action="${pageContext.request.contextPath}/controller?command=search_user_by_name" method="post">
        <input name="search" type="text" placeholder="<fmt:message key="search"/>" value="${search}"/>
        <button type="submit">&hookleftarrow;</button>
    </form>
    <table>
        <tr>
            <th><fmt:message key="admin.id"/></th>
            <th><fmt:message key="admin.name"/></th>
            <th><fmt:message key="admin.mail"/></th>
            <th><fmt:message key="admin.balance"/>, &dollar;</th>
            <th><fmt:message key="admin.role"/></th>
            <th><fmt:message key="admin.ban-unban"/></th>
        </tr>
        <c:forEach var="user" items="${requestScope.user_list}">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/controller?command=to_profile&user_id=${user.id}">${user.id}</a>
                </td>
                <td>${user.name}</td>
                <td>${user.mail}</td>
                <td>${user.balance}</td>
                <td><fmt:message key="${user.userRole}"/></td>
                <td>
                    <c:if test="${user.id ne sessionScope.user.id}">
                        <c:choose>
                            <c:when test="${user.banned}"><a
                                    href="${pageContext.request.contextPath}/controller?command=unban_user&user_id=${user.id}&user_active_page=${user_active_page}&lot_active_page=${lot_active_page}">unban</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/controller?command=ban_user&user_id=${user.id}&user_active_page=${user_active_page}&lot_active_page=${lot_active_page}">ban</a>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="Navigation for countries">
        <c:if test="${user_page_amount > 1}">
            <ul class="pagination">
                <c:if test="${user_active_page != 1}">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/controller?command=${user_pagingcommand}&user_page=${user_active_page-1}"><fmt:message key="paging.previous"/></a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${user_page_amount}" var="i">
                    <c:choose>
                        <c:when test="${user_active_page eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link"
                                                     href="${pageContext.request.contextPath}/controller?command=${user_paging_command}&user_page=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${user_active_page lt user_page_amount}">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/controller?command=${user_paging_command}&user_page=${user_active_page+1}"><fmt:message key="paging.next"/></a>
                    </li>
                </c:if>
            </ul>
        </c:if>
    </nav>
</div>

<div id="Lots" class="tabcontent">
    <form action="${pageContext.request.contextPath}/controller?command=search_lot_by_name" method="post">
        <input name="search" type="text" placeholder="<fmt:message key="search"/>" value="${search}"/>
        <button type="submit">&hookleftarrow;</button>
    </form>
    <table>
        <tr>
            <th><fmt:message key="admin.id"/></th>
            <th><fmt:message key="admin.name"/></th>
            <th><fmt:message key="admin.start_time"/></th>
            <th><fmt:message key="admin.finish_time"/></th>
            <th><fmt:message key="admin.current_bid"/></th>
            <th><fmt:message key="admin.seller_id"/></th>
            <th><fmt:message key="admin.buyer_id"/></th>
        </tr>
        <c:forEach var="lot" items="${requestScope.lot_list}">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/controller?command=to_lot&lot_id=${lot.id}">${lot.id}</a>
                </td>
                <td>${lot.name}</td>
                <td>${lot.startTime}</td>
                <td>${lot.finishTime}</td>
                <td>${lot.currentCost}</td>
                <td><a href="${pageContext.request.contextPath}/controller?command=to_profile&user_id=${lot.sellerId}">
                        ${lot.sellerId}</a></td>
                <td><c:choose>
                    <c:when test="${lot.buyerId eq 0}">
                        ---
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/controller?command=to_profile&user_id=${lot.buyerId}">${lot.buyerId}</a>
                    </c:otherwise>
                </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="Navigation for countries">
        <c:if test="${lot_page_amount > 1}">
            <ul class="pagination">
                <c:if test="${lot_active_page != 1}">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/controller?command=${lot_paging_command}&lot_page=${lot_active_page-1}"><fmt:message key="paging.previous"/></a>
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
                                                     href="${pageContext.request.contextPath}/controller?command=${lot_paging_command}&lot_page=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${lot_active_page lt lot_page_amount}">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/controller?command=${lot_paging_command}&lot_page=${lot_active_page+1}"><fmt:message key="paging.next"/></a>
                    </li>
                </c:if>
            </ul>
        </c:if>
    </nav>
</div>
</body>
</html>
