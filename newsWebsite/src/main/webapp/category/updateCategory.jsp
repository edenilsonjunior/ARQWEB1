<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<c:import url="../includes/header-default.jsp"/>


<main class="container">

	<c:if test="${not empty msg }">
		<div class="alert alert-danger" role="alert">${msg}</div>
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

<c:import url="../includes/footer.jsp"/>
<c:import url="../includes/footer-default.jsp"/>

