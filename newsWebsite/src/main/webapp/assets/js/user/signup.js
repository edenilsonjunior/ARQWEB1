import {submitPost} from '../components/global.js';

const formId = 'signupForm';
const servletUrl = '/signup';
const formContainer = document.getElementById(formId);

formContainer.addEventListener('submit', async (event)=>{
    await submitPost(event, servletUrl, formId);
});
