<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includes/header-default.jsp" %>
<%@ include file="includes/navbar-logged-in.jsp" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--Codigo para pegar dados das noticias--%>
<%--<main class="container">--%>
<%--    <div class="list-group">--%>
<%--        <c:if test="${not empty listNews}">--%>
<%--            <c:set var="listItemClass" value="list-group-item list-group-item-action active" />--%>
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

    <div class="list-group">
      <a href="#" class="list-group-item list-group-item-action active" aria-current="true">
        <div class="d-flex w-100 justify-content-between">
          <h5 class="mb-1">List group item heading</h5>
          <small>3 days ago</small>
        </div>
        <p class="mb-1">Some placeholder content in a paragraph.</p>
        <small>And some small print.</small>
      </a>
      <a href="#" class="list-group-item list-group-item-action">
        <div class="d-flex w-100 justify-content-between">
          <h5 class="mb-1">List group item heading</h5>
          <small class="text-muted">3 days ago</small>
        </div>
        <p class="mb-1">Some placeholder content in a paragraph.</p>
        <small class="text-muted">And some muted small print.</small>
      </a>
      <a href="#" class="list-group-item list-group-item-action">
        <div class="d-flex w-100 justify-content-between">
          <h5 class="mb-1">List group item heading</h5>
          <small class="text-muted">3 days ago</small>
        </div>
        <p class="mb-1">Some placeholder content in a paragraph.</p>
        <small class="text-muted">And some muted small print.</small>
      </a>
    </div>


<%@ include file="includes/footer.jsp" %>
<%@ include file="includes/footer-default.jsp" %>