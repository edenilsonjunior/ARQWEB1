<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="includes/navbar/header-default.jsp" %>


<!-- Navbar -->
<c:choose>
    <c:when test="${sessionScope.isLogged == true}">
        <c:import url="includes/navbar/navbar-logged-in.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="includes/navbar/navbar-logged-out.jsp"/>
    </c:otherwise>
</c:choose>


<main class="container">

    <h1 class="my-5 text-center">Notícias por Categoria</h1>

    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">${error}</div>
    </c:if>

    <!-- Botao para criar categoria-->
    <c:if test="${sessionScope.isLogged == true}">
        <div class="d-flex justify-content-start my-3">
            <a href="createCategory" class="btn btn-primary">Criar categoria</a>
        </div>
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

            <c:if test="${empty entry.value}">
                <li class="text-danger my-3">Nenhuma notícia cadastrada</li>
            </c:if>

            <c:set var="counter" value="0"/>
            <c:forEach var="newsArticle" items="${entry.value}">

                <c:if test="${counter % 4 == 0}">
                    <div class="row my-3">
                </c:if>

                <div class="col-sm-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${newsArticle.title}</h5>
                            <p class="card-text">Autor: ${newsArticle.author} </p>
                            <p class="card-text">Publicado em: ${newsArticle.publishDate} </p>
                            <a href="news?id=${newsArticle.id}" class="btn btn-primary">Leia mais</a>
                        </div>
                    </div>
                </div>

                <c:set var="counter" value="${counter + 1}"/>

                <c:if test="${counter % 4 == 0 || counter == fn:length(entry.value)}">
                    </div>
                </c:if>

            </c:forEach>
        </c:forEach>
    </c:if>

</main>

<c:import url="includes/footer/footer.jsp"/>
<c:import url="includes/footer/footer-default.jsp"/>
