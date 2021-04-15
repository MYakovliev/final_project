<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/custom.tld" %>
<%--
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
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title><fmt:message key="header.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>" type="text/css"/>
    <script src="<c:url value="/js/header.js"/>"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
</head>
<body>
<div class="topnav" id="topnavElement">
    <a class="active" href="${pageContext.request.contextPath}/controller?command=to_lots"><fmt:message
            key="header.home"/></a>
    <a href="#footer"><fmt:message key="header.contact"/></a>
    <tag:access role="admin"><a href="${pageContext.request.contextPath}/controller?command=to_admin"><fmt:message
            key="header.adminpage"/></a></tag:access>
    <tag:access role="seller"><a href="${pageContext.request.contextPath}/controller?command=to_lots"><fmt:message
            key="header.mylots"/></a></tag:access>
    <tag:access role="buyer"><a href="${pageContext.request.contextPath}/controller?command=to_lots"><fmt:message
            key="header.mylots"/></a></tag:access>
    <div class="topnav-right">
        <div class="buttons">
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <a href="${pageContext.request.contextPath}/controller?command=to_login">
                        <fmt:message key="header.signin"/>
                    </a>
                    <a href="${pageContext.request.contextPath}/controller?command=to_registration">
                        <fmt:message key="header.signup"/>
                    </a>
                </c:when>
                <c:otherwise>
                <a href="${pageContext.request.contextPath}/controller?command=to_profile&user_id=${sessionScope.user.id}">
                        ${sessionScope.user.name}
                    <img src="${pageContext.request.contextPath}/${sessionScope.user.avatar}" class="avatar"
                         alt="Avatar"/>
                </a>

                    <a href="${pageContext.request.contextPath}/controller?command=to_payment">${sessionScope.user.balance}</a>
                    <a href="${pageContext.request.contextPath}/controller?command=logout">
                        <fmt:message key="header.signout"/>
                    </a>
                </c:otherwise>
            </c:choose>
            <a href="#modal" id="globe">
                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 22 22">
                    <g fill="#FFF" fill-rule="nonzero">
                        <path d="M20.166 5.382l-.324.103-1.725.155-.487.782-.354-.113-1.373-1.244-.2-.647-.266-.69-.863-.778-1.018-.2-.023.468.997.978.488.578-.549.288-.446-.132-.67-.28.023-.543-.878-.363-.292 1.275-.885.202.088.711 1.153.223.2-1.137.951.142.443.26h.71l.486.978 1.288 1.313-.094.51-1.04-.132-1.794.91-1.293 1.558-.168.69h-.464l-.864-.4-.84.4.21.89.365-.423.642-.02-.045.8.532.156.532.6.868-.245.99.157 1.152.31.575.069.974 1.111 1.882 1.112-1.217 2.336-1.284.6-.488 1.334-1.858 1.247-.198.719A10.863 10.863 0 0 0 22 11.435a10.87 10.87 0 0 0-1.834-6.053z"></path>
                        <path d="M11.445 15.274l-.748-1.384.686-1.428-.686-.204-.77-.773-1.708-.382-.566-1.184v.703h-.25L5.932 8.63V6.995l-1.079-1.75-1.712.304H1.988l-.58-.38.74-.586-.739.17A10.172 10.172 0 0 0 0 9.919c0 5.66 4.595 10.249 10.264 10.249.437 0 .865-.039 1.289-.09l-.108-1.241s.472-1.844.472-1.907c-.001-.063-.472-1.655-.472-1.655zM4.391 3.252l2.041-.25.94-.452 1.06.267 1.69-.082.58-.8.845.123 2.051-.17.566-.546.797-.468 1.128.15.411-.055A12.79 12.79 0 0 0 11.61 0C8.043 0 4.854 1.427 2.75 3.667h.006l1.635-.415zm7.704-2.25l1.173-.566.754.382-1.09.729-1.042.091-.47-.267.675-.368zm-3.475.084l.518.19.678-.19.37.561-1.566.361-.753-.386s.736-.416.753-.536z"></path>
                    </g>
                </svg>
            </a>

            <div id="modal" class="modal">
                <div class="modal__content">
                    <h1><fmt:message key="header.changelanguage"/></h1>
                    <a href="#" class="modal__close">&times;</a>
                    <div class="languages">
                        <a id="en" href="${pageContext.request.contextPath}/controller?command=change_language&lang=en">EN</a>
                        <a id="ru" href="${pageContext.request.contextPath}/controller?command=change_language&lang=ru">RU</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <a href="javascript:void(0);" class="icon" onclick="openList()">
        <i class="fa fa-bars"></i>
    </a>
</div>
</body>
</html>