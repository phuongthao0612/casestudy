<%--
  Created by IntelliJ IDEA.
  User: Thao
  Date: 12/29/2024
  Time: 1:53 PM
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
    <h1>Danh Sách Khách Hàng</h1>
    <button class="btn btn-primary m-4" onclick="window.location.href='/customer?action=create'">Add New</button>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Tuổi</th>
            <th>Điện Thoại</th>
            <th>Email</th>
            <th colspan="2">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="customer" items="${customers}" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${customer.name}</td>
                <td>${customer.age}</td>
                <td>${customer.phone}</td>
                <td>${customer.email}</td>
                <td>
                    <button class="btn btn-warning"
                            onclick="window.location.href='/customer?action=update&id=${customer.id}'">Update
                    </button>
                </td>
                <td>
                    <button class="btn btn-danger"
                            onclick="window.location.href='/customer?action=delete&id=${customer.id}'">Delete
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
