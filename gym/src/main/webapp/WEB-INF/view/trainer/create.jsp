<%--
  Created by IntelliJ IDEA.
  User: Thao
  Date: 1/1/2025
  Time: 9:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/view/utils/bootstrap.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <form method="post">
        <div class="form-group">
            <label for="name">Tên:</label>
            <input type="text" class="form-control" id="name" name="name">
        </div>
        <div class="form-group">
            <label for="specialization">Chuyên môn:</label>
            <input type="text" class="form-control" id="specialization" name="specialization">
        </div>
        <div class="form-group">
            <label for="phone">Số điện thoại:</label>
            <input type="number" class="form-control" id="phone" name="phone">
        </div>
        <div class="form-group">
            <label for="className">Lớp dạy:</label>
            <select class="form-control" id="className" name="idClass">
                <c:forEach var="gymClass" items="${gymClasses}">
                    <option value="${gymClass.classId}">${gymClass.className}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>

    </form>
</div>

</body>
</html>
