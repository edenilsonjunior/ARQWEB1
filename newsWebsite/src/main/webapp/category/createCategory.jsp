<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

    <c:if test="${not empty message}">
        <div style="color: red;">${message}</div>
    </c:if>

    <h1>Criar Categoria</h1>
    <form action="../createCategory" method="post">
        <label for="category">Nome da Categoria:</label>
        <input type="text" id="category" name="category" required>
        <br>
        <input type="submit" value="Criar Categoria">
    </form>
</body>
</html>
