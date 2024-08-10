<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<c:import url="../includes/header-default.jsp"/>
<c:import url="../includes/navbar-logged-in.jsp"/>

	<main class="container">

    <h1>Lista de Categorias</h1>

    <c:if test="${not empty msg }">
        <div class="alert alert-danger" role="alert">${msg}</div>
    </c:if>

	<table class="table table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Nome</th>
              <th scope="col">Descrição</th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
        <c:if test="${not empty categories}">
        <c:forEach var="category" items="${categories}">
            <tr>
              <th scope="row">${category.id}</th>
              <td>${category.category}</td>
              <td><a href="deleteCategory?id=${category.id}"
                    class="btn btn-danger"> Excluir </a></td>
              <td><a href="updateCategory?id=${category.id}"
            class="btn btn-warning"> Editar </a></td>
            </tr>
        </c:forEach>
    </c:if>
  </tbody>
</table>

    </main>

<c:import url="../includes/footer.jsp"/>
<c:import url="../includes/footer-default.jsp"/>
