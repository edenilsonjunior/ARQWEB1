import {contextPath} from '../components/global.js';

document.addEventListener("DOMContentLoaded", async function () {
    const params = new URLSearchParams(window.location.search);
    const keyword = params.get('keyword');
    if (keyword) {
        const newsList = await fetchNewsByKeyword(keyword);
        await loadSearchNews(newsList);
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

const loadSearchNews = async (newsList) =>{

    const newsContainer = document.getElementById('newsSearched');
    let content = `<div class="col-lg-6 px-0">`;

    if(newsList.length === 0){
        content += `
            <div class="p-4 p-md-5 mb-4 rounded border text-body-emphasis bg-body-tertiary">
                <div class="alert alert-warning" role="alert">
                    Nenhuma notícia encontrada para a busca realizada.
                </div>
            </div>
        `;
    } else {
        content += `
            <div class="col-lg-6 px-0">
                <h1 class="display-4 fst-italic">${news.title}</h1>
                <p class="lead my-3">${news.summary}</p>
                <p class="lead my-3">Autor: ${news.author}</p>
                <p class="lead mb-0"><a href="views/news/news?id=${news.id}" class="fw-bold">Continue lendo...</a></p>
            </div>
        `;
    }
    newsContainer.innerHTML = content;
}