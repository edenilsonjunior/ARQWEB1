<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="includes/navbar/header-default.jsp" %>


<div id="nav-bar"></div>

<%--Chamando um servlet que popula a index--%>
<c:if test="${empty isLoaded || isLoaded == false}">
    <c:redirect url="index"/>
</c:if>


<main class="container">


    <%-- Boas-vindas ao site --%>
    <div class="p-4 p-md-5 mb-4 rounded text-body-emphasis bg-body-secondary bk-news">
        <div class="col-lg-6 px-0 bg-dark text-light rounded px-2 py-2">
            <h1 class="display-4 fst-italic">Bem-vindo ao IFNews!</h1>
            <p class="lead my-3">Este sistema foi desenvolvido para oferecer uma plataforma completa e intuitiva para a
                gestão e visualização de notícias.</p>
            <p class="lead mb-0"><a href="about.jsp" class="fw-bold">Continue lendo...</a></p>
        </div>
    </div>


    <%--Exibindo as duas noticias mais recentes--%>
    <c:choose>
        <c:when test="${not empty newsList}">
            <div class="row mb-2">
                <c:forEach var="news" items="${newsList}" begin="0" end="1">
                    <div class="col-md-6">
                        <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                            <div class="col p-4 d-flex flex-column position-static">
                                <strong class="d-inline-block mb-2 text-primary-emphasis">${news.category.category}</strong>
                                <h3 class="mb-0">${news.title}</h3>
                                <div class="mb-1 text-body-secondary">${news.publishDate}</div>
                                <p class="card-text mb-auto">${news.summary}</p>
                                <a href="news?id=${news.id}" class="icon-link gap-1 icon-link-hover stretched-link">
                                    Continue lendo...
                                    <svg class="bi">
                                        <use xlink:href="#chevron-right"/>
                                    </svg>
                                </a>
                            </div>
                            <div class="col-auto d-none d-lg-block bk-news">
                                <img src="${news.images[0]}" alt="${news.title}" class="bd-placeholder-img" width="200"
                                     height="250"
                                     style="object-fit: cover;"/>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <h3 class="my-5 mx-5 px-3 py-3 border rounded">
                Hmm, parece que não temos nada por enquanto, comece se cadastrando
                <a href="signup.jsp">aqui</a>
                e criando uma notícia!
            </h3>
        </c:otherwise>
    </c:choose>


    <div class="row">

        <div class="col-md-8">
            <h3 class="pb-4 mb-4 fst-italic border-bottom">
                Galeria de imagens
            </h3>

            <article class="blog-post">

                <c:if test="${not empty newsList}">
                    <c:forEach var="news" items="${newsList}">
                        <h4 class="blog-post-title">${news.title}</h4>
                        <p class="blog-post-meta">${news.publishDate} por <a href="#">${news.author}</a></p>

                        <div class="row">

                            <c:forEach var="image" items="${news.images}">
                                <div class="col-md-6">
                                    <img src="${image}" alt="${news.title}" class="bd-placeholder-img"
                                         width="100%" height="100%"
                                         style="object-fit: cover;"/>
                                </div>
                            </c:forEach>
                        </div>
                        <hr>
                    </c:forEach>
                </c:if>
            </article>
        </div>

        <div class="col-md-4">
            <div class="position-sticky" style="top: 2rem;">

                <div class="p-4 mb-3 bg-body-tertiary rounded">
                    <h4 class="fst-italic">Sobre</h4>
                    <p class="mb-0">Abaixo voce encontrará, se disponível, as noticias recentes, além das categorias
                        presentes no site.</p>
                </div>

                <%--Posts recentes (max de 3)--%>
                <c:if test="${not empty newsList}">
                    <div class="p-4">
                        <h4 class="fst-italic">Noticias recentes</h4>
                        <ul class="list-unstyled">
                            <c:forEach var="news" items="${newsList}">
                                <li>
                                    <a class="d-flex flex-column flex-lg-row gap-3 align-items-start align-items-lg-center py-3 link-body-emphasis text-decoration-none border-top"
                                       href="news?id=${news.id}">
                                        <img src="${news.images[0]}" alt="${news.title}" class="bd-placeholder-img"
                                             width="100" height="96"
                                             style="object-fit: cover;"/>
                                        <div class="col-lg-8">
                                            <h6 class="mb-0">${news.title}</h6>
                                            <small class="text-body-secondary">${news.publishDate}</small>
                                        </div>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>


                <%--Listando todas as categorias--%>
                <div class="p-4">
                    <h4 class="fst-italic
    ">Categorias</h4>
                    <ol class="list-unstyled mb-0">
                        <c:if test="${not empty categoryList}">
                            <c:forEach var="category" items="${categoryList}">
                                <li><a href="searchByCategory?id=${category.id}">${category.category}</a></li>
                            </c:forEach>
                        </c:if>

                        <c:if test="${empty categoryList}">
                            <li>Nenhuma categoria cadastrada</li>
                        </c:if>
                    </ol>
                </div>

                <div class="p-4">
                    <h4 class="fst-italic">Integrantes</h4>
                    <ol class="list-unstyled">
                        <li><a href="https://github.com/edenilsonjunior">Edenilson Garcia</a></li>
                        <li><a href="https://github.com/roberttiss">Gabriel Roberts</a></li>
                        <li><a href="https://github.com/RICKBISPO">Henrique Bispo</a></li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
</main>

<script src="${pageContext.request.contextPath}/components/navbar.js" type="module"></script>

<%@ include file="includes/footer/footer.jsp" %>
<%@ include file="includes/footer/footer-default.jsp" %>

