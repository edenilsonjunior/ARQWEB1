<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">

      <header class="border-bottom lh-1 py-2">
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
          <div class="container-fluid">
            <a class="navbar-brand" href="#"><img alt="logo" src="${pageContext.request.contextPath}/assets/images/IFNews.png" width="100px"></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                  <a class="nav-link active" aria-current="page" href="index.jsp">Página Inicial</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="retrieveCategory">Categorias</a>
                  </li>
                <li class="nav-item">
                  <a class="nav-link" href="${pageContext.request.contextPath}/createNewsArticle.jsp">Cadastrar Noticia</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="about.jsp">Sobre</a>
                </li>
              </ul>
              <form class="d-flex" role="search" action="${pageContext.request.contextPath}/search" method="get">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="keyword">
                <button class="btn btn-light" type="submit">
                  <i class="fas fa-search"></i>
                </button>
              </form> 
              <ul class="navbar-nav me-right mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      <img src="https://cdn-icons-png.flaticon.com/512/1144/1144760.png" alt="user" width="30px">
                    </a>
                    <ul class="dropdown-menu">
                      <li><a class="dropdown-item" href="profile.jsp">Perfil</a></li>
                      <li><hr class="dropdown-divider"></li>
                      <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logoutUser">Sair</a></li>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </header>
      
    </div>
