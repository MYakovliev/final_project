<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 2/10/2021
  Time: 11:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Login</title>
    <link href="<c:url value="/css/login.css"/>" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="login-page">
    <div class="form">
        <form class="login-form" action="login.do" method="post"/>
            <input type="hidden" name="command" value="login"/>
            <input type="text" placeholder="username" name="login"/>
            <input type="password" placeholder="password" name="password"/>
            <input type="submit" name="btn" value="log in"/>
            <p class="message">Not registered? <a href="controller?command=to_registration" >Create an account</a></p>
            <p class="message">Don't want to log in?<a href="controller?command=to_main" >Back to main</a></p>
        </form>
    </div>
</div>
<%--<div class="login">--%>
<%--    <div class="login_title">--%>
<%--        <span>Login to your account</span>--%>
<%--    </div>--%>
<%--    <div class="login_fields">--%>
<%--        <form action="login.do" method="post">--%>
<%--            <div class="login_fields__user">--%>
<%--                <div class="icon">--%>
<%--                    <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/user_icon_copy.png'/>--%>
<%--                </div>--%>
<%--                <input type='text' placeholder='Username' name="login"/>--%>
<%--                <div class="validation">--%>
<%--                    <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/tick.png'/></div>--%>
<%--            </div>--%>
<%--            <div class="login_fields__password">--%>
<%--                <div class="icon">--%>
<%--                    <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/lock_icon_copy.png'/></div>--%>
<%--                <input type='password' placeholder='Password' name="password"/>--%>
<%--                <div class="validation">--%>
<%--                    <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/tick.png'/></div>--%>
<%--            </div>--%>
<%--            <div class="login_fields__submit">--%>

<%--                <input type="hidden" name="command" value="login"/>--%>
<%--                <input type='submit' value='Log In'/>--%>
<%--            </div>--%>
<%--        </form>--%>
<%--        <div class="login_fields__submit">--%>
<%--            <div class="forgot">--%>
<%--                <a href='#'> Forgot password?</a> <br/>--%>
<%--                <a href="#">Sigh up</a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<div class="authent">--%>
<%--    <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/puff.svg'/>--%>
<%--    <p>Authenticating...</p>--%>
<%--</div>--%>
<%--    <div id="input_container">--%>
<%--        <div id="input_container__inner">--%>
<%--            <form action="controller" method="get">--%>
<%--                <input type="hidden" name="command" value="login"/>--%>
<%--                login:<br/>--%>
<%--                <label>--%>
<%--                    <input type="text" name="login" value="login"/>--%>
<%--                </label> <br/>--%>
<%--                Password:<br/>--%>
<%--                <label>--%>
<%--                    <input type="password" name="password" value="password" required pattern=".{3, 40}" />--%>
<%--                </label> <br/>--%>
<%--                <input type="submit" name="but" value="log in"/>--%>
<%--            </form>--%>
<%--            <form action="controller" method="post">--%>
<%--                <input type="hidden" name="command" value="to_registration">--%>
<%--                <input type="submit" name="but" value="register">--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
</body>
</html>

