<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="includes/navbar/header-default.jsp" %>
<c:choose>
    <c:when test="${sessionScope.isLogged == true}">
        <c:import url="includes/navbar/navbar-logged-in.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="includes/navbar/navbar-logged-out.jsp"/>
    </c:otherwise>
</c:choose>

<main class="container">

    <img class="" src="assets/errorimg" alt="error" >
    <h1 class="mt-2 text-center">404</h1>
    <h4 class="mt-2 text-center">Não Encontrado</h4>

</main>


<%@ include file="includes/footer/footer.jsp" %>
<%@ include file="includes/footer/footer-default.jsp" %>

