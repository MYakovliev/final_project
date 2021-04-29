<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<style>
    body {
        margin-top: 25%;
        font-size: 35px;
        alignment: center;
        background-color: blanchedalmond;
        text-align: center;
    }
</style>
<body>
<h3>Hello and Welcome</h3>
<a href="${pageContext.request.contextPath}/controller?command=to_lots">See the lots</a>
</body>
</html>