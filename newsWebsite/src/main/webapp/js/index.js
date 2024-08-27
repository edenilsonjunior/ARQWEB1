document.addEventListener('DOMContentLoaded', function () {

    fetch('api/index-load-data')
        .then(response => response.json())
        .then(data => {
            console.log(data);

            loadNavbar(data.isLogged);
            loadLatestNews(data.newsList);
            loadImageGallery(data.newsList);
            loadRecentPosts(data.newsList);
            loadCategories(data.newsList);

        })
        .catch(error => console.error(error));
});


function loadNavbar(isLogged){

    const navbarContainer = document.getElementById('nav-bar');
    navbarContainer.innerHTML = '';

    if (isLogged){
        navbarContainer.innerHTML = `
        <div class="container">
          <header class="border-bottom lh-1 py-2">
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
              <div class="container-fluid">
                <a class="navbar-brand" href="#"><img alt="logo" src="/assets/images/IFNews.png" width="100px"></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="/index.html">Página Inicial</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/retrieveCategory">Categorias</a>
                      </li>
                    <li class="nav-item">
                      <a class="nav-link" href="/createNewsArticle.jsp">Cadastrar Noticia</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="/about.jsp">Sobre</a>
                    </li>
                  </ul>
                  <form class="d-flex" role="search" action="/search" method="get">
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
                          <li><a class="dropdown-item" href="/profile.jsp">Perfil</a></li>
                          <li><hr class="dropdown-divider"></li>
                          <li><a class="dropdown-item" href="/logoutUser">Sair</a></li>
                    </li>
                  </ul>
                </div>
              </div>
            </nav>
          </header>
        </div>
    `;
    }
    else {
        navbarContainer.innerHTML = `
    <div class="container">
      <header class="border-bottom lh-1 py-2">
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
          <div class="container-fluid">
            <a class="navbar-brand" href="#"><img alt="logo" src="assets/images/IFNews.png" width="100px"></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                  <a class="nav-link active" aria-current="page" href="/index.jsp">Página Inicial</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/retrieveCategory">Categorias</a>
                  </li>
                <li class="nav-item">
                  <a class="nav-link" href="/about.jsp">Sobre</a>
                </li>
              </ul>
              <form class="d-flex" role="search" action="/search" method="get">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="keyword">
                <button class="btn btn-light" type="submit">
                  <i class="fas fa-search"></i>
                </button>
              </form>
              <ul class="navbar-nav me-right mb-2 mb-lg-0 mx-1 mt-2">
                <li class="nav-item dropdown">
                      <a href="/signup.jsp" class="btn btn-outline-secondary" role="button">SignUp</a>
                      <a href="/login.jsp" class="btn btn-secondary" role="button">Login</a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </header>
    </div>`;
    }
}


function loadLatestNews(newsList) {
    const latestNewsContainer = document.getElementById('latest-news');
    latestNewsContainer.innerHTML = '';

    if (newsList.length === 0) {
        latestNewsContainer.innerHTML = `
            <h3 class="my-5 mx-5 px-3 py-3 border rounded">
                Hmm, parece que não temos nada por enquanto, comece se cadastrando
                <a href="signup.jsp">aqui</a>
                e criando uma notícia!
            </h3>
        `;
    } else {
        let row = '<div class="row mb-2">';

        for (let i = 0; i < Math.min(2, newsList.length); i++) {
            let news = newsList[i];

            row += `
                <div class="col-md-6">
                    <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                        <div class="col p-4 d-flex flex-column position-static">
                            <strong class="d-inline-block mb-2 text-primary-emphasis">${news.category.category}</strong>
                            <h3 class="mb-0">${news.title}</h3>
                            <div class="mb-1 text-body-secondary">${news.publishDate}</div>
                            <p class="card-text mb-auto">${news.summary}</p>
                            <a class="icon-link gap-1 icon-link-hover stretched-link" href="news?id=${news.id}">Continue lendo...</a>
                        </div>
                        <div class="col-auto d-none d-lg-block bk-news">
                            <img src="${news.images[0]}" alt="${news.title}" class="bd-placeholder-img" width="200" height="250" style="object-fit: cover;">
                        </div>
                    </div>
                </div>
            `;
        }

        row += '</div>';
        latestNewsContainer.innerHTML = row;
    }
}

function loadImageGallery(newsList) {
    const galleryContainer = document.getElementById('image-gallery');

    galleryContainer.innerHTML = `
        <h3 class="pb-4 mb-4 fst-italic border-bottom">Galeria de imagens</h3>
            <article class="blog-post">
        `;

    if (newsList.length === 0) {
        galleryContainer.innerHTML = '<p>Não há notícias disponíveis no momento.</p>';
    } else {
        let content = `
            <h3 class="pb-4 mb-4 fst-italic border-bottom">Galeria de imagens</h3>
            <article class="blog-post">
        `;

        newsList.forEach(news => {
            content += `
                <h4 class="blog-post-title">${news.title}</h4>
                <p class="blog-post-meta">${news.publishDate} por <a href="#">${news.author}</a></p>
                <div class="row">
            `;

            news.images.forEach(image => {
                content += `
                    <div class="col-md-6">
                        <img src="${image}" alt="${news.title}" class="bd-placeholder-img"
                             width="100%" height="100%"
                             style="object-fit: cover;"/>
                    </div>
                `;
            });

            content += '</div><hr>';
        });

        content += '</article>';
        galleryContainer.innerHTML = content;
    }
}


function loadRecentPosts(newsList) {
    const recentPostsContainer = document.getElementById('recent-posts');

    if (newsList.length > 0) {

        let content = `
            <div class="p-4">
                <h4 class="fst-italic">Noticias recentes</h4>
                <ul class="list-unstyled">
        `;

        newsList.forEach(news => {
            content += `
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
            `;
        });

        content += '</ul></div>';
        recentPostsContainer.innerHTML = content;
    }
}

function loadCategories(newsList) {
    const categoriesContainer = document.getElementById('categories');

    let content= `
            <div class="p-4">
                <h4 class="fst-italic">Categorias</h4>
                <ol class="list-unstyled mb-0">
        `;

    if (newsList.length > 0) {
        newsList.forEach(news => {
            content += `
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
            `;
        });

    } else {
        content += '<li>Nenhuma categoria cadastrada</li>';
    }

    content += '</ol></div>';
    categoriesContainer.innerHTML = content;
}