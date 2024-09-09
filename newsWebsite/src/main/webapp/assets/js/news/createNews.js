import {checkUserPermission, contextPath, loadData, submitPost} from '../components/global.js';

const formId = 'createNewsForm';
const servletUrl = '/create-news';
const formContainer = document.getElementById(formId);

document.addEventListener('DOMContentLoaded', async () => {
    await checkUserPermission();

    await loadCategories();

});

const loadCategories = async ()=> {

    const data = await loadData();
    const categories = data ? data.categoryList : [];

    if(categories.length === 0){
        window.location.href = `${contextPath}/index.html`;
        return;
    }

    const categoryOptions = categories.map(category =>
        `<option value="${category.id}">${category.category}</option>`
    ).join('');


    document.getElementById('category').innerHTML = categoryOptions;
}


formContainer.addEventListener('submit', async (event) => {
    await submitPost(event, servletUrl, formId);
});
