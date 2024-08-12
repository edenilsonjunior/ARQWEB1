<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="includes/navbar/header-default.jsp" %>


<c:if test="${sessionScope.isLogged == false}">
    <c:redirect url="errors/401.jsp"/>
</c:if>

	<main class="container">

		<c:if test="${not empty error}">
	        <div class="alert alert-danger" role="alert">${error}</div>
	    </c:if>

        <div class="container-login pt-1 pb-5 my-5">
            <h1 class="my-5 text-center">Criar Categoria</h1>

            <div class="container form-container">
                <form action="createCategory" method="post">
                    <div class="mb-3">
                    <label for="category" class="form-label">Nome</label>
                    <input class="form-control" id="category" name="category">
                    </div>
                    <button type="submit" class="btn btn-secondary px-4">Enviar</button>
                </form>
            </div>
        </div>

    </main>


<c:import url="includes/footer/footer.jsp"/>
<c:import url="includes/footer/footer-default.jsp"/>
