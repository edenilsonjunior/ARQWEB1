import {checkLoginStatus, submitGet, contextPath} from "../components/global.js";

const editCategoryContainer = document.getElementById('edit-category');
const newsByCategoryContainer = document.getElementById('news-by-category');

document.addEventListener('DOMContentLoaded', async () => {

    let userStatus = await checkLoginStatus();

    loadEditCategory(userStatus.isLogged);
    await loadNewsByCategory();
});

const loadEditCategory = (isLogged) => {

    if(isLogged){

        editCategoryContainer.innerHTML = `
            <div class="d-flex justify-content-start my-3">
            <a href="${contextPath}/views/category/createCategory.html" class="btn btn-primary">Criar categoria</a>
            </div>
        `;
    }
}

const loadNewsByCategory = async () => {

    try {
        let data = await submitGet('/news-by-category');
        displayNews(data);

    }catch (e) {
        console.error(e);
    }

}

const displayNews = (data) => {

    newsByCategoryContainer.innerHTML = '';

    Object.entries(data.map).forEach(([categoryKey, newsList]) => {

        let html = `
            <div class="d-flex justify-content-between align-items-center border border-secondary rounded px-2 py-2">
                <h2 class="me-4">${categoryKey}</h2>
                <div>
                    <a href="deleteCategory?id=${newsList[0]?.category?.id}" class="btn btn-danger">Excluir</a>
                    <a href="updateCategory?id=${newsList[0]?.category?.id}" class="btn btn-warning">Editar</a>
                </div>
            </div>
        `;

        if (newsList.length === 0) {
            html += `<li class="text-danger my-3">Nenhuma notícia cadastrada</li>`;
        } else {
            let counter = 0;
            html += '<div class="row my-3">';
            newsList.forEach(newsArticle => {
                html += `
                    <div class="col-sm-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${newsArticle.title}</h5>
                                <p class="card-text">Autor: ${newsArticle.author}</p>
                                <p class="card-text">Publicado em: ${newsArticle.publishDate}</p>
                                <a href="news?id=${newsArticle.id}" class="btn btn-primary">Leia mais</a>
                            </div>
                        </div>
                    </div>
                `;
                counter++;
                if (counter % 4 === 0 || counter === newsList.length) {
                    html += '</div><div class="row my-3">';
                }
            });
            html += '</div>';
        }

        newsByCategoryContainer.innerHTML = html;
    });
};

