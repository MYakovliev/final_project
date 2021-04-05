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
    <title>Lot_Edit</title>
</head>
<body>
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="add_lot"/>
    <input type="text" name="name" placeholder="Name"/>
    <input type="text" name="description" placeholder="Description"/>
    <input type="datetime-local" name="startTime"/>
    <input type="file" name="image_path1"/>
    <img name="image_path1" src="" style="visibility: hidden" />
    <input type="file" name="image_path2" style="visibility: hidden"/>
    <img name="image_path2" src="" style="visibility: hidden"/>
    <input type="file" name="image_path3" style="visibility: hidden"/>
    <img name="image_path3" src="" style="visibility: hidden"/>
    <input type="file" name="image_path4" style="visibility: hidden"/>
    <img name="image_path4" src="" style="visibility: hidden"/>
    <input type="file" name="image_path5" style="visibility: hidden"/>
    <img name="image_path5" src="" style="visibility: hidden"/>
</form>
</body>
</html>
