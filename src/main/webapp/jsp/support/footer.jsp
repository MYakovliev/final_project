<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 3/15/2021
  Time: 12:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value="/css/footer.css"/>" type="text/css" rel="stylesheet"/>
</head>
<body>
<footer class="footer">
    <div class="footer__addr">
        <h1 class="footer__logo">Thank you for visiting</h1>

        <h2>Contact</h2>

        <address>
            3rd Builders street, home 25<br>

            <a class="footer__btn" href="mailto:example@gmail.com">Email Us</a>
        </address>
    </div>

    <ul class="footer__nav">
        <li class="nav__item">
            <h2 class="nav__title">Media</h2>
            <ul class="nav__ul">
                <li>
                    <a href="#">Online</a>
                </li>
                <li>
                    <a href="#">Print</a>
                </li>
                <li>
                    <a href="#">Alternative Ads</a>
                </li>
            </ul>
        </li>

        <li class="nav__item nav__item--extra">
            <h2 class="nav__title">Technology</h2>

            <ul class="nav__ul nav__ul--extra">
                <li>
                    <a href="#">Hardware Design</a>
                </li>

                <li>
                    <a href="#">Software Design</a>
                </li>

                <li>
                    <a href="#">Digital Signage</a>
                </li>

                <li>
                    <a href="#">Automation</a>
                </li>

                <li>
                    <a href="#">Artificial Intelligence</a>
                </li>

                <li>
                    <a href="#">IoT</a>
                </li>
            </ul>
        </li>

        <li class="nav__item">
            <h2 class="nav__title">Legal</h2>

            <ul class="nav__ul">
                <li>
                    <a href="#">Privacy Policy</a>
                </li>

                <li>
                    <a href="#">Terms of Use</a>
                </li>

                <li>
                    <a href="#">Sitemap</a>
                </li>
            </ul>
        </li>
    </ul>
    <div class="legal">
        <p>&copy; 2021 All rights reserved.</p>
    </div>
</footer>
</body>
</html>
