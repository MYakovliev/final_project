<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 10.01.2021
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html><title>Error Page</title>
<body>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.exception}
<br/>
Message from exception: ${pageContext.exception.message}
<br/>
Stack trace: ${pageContext.exception.printStackTrace()}
</body>
</html>
