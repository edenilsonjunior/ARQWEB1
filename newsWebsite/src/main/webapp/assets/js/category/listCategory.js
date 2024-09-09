import {checkLoginStatus, submitGet, contextPath} from "../components/global.js";

const createCategoryContainer = document.getElementById('create-category');
const newsByCategoryContainer = document.getElementById('news-by-category');
const messageContainer = document.getElementById('message');

document.addEventListener('DOMContentLoaded', async () => {

    let userStatus = await checkLoginStatus();

    loadCreateCategory(userStatus.isLogged);
    await loadNewsByCategory(userStatus.isLogged);
});

const loadCreateCategory = (isLogged) => {

    if (isLogged) {

        createCategoryContainer.innerHTML = `
            <div class="d-flex justify-content-start my-3">
            <a href="${contextPath}/views/category/createCategory.html" class="btn btn-primary">Criar categoria</a>
            </div>
        `;
    }
}

const loadNewsByCategory = async (isLogged) => {

    try {
        let data = await submitGet('/news-by-category');
        displayNews(data, isLogged);

    } catch (e) {
        console.error(e);
    }

}

const displayNews = (data, isLogged) => {

    newsByCategoryContainer.innerHTML = '';

    for (const [category, articles] of Object.entries(data.newsByCategory)) {

        let categoryHTML = `
            <div class="d-flex justify-content-between align-items-center border border-secondary rounded px-2 py-2">
                <h2 class="me-4">${category}</h2>
                ${isLogged ? `
                <div>    
                    <a data-category="${category}" class="btn btn-danger delete-category">Excluir</a>
                    <a href="${contextPath}/views/category/updateCategory.html?category=${category}" class="btn btn-warning">Editar</a>
                </div>` : ''}
            </div>
        `;

        newsByCategoryContainer.innerHTML += categoryHTML;

        if (articles.length === 0) {
            newsByCategoryContainer.innerHTML += `
                <li class="text-danger my-3">Nenhuma notícia cadastrada</li>
            `;
        } else {
            let counter = 0;
            let rowHTML = '';

            articles.forEach((newsArticle, index) => {

                if (counter % 4 === 0) {
                    if (counter > 0) rowHTML += `</div>`;
                    rowHTML += `<div class="row my-3">`;
                }

                rowHTML += `
                    <div class="col-sm-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${newsArticle.title}</h5>
                                <p class="card-text">Autor: ${newsArticle.author}</p>
                                <p class="card-text">Publicado em: ${newsArticle.publishDate}</p>
                                <a href="${contextPath}/news?id=${newsArticle.id}" class="btn btn-primary">Leia mais</a>
                            </div>
                        </div>
                    </div>
                `;

                counter++;
            });

            rowHTML += `</div>`;
            newsByCategoryContainer.innerHTML += rowHTML;
        }
    }

    if (isLogged) {
        const divs = document.getElementsByClassName('delete-category');

        Array.from(divs).forEach(div => {
            div.addEventListener('click', deleteCategory);
        });
    }

};

const deleteCategory = async (event) => {

    debugger;
    const category = event.target.getAttribute('data-category');
    const response = await fetch(`${contextPath}/delete-category?category=${category}`, {
        method: 'GET'
    });

    const content = await response.json();

    let message = '';

    if (content.error != null)
        message = `<div class="alert alert-danger" role="alert">${content.error}</div>`;
    else
        message = `<div class="alert alert-success" role="alert">Categoria excluida com sucesso! Atualize a pagina para ver as mudanças</div>`;

    messageContainer.innerHTML = message;
}

