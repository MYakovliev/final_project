<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" type="text/css"
          rel="stylesheet"/>
    <link href="<c:url value="/css/lot_edit.css"/>" type="text/css"
          rel="stylesheet"/>
    <title>Lot_Edit</title>
</head>
<body>
<form class="container" action="${pageContext.request.contextPath}/upload/" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="add_lot"/>
    <input type="text" name="name" value="${lot.name}" placeholder="Name"/>
    <br/>
    <textarea name="description" placeholder="Description">${lot.description}</textarea>
    <br/>
    <input type="number" min="0.01" step="0.01" name="bid" value="${lot.currentCost}" placeholder="Put your start bid"/>
    <br/>
    <input type="datetime-local" value="${lot.startTime}" name="startTime"/>
    <input type="datetime-local" value="${lot.finishTime}" name="endTime"/>
    <br/>
    <img src="${lot.images.get(0)}" alt="image" id="image_path1Image" style="visibility: hidden"/>
    <img id="image_path2Image" src="${lot.images.get(1)}" style="visibility: hidden"/>
    <img id="image_path3Image" src="${lot.images.get(2)}" style="visibility: hidden"/>
    <img id="image_path4Image" src="${lot.images.get(3)}" style="visibility: hidden"/>
    <img id="image_path5Image" src="${lot.images.get(4)}" style="visibility: hidden"/>
    <br/>
    <input type="file" name="image_path1Input" required
           onchange="readURL(this, 'image_path1Image', 'image_path2Input')"/>
    <input type="file" id="image_path2Input" name="image_path2" style="visibility: hidden"
           onchange="readURL(this, 'image_path2Image', 'image_path3Input')"/>
    <input type="file" name="image_path3" id="image_path3Input" style="visibility: hidden"
           onchange="readURL(this, 'image_path3Image', 'image_path4Input')"/>
    <input type="file" name="image_path4" style="visibility: hidden" id="image_path4Input"
           onchange="readURL(this, 'image_path4Image', 'image_path5Input')"/>
    <input type="file" name="image_path5" style="visibility: hidden" id="image_path5Input"
           onchange="readURL(this, 'image_path5Image', null)"/>
    <br/>
    <input type="submit" name="btn" value="Submit"/>
    <p class="message"><a href="controller?command=to_lots">
        <fmt:message key="login.back_to_main"/></a>
    </p>
</form>
<script src="<c:url value="/js/open_images.js"/>"></script>
</body>
</html>
