<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/navbar/header-default.jsp" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test="${sessionScope.isLogged == true}">
        <c:import url="../includes/navbar/navbar-logged-in.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="../includes/navbar/navbar-logged-out.jsp"/>
    </c:otherwise>
</c:choose>

<h1 align="center">Noticias</h1>

<main class="container">
    <c:if test="${not empty listNews}">
        <c:forEach var="news" items="${listNews}">
            <div class="bk-book p-4 p-md-5 mb-4 rounded text-body-emphasis bg-body-tertiary">
                <div class="col-lg-6 px-0">
                    <h1 class="display-4 fst-italic">${news.title}</h1>
                    <p class="lead my-3">${news.text}</p>
                    <p class="lead mb-0"><a href="news?id=${news.id}" class="fw-bold">Continue reading...</a></p>
                    <small>${news.author}</small>
                </div>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${empty listNews}">
        <div class="alert alert-warning" role="alert">
            Nenhuma notícia encontrada para a busca realizada.
        </div>
    </c:if>
</main>

<%@ include file="../includes/footer/footer.jsp" %>
<%@ include file="../includes/footer/footer-default.jsp" %>