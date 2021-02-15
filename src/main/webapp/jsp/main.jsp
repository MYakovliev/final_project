<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Main</title>
</head>
<body>
<form action="controller" method="get">
    Hello, ${name}
    <hr/>
        <input type="hidden" name="command" value="logout">
        <input type="submit" name="logoutBtn" value="log out">
</form>
</body>
</html>
