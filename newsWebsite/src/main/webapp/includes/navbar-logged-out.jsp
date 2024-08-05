<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">

      <header class="border-bottom lh-1 py-2">
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
          <div class="container-fluid">
            <a class="navbar-brand" href="#">IFNews</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                  <a class="nav-link active" aria-current="page" href="index.jsp">Página Inicial</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Categorias</a>
                  </li>
                <li class="nav-item">
                  <a class="nav-link" href="about.jsp">Sobre</a>
                </li>
              </ul>
              <form class="d-flex mt-2" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-light" type="submit">
                  <i class="fas fa-search"></i>
                </button>
              </form> 
              <ul class="navbar-nav me-right mb-2 mb-lg-0 mx-1 mt-2">
                <li class="nav-item dropdown">
                      <a href="signup.jsp" class="btn btn-outline-secondary" role="button">SignUp</a>
                      <a href="login.jsp" class="btn btn-secondary" role="button">Login</a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </header>
      
    </div>