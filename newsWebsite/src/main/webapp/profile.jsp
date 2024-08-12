<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="includes/navbar/header-default.jsp" %>
<%@ include file="includes/navbar/navbar-logged-in.jsp" %>

<c:if test="${sessionScope.isLogged == false}">
    <c:redirect url="errors/401.jsp"/>
</c:if>

<main class="container">

    <h1 class="my-5 text-center">${user.username}</h1>

    <div class="container form-container">
        <form action="updateUser" method="post">
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="${user.email}"
                       aria-describedby="emailHelp">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Nova Senha</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
            <div class="mb-3">
                <label for="confirmPassword" class="form-label">Confirmar Nova Senha</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
            </div>
            <button type="submit" class="btn btn-secondary px-4">Editar</button>
        </form>
    </div>

</main>

<%@ include file="includes/footer/footer.jsp" %>
<%@ include file="includes/footer/footer-default.jsp" %>



