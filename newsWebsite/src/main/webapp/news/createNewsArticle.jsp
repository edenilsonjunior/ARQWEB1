<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../includes/navbar/header-default.jsp" %>
<%@ include file="../includes/navbar/navbar-logged-in.jsp" %>

<c:if test="${empty categoryList}">
    <c:redirect url="/utilServlet"/>
</c:if>

<main class="container">

    <div class="container form-container">
        <form action="createNewsArticle" method="post">
            <div class="mb-3">
                <label for="title" class="form-label">Título</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
            <div class="mb-3">
                <label for="author" class="form-label">Autor</label>
                <input type="text" class="form-control" id="author" name="author" required>
            </div>
            <div class="mb-3">
                <label for="publishDate" class="form-label">Data de Publicação</label>
                <input type="date" class="form-control" id="publishDate" name="publishDate" required>
            </div>
            <div class="mb-3">
                <label for="source" class="form-label">Fonte</label>
                <input type="text" class="form-control" id="source" name="source" required>
            </div>
            <div class="mb-3">
                <label for="summary" class="form-label">Resumo</label>
                <textarea class="form-control" id="summary" name="summary" rows="3" required></textarea>
            </div>
            <div class="mb-3">
                <label for="text" class="form-label">Texto</label>
                <textarea class="form-control" id="text" name="text" rows="5" required></textarea>
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
                <input type="text" class="form-control" id="image1" name="image1">
            </div>
            <div class="mb-3">
                <label for="image2" class="form-label">Imagem 2</label>
                <input type="text" class="form-control" id="image2" name="image2">
            </div>
            <button type="submit" class="btn btn-secondary px-4">Enviar</button>
        </form>
    </div>

</main>

<%@ include file="../includes/footer/footer.jsp" %>
<%@ include file="../includes/footer/footer-default.jsp" %>
