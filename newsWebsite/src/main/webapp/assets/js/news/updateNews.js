import {checkUserPermission, loadData, submitGet, submitPost} from '../components/global.js';

const formId = 'updateNewsForm';
const servletUrl = '/update-news';
const updateNewsContainer = document.getElementById('update-news');


document.addEventListener('DOMContentLoaded', async () => {

    await checkUserPermission();
    await loadUpdateNewsForm();
});

const loadUpdateNewsForm = async () => {

    const params = new URLSearchParams(window.location.search);
    const newsId = params.get('id');

    debugger;
    const newsData = await submitGet(`/news?id=${newsId}`);
    const data = await loadData();

    const categories = data.categoryList;

    updateNewsContainer.innerHTML = loadForm(newsData, categories);

    document.getElementById(formId).addEventListener('submit', async (event) => {
        await submitPost(event, servletUrl, formId);
    });
}


const loadForm = (newsData, categories) => {

    const newsArticle = newsData.news;

    const categoryOptions = categories.map(category =>
        `<option value="${category.id}" ${category.id === newsArticle.category.id ? 'selected' : ''}>
            ${category.category}
        </option>`
    ).join('');


    return `
        <div class="container form-container">
            <form method="post" id="${formId}">
                <input type="hidden" name="id" value="${newsArticle.id}">
                <div class="mb-3">
                    <label for="title" class="form-label">Título</label>
                    <input type="text" class="form-control" id="title" name="title" value="${newsArticle.title}" required>
                </div>
                <div class="mb-3">
                    <label for="author" class="form-label">Autor</label>
                    <input type="text" class="form-control" id="author" name="author" value="${newsArticle.author}" required>
                </div>
                <div class="mb-3">
                    <label for="publishDate" class="form-label">Data de Publicação</label>
                    <input type="date" class="form-control" id="publishDate" name="publishDate" value="${newsArticle.publishDate}" required>
                </div>
                <div class="mb-3">
                    <label for="source" class="form-label">Fonte</label>
                    <input type="text" class="form-control" id="source" name="source" value="${newsArticle.source}" required>
                </div>
                <div class="mb-3">
                    <label for="summary" class="form-label">Resumo</label>
                    <textarea class="form-control" id="summary" name="summary" rows="3" required>${newsArticle.summary}</textarea>
                </div>
                <div class="mb-3">
                    <label for="text" class="form-label">Texto</label>
                    <textarea class="form-control" id="text" name="text" rows="5" required>${newsArticle.text}</textarea>
                </div>
                <div class="mb-3">
                    <label for="category" class="form-label">Categoria</label>
                    <select class="form-select" id="category" name="category" required>
                        ${categoryOptions}
                    </select>
                </div>
                <div class="mb-3">
                    <label for="image1" class="form-label">Imagem 1</label>
                    <input type="text" class="form-control" id="image1" name="image1" value="${newsArticle.images[0]}">
                </div>
                <div class="mb-3">
                    <label for="image2" class="form-label">Imagem 2</label>
                    <input type="text" class="form-control" id="image2" name="image2" value="${newsArticle.images[1]}">
                </div>
                <button type="submit" class="btn btn-warning px-4">Editar</button>
            </form>
        </div>
    `;
}
