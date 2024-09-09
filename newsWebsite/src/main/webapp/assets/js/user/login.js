import {submitPost} from '../components/global.js';

const formId = 'loginForm';
const servletUrl = '/login';
const container = document.getElementById(formId);

container.addEventListener('submit', async (event)=>{
    await submitPost(event, servletUrl, formId);
});
