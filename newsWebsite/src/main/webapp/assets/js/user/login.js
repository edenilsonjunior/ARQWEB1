import {submitPost} from '../components/global.js';

const formId = 'loginForm';
const servletUrl = '/loginUser';
const container = document.getElementById(formId);

container.addEventListener('submit', async (event)=>{
    await submitPost(event, servletUrl, formId);
});
