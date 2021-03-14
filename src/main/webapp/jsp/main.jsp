<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="page" value="${'/jsp/main.jsp'}" scope="session"/>
<html>
<head>
    <title>Main</title>
    <script src="<c:url value="/js/time_counter.js"/>"></script>
</head>
<body>
<jsp:include page="support/header.jsp"/>

<c:forEach var="lot" items="${requestScope.lotList}">
    <form action="to_lot.do" method="post">
            ${lot.name}
            ${lot.description}
    </form>
</c:forEach>
<jsp:include page="support/footer.jsp"/>
</body>
</html>
