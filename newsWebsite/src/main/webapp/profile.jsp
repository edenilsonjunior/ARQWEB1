<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includes/header-default.jsp" %>
<%@ include file="includes/navbar-logged-in.jsp" %>

	<main class="container">

        <h1 class="my-5 text-center">Luiz Henrique Nunes</h1>

        <div class="container form-container">
            <form> 
                <div class="mb-3">
                  <label for="email" class="form-label">Email</label>
                  <input type="email" class="form-control" id="email" aria-describedby="emailHelp" value="luiz.nunes@gmail.com">
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">Senha</label>
                  <input type="password" class="form-control" id="confirmPassword">
                </div class="mb-3">
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label">Confirmar Senha</label>
                    <input type="password" class="form-control" id="confirmPassword">
                  </div class="mb-3">
                <button type="submit" class="btn btn-secondary px-4">Editar</button>
            </form>
        </div>
            
    </main>

<%@ include file="includes/footer.jsp" %>
<%@ include file="includes/footer-default.jsp" %>


