<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="../includes/header-default.jsp" %>


<c:if test="${empty map}">
    <c:redirect url="/retrieveCategory"/>
</c:if>



<c:choose>
    <c:when test="${sessionScope.isLogged == true}">
        <c:import url="../includes/navbar-logged-in.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="../includes/navbar-logged-out.jsp"/>
    </c:otherwise>
</c:choose>

<main class="container">

    <h1 class="text-center mb-4" >Noticias por categoria</h1>

    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">${error}</div>
    </c:if>

    <c:if test="${not empty map}">
        <c:forEach var="entry" items="${map}">
            <div class="d-flex justify-content-between align-items-center border border-secondary rounded px-2 py-2">
                <h2 class="me-4">${entry.key.category}</h2>
                <c:if test="${sessionScope.isLogged == true}">
                    <div>
                        <a href="deleteCategory?id=${entry.key.id}" class="btn btn-danger">Excluir</a>
                        <a href="updateCategory?id=${entry.key.id}" class="btn btn-warning">Editar</a>
                    </div>
                </c:if>
            </div>
            <ul>
                <c:if test="${empty entry.value}">
                    <li class="text-danger">Nenhuma notícia cadastrada</li>
                </c:if>

                <c:forEach var="newsArticle" items="${entry.value}">
                    <li>${newsArticle.title}</li>
                </c:forEach>
            </ul>
        </c:forEach>
    </c:if>

</main>

<c:import url="../includes/footer.jsp"/>
<c:import url="../includes/footer-default.jsp"/>
