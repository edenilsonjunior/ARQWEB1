import {loadData, contextPath, checkLoginStatus, submitPost} from '../components/global.js';

document.addEventListener("DOMContentLoaded", async function () {

    const params = new URLSearchParams(window.location.search);

    const id = params.get('id');
    const data = await newsById(id);

    const news = data.news;
    const listCommentary = data.listCommentary;
    const images = data.images;


    loadCarousel(images);
    await loadEditNews(news);
    loadNews(news);
    await loadComments(listCommentary, news);
});

const newsById = async (newsId) => {
    try {
        const response = await fetch(`${contextPath}/news?id=${newsId}`);
        return await response.json();
    } catch (error) {
    console.error(error);
    }
}

const loadCarousel = (images) =>{

    const carouselInner = document.getElementById('carouselInner');

    let content = '';
    images.forEach((image, index) => {

        content +=`
            <div class="carousel-item ${index === 0 ? 'active' : ''}">
                <img class="d-block w-100 img-news rounded" src="${image}" alt="Image ${index + 1}"
                style="object-fit: contain;">
            </div>
        `;
    });

    carouselInner.innerHTML = content;
}


const loadEditNews = async (news) => {

    const isLogged = await checkLoginStatus();
    const editNewsContainer = document.getElementById('edit-news');

    if(isLogged) {

        editNewsContainer.innerHTML = `
            <div class="d-flex justify-content-between align-items-center mt-2 mb-2">
                <div>
                    <a href="${contextPath}/deleteNewsArticle?id=${news.id}" class="btn btn-danger">Excluir </a>
                    <a href="${contextPath}/updateNewsArticle?id=${news.id}" class="btn btn-warning">Editar</a>
                </div>
            </div>
        `;
    }
}

const loadNews = (news) => {

    const editNewsContainer = document.getElementById('news-details');

    editNewsContainer.innerHTML = `
            
        <article class="blog-post p-4 p-md-5 mb-4 rounded bg-light shadow-sm">
            <div class="blog-post-header mb-3">
                <h2 class="display-4 link-body-emphasis mb-1">${news.title}</h2>
                <p class="blog-post-meta text-muted" style="font-size: 1.2em;">${news.category.category}</p>
                <p class="blog-post-meta text-muted">${news.publishDate} por <a
                class="text-decoration-none">${news.author}</a>
                </p>
            </div>
            <div class="blog-post-body">
                <p class="lead my-3">${news.text}</p>
                <p class="lead my-3 text-muted">Fonte: ${news.source}</p>
            </div>
        </article>
    `;
}

const loadComments = async (comments, news) => {

    const isLogged = await checkLoginStatus();
    const commentsContainer = document.getElementById('comments');

    let content = `<h3 class="display-6 link-body-emphasis mb-2 mt-4">Comentários</h3>`;

    if(isLogged){
        content += `
            <form method="post" action="${contextPath}/news">
                <input type="hidden" name="newsId" value="${news.id}">
                <div class="form-comment">
                    <textarea class="form-control" name="comment" minlength="2" required placeholder="Comente aqui"
                              id="floatingTextarea2" style="height: 100px"></textarea>
                    <button type="submit" class="btn btn-outline-primary">Enviar</button>
                </div>
            </form>
        `;
    }else{
        content +=`
            <div class="alert alert-warning" role="alert">
                Você precisa estar logado para comentar.
            </div>
        `;
    }

    if(comments.length === 0){
        content += `<p>Sem comentários.</p>`;
    }else{

        comments.forEach((commentary) => {
            content += `
                <div class="comment">
                    <p class="comment-author"><strong>${commentary.user.username}</strong></p>
                    <p class="comment-text">${commentary.text}</p>
                </div>
            `;
        });
    }

    commentsContainer.innerHTML = content;
}
