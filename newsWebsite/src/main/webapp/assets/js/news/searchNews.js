import {contextPath} from '../components/global.js';

document.addEventListener("DOMContentLoaded", async function () {
    const params = new URLSearchParams(window.location.search);
    const keyword = params.get('keyword');

    if (keyword) {
        const response = await fetchNewsByKeyword(encodeURIComponent(keyword));
        await loadSearchNews(response.newsList);
    } else {
        displayNoResults('Por favor, insira uma palavra-chave para realizar a busca.');
    }
});

const fetchNewsByKeyword = async (keyword) => {
    try {
        const response = await fetch(`${contextPath}/search?keyword=${keyword}`);
        return await response.json();
    } catch (error) {
        console.error('Error fetching news:', error);
        return [];
    }
}

const loadSearchNews = async (newsList) => {
    const newsContainer = document.getElementById('news');

    if (newsList.length === 0) {
        newsContainer.innerHTML = `
            <div class="alert alert-warning" role="alert">
                Nenhuma notícia encontrada para a busca realizada.
            </div>
        `;
        return;
    }


    let content = '';
    newsList.forEach((news) => {
        content += `
            <div class="p-4 p-md-5 mb-4 rounded border text-body-emphasis bg-body-tertiary">
                <div class="col-lg-6 px-0">
                    <h1 class="display-4 fst-italic">${news.title}</h1>
                    <p class="lead my-3">${news.summary}</p>
                    <p class="lead my-3">Autor: ${news.author}</p>
                    <p class="lead mb-0"><a href="${contextPath}views/news/news.html?id=${news.id}" class="fw-bold">Continue lendo...</a></p>
                </div>
            </div>
        `;
    });
    newsContainer.innerHTML = content;
}

const displayNoResults = (message) => {
    const newsContainer = document.getElementById('news');
    newsContainer.innerHTML = `
        <div class="alert alert-warning" role="alert">
            ${message}
        </div>
    `;
}