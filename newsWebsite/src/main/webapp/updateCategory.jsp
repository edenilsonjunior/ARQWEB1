<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<c:import url="includes/navbar/header-default.jsp"/>
<c:import url="includes/navbar/navbar-logged-in.jsp"/>

<c:if test="${sessionScope.isLogged == false}">
    <c:redirect url="errors/401.jsp"/>
</c:if>

<main class="container">

    <c:if test="${not empty error }">
        <div class="alert alert-danger" role="alert">${error}</div>
    </c:if>

    <h1>Editar categoria</h1>
    <form action="updateCategory" method="post">
        <input type="hidden" name="id" value="${category.id}">

        <div class="row my-1 align-items-center">
            <div class="col-2">
                <label for="categoryName" class="form-label"> Categoria: </label>
            </div>
            <div class="col-8">
                <input name="categoryName" class="form-control" value="${category.category}">
            </div>
        </div>

        <button class="btn btn-primary">Enviar</button>
    </form>


</main>

<c:import url="includes/footer/footer.jsp"/>
<c:import url="includes/footer/footer-default.jsp"/>

