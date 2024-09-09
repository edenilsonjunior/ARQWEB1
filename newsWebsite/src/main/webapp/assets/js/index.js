import {loadData, contextPath} from './components/global.js';

const latestNewsContainer = document.getElementById('latest-news');
const galleryContainer = document.getElementById('image-gallery-content');
const categoriesContainer = document.getElementById('categories');
const recentPostsContainer = document.getElementById('recent-posts');

document.addEventListener('DOMContentLoaded', async () => {

    let data = await loadData();
    welcome();
    latestNews(data.newsList);
    imageGallery(data.newsList);
    recentPosts(data.newsList);
    categories(data.categoryList);
});

const welcome = () => {
    document.getElementById('welcome-link').href = `${contextPath}/about.html`;
}


const latestNews = (newsList) => {

    if (newsList.length === 0) {
        latestNewsContainer.innerHTML = `
            <h3 class="my-5 mx-5 px-3 py-3 border rounded"> 
                Hmm, parece que não temos nada por enquanto, comece se cadastrando <a href="${contextPath}/signup">aqui</a> e criando uma notícia!
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
                            <a class="icon-link gap-1 icon-link-hover stretched-link" href="${contextPath}/views/news/news.html?id=${news.id}">Continue lendo...</a>
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

    if (newsList.length === 0) {
        galleryContainer.innerHTML = '<p>Não há notícias disponíveis no momento.</p> </article>';
        return;
    }
    
    let content = '';
    newsList.forEach(news => {
        content += `
                <h4 class="blog-post-title">${news.title}</h4>
                <p class="blog-post-meta">${news.publishDate} por <a href="#">${news.author}</a></p>
            `;

        content += `<div class="row">${showImages(news)}</div><hr>`;
    });

    galleryContainer.innerHTML = content;
}


const showImages = (news) => {

    let content = '';

    news.images.forEach(image => {
        content += `
        <div class="col-md-6">
            <img src="${image}" alt="${news.title}" class="bd-placeholder-img" 
                width="100%" height="100%" 
                style="object-fit: cover;"/>
        </div>`;
    });

    return content;
}


const recentPosts = (newsList) => {

    if (newsList.length === 0) 
        return;

    recentPostsContainer.innerHTML =  `
        <div class="p-4">
            <h4 class="fst-italic">Noticias recentes</h4>
            <ul class="list-unstyled">
                ${newsList.forEach(news => {
                    return `
                        <li>
                            <a class="d-flex flex-column flex-lg-row gap-3 align-items-start align-items-lg-center py-3 link-body-emphasis text-decoration-none border-top"
                            href="${contextPath}/views/news/news.html?id=${news.id}">
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
                })}
            </ul>
        </div>`;
}


const categories = (categoryList) => {

    categoriesContainer.innerHTML = `
        <div class="p-4">
            <h4 class="fst-italic">Categorias</h4>
            <ol class="list-unstyled mb-0">
                ${showCategoriesInList(categoryList)}
            </ol>
        </div>`;
}


const showCategoriesInList = (categoryList)=>{

    if(categoryList.length === 0)
        return '<li>Nenhuma categoria cadastrada</li>'
    else{

        return categoryList.map(category => {
            return `
                <li>
                    <a href="${contextPath}/searchByCategory?id=${category.id}">${category.category}</a>
                </li>`;
        }).join('');
    }
}
