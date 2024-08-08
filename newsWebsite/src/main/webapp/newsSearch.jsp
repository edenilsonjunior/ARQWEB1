<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includes/header-default.jsp" %>
<%@ include file="includes/navbar-logged-in.jsp" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--Codigo para pegar dados das noticias--%>
<%--<main class="container">--%>
<%--    <div class="list-group">--%>
<%--        <c:if test="${not empty listNews}">--%>
<%--            <c:set var="listItemClass" value="list-group-item list-group-item-action active" style="display: block; width: 50%; margin: 0 auto;"/>--%>
<%--                <c:forEach var="news" items="${listNews}">--%>
<%--                    <a href="#" class="${listItemClass}" aria-current="true">--%>
<%--                        <div class="d-flex w-100 justify-content-between">--%>
<%--                            <h5 class="mb-1">${news.title}</h5>--%>
<%--                            <p class="mb-1">${news.text}</p>--%>
<%--                            <small>${news.author}</small>--%>
<%--                        </div>--%>
<%--                    </a>--%>
<%--                </c:forEach>--%>
<%--        </c:if>--%>
<%--    </div>--%>
<%--</main>--%>

<h1 align="center">Noticias</h1>

<main class="container">
    <div class="bk-book p-4 p-md-5 mb-4 rounded text-body-emphasis bg-body-secondary">
        <c:if test="${not empty listNews}">
            <c:forEach var="news" items="${listNews}">
                <div class="col-lg-6 px-0 ">
                    <h1 class="display-4 fst-italic">${news.title}</h1>
                    <p class="lead my-3">${news.text}</p>
                    <p class="lead mb-0"><a href="news.jsp" class="fw-bold">Continue reading...</a></p>
                    <small>${news.author}</small>
                </div>
            </c:forEach>
        </c:if>
    </div>
</main>

<%@ include file="includes/footer.jsp" %>
<%@ include file="includes/footer-default.jsp" %>