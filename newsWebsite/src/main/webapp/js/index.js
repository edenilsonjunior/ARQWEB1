import {loadData} from '../components/global.js';
import {contextPath} from '../components/global.js';

const welcomeContainer = document.getElementById('welcome');
const latestNewsContainer = document.getElementById('latest-news');
const galleryContainer = document.getElementById('image-gallery');
const categoriesContainer = document.getElementById('categories');
const recentPostsContainer = document.getElementById('recent-posts');


document.addEventListener('DOMContentLoaded', async () => {

    let data = await loadData();
    welcome();
    latestNews(data.newsList);
    imageGallery(data.newsList);
    recentPosts(data.newsList);
    categories(data.newsList);
});

const welcome = () => {

    welcomeContainer.innerHTML = `
        <div class="p-4 p-md-5 mb-4 rounded text-body-emphasis bg-body-secondary bk-news">
            <div class="col-lg-6 px-0 bg-dark text-light rounded px-2 py-2">
                <h1 class="display-4 fst-italic">Bem-vindo ao IFNews!</h1>
                <p class="lead my-3">Este sistema foi desenvolvido para oferecer uma plataforma completa e intuitiva para a gestão e visualização de notícias.</p>
                <p class="lead mb-0"><a href="${contextPath}/about.jsp" class="fw-bold">Continue lendo...</a></p>
            </div>
        </div> 
    `;
}


const latestNews = (newsList) => {

    if (newsList.length === 0) {
        latestNewsContainer.innerHTML = `
            <h3 class="my-5 mx-5 px-3 py-3 border rounded"> 
                Hmm, parece que não temos nada por enquanto, comece se cadastrando <a href="${contextPath}/signup.jsp">aqui</a> e criando uma notícia!
            </h3>
        `;
        return;
    }

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
                            <a class="icon-link gap-1 icon-link-hover stretched-link" href="${contextPath}/news?id=${news.id}">Continue lendo...</a>
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

const imageGallery = (newsList) => {

    let content = `
            <h3 class="pb-4 mb-4 fst-italic border-bottom">Galeria de imagens</h3>
                <article class="blog-post">
        `;

    if (newsList.length === 0) {
        content += '<p>Não há notícias disponíveis no momento.</p> </article>';
        galleryContainer.innerHTML = content;
        return;
    }

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

const recentPosts = (newsList) => {

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
                       href="${contextPath}/news?id=${news.id}">
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

const categories = (newsList) => {

    let content = `
            <div class="p-4">
                <h4 class="fst-italic">Categorias</h4>
                <ol class="list-unstyled mb-0">
        `;

    if (newsList.length === 0) {
        content += '<li>Nenhuma categoria cadastrada</li> </ol></div>';
        categoriesContainer.innerHTML = content;
        return;
    }

    newsList.forEach(news => {
        content += `
            <li>
                <a class="d-flex flex-column flex-lg-row gap-3 align-items-start align-items-lg-center py-3 link-body-emphasis text-decoration-none border-top"
                   href="${contextPath}/news?id=${news.id}">
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

    content += '</ol></div>';
    categoriesContainer.innerHTML = content;
}
