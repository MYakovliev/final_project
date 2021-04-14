<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 4/3/2021
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" type="text/css"
          rel="stylesheet"/>
    <script src="<c:url value="/js/open_images.js"/>"></script>
    <title>Lot_Edit</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/upload/" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="add_lot"/>
    <input type="text" name="name" value="${lot.name}" placeholder="Name"/>
    <br/>
    <textarea name="description">${lot.description}</textarea>
    <br/>
    <input type="number" min="0.01" step="0.01" name="bid" value="${lot.currentCost}" placeholder="Put your start bid"/>
    <input type="datetime-local" value="${lot.startTime}" name="startTime"/>
    <input type="datetime-local" value="${lot.finishTime}" name="endTime"/>
    <input type="file" name="image_path1" required />
    <img src="${lot.images.get(0)}" alt="image" id="image_path1"/>
    <input type="file" class="image_path2" name="image_path2"/>
    <img name="image_path2" src="${lot.images.get(1)}"/>
    <input type="file" name="image_path3"/>
    <img name="image_path3" src="${lot.images.get(2)}"/>
    <input type="file" name="image_path4"/>
    <img name="image_path4" src="${lot.images.get(3)}"/>
    <input type="file" name="image_path5"/>
    <img name="image_path5" src="${lot.images.get(4)}"/>
    <input type="submit" name="btn" value="Submit"/>
</form>
</body>
</html>
