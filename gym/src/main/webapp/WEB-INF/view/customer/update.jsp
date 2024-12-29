<%--
  Created by IntelliJ IDEA.
  User: Thao
  Date: 12/29/2024
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <form method="post">
        <div class="form-group">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${customer.id}">
        </div>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name">
        </div>
        <div class="form-group">
            <label for="age">Name:</label>
            <input type="number" class="form-control" id="age" name="age">
        </div>
        <div class="form-group">
            <label for="phone">Name:</label>
            <input type="number" class="form-control" id="phone" name="phone">
        </div>
        <div class="form-group">
            <label for="email">Name:</label>
            <input type="text" class="form-control" id="email" name="email">
        </div>
        <button type="submit" class="btn btn-primary">Update</button>

    </form>
</div>

</body>
</html>
