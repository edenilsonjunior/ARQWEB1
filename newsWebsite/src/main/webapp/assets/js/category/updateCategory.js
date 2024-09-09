import {checkUserPermission, submitGet, submitPost} from '../components/global.js';

const formId = 'updateCategoryForm';
const servletUrl = '/update-category';

document.addEventListener('DOMContentLoaded', async () => {
    await checkUserPermission();

    await loadCategoryField();
});

const loadCategoryField = async () => {

    const params = new URLSearchParams(window.location.search);
    const category = params.get('category');

    document.getElementById('id').value = category;
    document.getElementById('categoryName').value = category;

    document.getElementById('updateCategoryForm').addEventListener('submit', async (event) => {

        await submitPost(event, servletUrl, formId);
    });
}
