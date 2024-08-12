<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/navbar/header-default.jsp" %>

	<main class="container">

		<c:if test="${not empty error}">
        	<div class="alert alert-danger" role="alert">${error}</div>
    	</c:if>

        <div class="container-login pt-1 pb-5 my-5">
            <h1 class="my-5 text-center">Login</h1>

            <div class="container form-container">
                <form action="loginUser" method="post">
                    <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email">
                    </div>
                    <div class="mb-3">
                    <label for="password" class="form-label">Senha</label>
                    <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <button type="submit" class="btn btn-secondary px-4">Enviar</button>
                </form>
            </div>
        </div>
            
    </main>

<%@ include file="includes/footer/footer-default.jsp" %>


