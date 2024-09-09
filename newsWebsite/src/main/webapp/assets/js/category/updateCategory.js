import {checkUserPermission, submitGet, submitPost} from '../components/global.js';

const formId = 'updateCategoryForm';
const servletUrl = '/update-category';
const updateCategoryContainer = document.getElementById('update-category');

document.addEventListener('DOMContentLoaded', async () => {
    await checkUserPermission();

    await loadCategoryField();
});

const loadCategoryField = async () => {

    const params = new URLSearchParams(window.location.search);
    const category = params.get('category');

    updateCategoryContainer.innerHTML = `
    
    <div class="container-login pt-1 pb-5 my-5">
        <h1 class="my-5 text-center">Atualizar Categoria</h1>
        
        <div class="container form-container">
            <form method="post" id="updateCategoryForm">
                <input type="hidden" name="id" value="${category}">
                <div class="mb-3">
                    <label for="categoryName" class="form-label">Categoria: </label>
                    <input class="form-control" id="categoryName" name="categoryName" value="${category}">
                </div>
                <button type="submit" class="btn btn-secondary px-4">Enviar</button>
            </form>
        </div>
    </div>
    `;


    document.getElementById('updateCategoryForm').addEventListener('submit', async (event) => {

        debugger;
        await submitPost(event, servletUrl, formId);
    });

}
