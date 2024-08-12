<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="includes/navbar/header-default.jsp" %>
<%@ include file="includes/navbar/navbar-logged-in.jsp" %>

<c:if test="${sessionScope.isLogged == false}">
    <c:redirect url="errors/401.jsp"/>
</c:if>


<c:if test="${empty isLoaded}">
    <c:redirect url="updateNewsArticle"/>
</c:if>

<c:if test="${empty newsArticle}">
    <c:redirect url="index.jsp"/>
</c:if>


<main class="container">

    <h1 class="my-5 text-center">Atualizar Notícia</h1>

    <div class="container form-container">
        <form action="updateNewsArticle" method="post">
            <input type="hidden" name="id" value="${newsArticle.id}">
            <div class="mb-3">
                <label for="title" class="form-label">Título</label>
                <input type="text" class="form-control" id="title" name="title" value="${newsArticle.title}" required>
            </div>
            <div class="mb-3">
                <label for="author" class="form-label">Autor</label>
                <input type="text" class="form-control" id="author" name="author" value="${newsArticle.author}" required>
            </div>
            <div class="mb-3">
                <label for="publishDate" class="form-label">Data de Publicação</label>
                <input type="date" class="form-control" id="publishDate" name="publishDate" value="${newsArticle.publishDate}" required>
            </div>
            <div class="mb-3">
                <label for="source" class="form-label">Fonte</label>
                <input type="text" class="form-control" id="source" name="source" value="${newsArticle.source}" required>
            </div>
            <div class="mb-3">
                <label for="summary" class="form-label">Resumo</label>
                <textarea class="form-control" id="summary" name="summary" rows="3" required >${newsArticle.summary}</textarea>
            </div>
            <div class="mb-3">
                <label for="text" class="form-label">Texto</label>
                <textarea class="form-control" id="text" name="text" rows="5" required>${newsArticle.text}</textarea>
            </div>
            <div class="mb-3">
                <label for="category" class="form-label">Categoria</label>
                <select class="form-select" id="category" name="category" required>
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.id}">${category.category}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="image1" class="form-label">Imagem 1</label>
                <input type="text" class="form-control" id="image1" name="image1" value="${newsArticle.images[0]}">
            </div>
            <div class="mb-3">
                <label for="image2" class="form-label">Imagem 2</label>
                <input type="text" class="form-control" id="image2" name="image2" value="${newsArticle.images[1]}">
            </div>
            <button type="submit" class="btn btn-warning px-4">Editar</button>
        </form>
    </div>

</main>

<%@ include file="includes/footer/footer.jsp" %>
<%@ include file="includes/footer/footer-default.jsp" %>
