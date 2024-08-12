<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="includes/navbar/header-default.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test="${sessionScope.isLogged == true}">
        <c:import url="includes/navbar/navbar-logged-in.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="includes/navbar/navbar-logged-out.jsp"/>
    </c:otherwise>
</c:choose>

<main class="container">

    <!-- Carrossel de Imagens -->
    <div id="newsCarousel" class="carousel slide mb-3" data-bs-ride="carousel">
        <div class="carousel-inner bg-dark rounded">
            <c:forEach var="image" items="${news.images}" varStatus="status">
                <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                    <img class="d-block w-100 img-news rounded" src="${image}" alt="Image ${status.index + 1}"
                         style="object-fit: contain;">
                </div>
            </c:forEach>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#newsCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#newsCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

    <div class="d-flex justify-content-between align-items-center mt-2 mb-2">
            <div>
                <a href="deleteNewsArticle?id=${news.id}" class="btn btn-danger">Excluir </a>
                <a href="updateNewsArticle?id=${news.id}" class="btn btn-warning">Editar</a>
            </div>
    </div>

    <article class="blog-post p-4 p-md-5 mb-4 rounded bg-light shadow-sm">
        <div class="blog-post-header mb-3">
            <h2 class="display-4 link-body-emphasis mb-1">${news.title}</h2>
            <p class="blog-post-meta text-muted" style="font-size: 1.2em;">${news.category.category}</p>
            <p class="blog-post-meta text-muted">${news.publishDate} por <a class="text-decoration-none">${news.author}</a>
            </p>
        </div>
        <div class="blog-post-body">
            <p class="lead my-3">${news.text}</p>
            <p class="lead my-3 text-muted">Fonte: ${news.source}</p>
        </div>
    </article>

    <h3 class="display-6 link-body-emphasis mb-2 mt-4">Comentários</h3>
    <c:if test="${sessionScope.isLogged == true}">
        <form action="news" method="post">
            <input type="hidden" name="newsId" value="${news.id}">
            <div class="form-comment">
                <textarea class="form-control" name="comment" minlength="2" required placeholder="Comente aqui"
                          id="floatingTextarea2" style="height: 100px"></textarea>
                <button type="submit" class="btn btn-outline-primary">Enviar</button>
            </div>
        </form>
    </c:if>

    <c:if test="${sessionScope.isLogged != true}">
        <div class="alert alert-warning" role="alert">
            Você precisa estar logado para comentar.
        </div>
    </c:if>
    <c:if test="${not empty listCommentary}">
        <c:forEach var="commentary" items="${listCommentary}">
            <div class="comment">
                <p class="comment-author"><strong>${commentary.user.username}</strong></p>
                <p class="comment-text">${commentary.text}</p>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${empty listCommentary}">
        <p>Sem comentários.</p>
    </c:if>
</main>

<%@ include file="includes/footer/footer.jsp" %>
<%@ include file="includes/footer/footer-default.jsp" %>