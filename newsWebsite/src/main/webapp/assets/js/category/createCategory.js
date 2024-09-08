import {checkUserPermission, submitPost} from '../components/global.js';

const formId = 'createCategoryForm';
const servletUrl = '/create-category';
const formContainer = document.getElementById(formId);

document.addEventListener('DOMContentLoaded', async () => {
    await checkUserPermission();
});

formContainer.addEventListener('submit', async (event) => {
    await submitPost(event, servletUrl, formId);
});
