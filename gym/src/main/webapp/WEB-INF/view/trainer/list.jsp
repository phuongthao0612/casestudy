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
    <h1>Danh Sách Huấn Luyện Viên</h1>
    <button class="btn btn-primary m-4" onclick="window.location.href='/trainer?action=create'">Add New</button>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Chuyên môn</th>
            <th>Điện Thoại</th>
            <th>Lớp dạy</th>
            <th colspan="2">Chức năng</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="trainer" items="${trainers}" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${trainer.name}</td>
                <td>${trainer.specialization}</td>
                <td>${trainer.phone}</td>
                <td>${trainer.className}</td>
                <td>
                    <button class="btn btn-warning"
                            onclick="window.location.href='/trainer?action=update&id=${trainer.id}'">Update
                    </button>
                </td>
                <td>
                    <button class="btn btn-danger"
                            onclick="window.location.href='/trainer?action=delete&id=${trainer.id}'">Delete
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
