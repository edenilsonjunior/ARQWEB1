<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../includes/navbar/header-default.jsp" %>
<c:choose>
    <c:when test="${sessionScope.isLogged == true}">
        <c:import url="../includes/navbar/navbar-logged-in.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="../includes/navbar/navbar-logged-out.jsp"/>
    </c:otherwise>
</c:choose>

<main class="container">

    <div class="d-flex flex-column align-items-center my-5">
        <img class="" src="assets/images/500.png" alt="error" width="400px">
        <h1 class="mt-5 text-center">Erro Interno do Servidor</h1>
        <h4 class="mt-2 text-center">Algo deu errado do nosso lado. Estamos trabalhando para resolver o problema.
            Tente novamente mais tarde ou volte ao <a href="index.html" class="text-primary">início</a>.
        </h4>
        <p class="mt-2 text-center">Código do erro: 500</p>
    </div>
</main>

<%@ include file="../includes/footer/footer.jsp" %>
<%@ include file="../includes/footer/footer-default.jsp" %>
