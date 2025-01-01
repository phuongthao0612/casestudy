<%--
  Created by IntelliJ IDEA.
  User: Thao
  Date: 12/29/2024
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/utils/bootstrap.jsp" %>
<html>
<head>
    <title>Update Customer</title>
</head>
<body>
<div class="container">
    <form method="post">
        <input type="hidden" name="id" value="${customer.id}">
        <div class="form-group">
            <label for="updatedName">Name:</label>
            <input type="text" class="form-control" id="updatedName" name="updatedName" value="${customer.name}">
        </div>
        <div class="form-group">
            <label for="updatedAge">Age:</label>
            <input type="number" class="form-control" id="updatedAge" name="updatedAge" value="${customer.age}">
        </div>
        <div class="form-group">
            <label for="updatedPhone">Phone:</label>
            <input type="text" class="form-control" id="updatedPhone" name="updatedPhone" value="${customer.phone}">
        </div>
        <div class="form-group">
            <label for="updatedEmail">Email:</label>
            <input type="email" class="form-control" id="updatedEmail" name="updatedEmail" value="${customer.email}">
        </div>
        <div class="form-group">
            <label for="updatedClassId">Class:</label>
            <select class="form-control" id="updatedClassId" name="updatedClassId">
                <c:forEach var="gymClass" items="${gymClasses}">
                    <option value="${gymClass.classId}"
                        ${gymClass.classId == customer.idClass ? 'selected' : ''}>
                            ${gymClass.className}
                    </option>
                </c:forEach>

            </select>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>
</body>
</html>
