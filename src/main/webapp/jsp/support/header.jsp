<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- todo use bundle
  Created by IntelliJ IDEA.
  User: nicki
  Date: 2/21/2021
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="css/header.css" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
</head>
<body>
<div class="topnav" id="topnavElement">
    <a class="active"><fmt:message key="header.home"/></a>
    <a>Contact</a>
    <div class="topnav-right">

        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="to_login">
                    <input type="submit" name="but" value="login"/>
                </form>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="to_registration">
                    <input type="submit" name="but" value="register"/>
                </form>
            </c:when>
            <c:otherwise>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="logout">
                    <input type="submit" name="but" value="logout"/>
                </form>
                <img src="${sessionScope.user.avatar}" alt="Avatar"/>
                ${sessionScope.user.name}
            </c:otherwise>
        </c:choose>
    </div>
    <a href="javascript:void(0);" class="icon" onclick="openList()">
        <i class="fa fa-bars"></i>
    </a>
</div>
<script>
    function openList() {
        let x = document.getElementById("topnavElement");
        if (x.className === "topnav") {
            x.className += " responsive";
        } else {
            x.className = "topnav";
        }
    }
</script>
</body>
</html>