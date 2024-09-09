import {checkUserPermission, contextPath, submitPost} from "../components/global.js";

const formId = 'profileForm';
const formContainer = document.getElementById(formId);
const servletUrl = '/update-user';

const usernameContainer = document.getElementById('username');
const emailContainer = document.getElementById('email');



document.addEventListener('DOMContentLoaded', async () => {
    await checkUserPermission();

    try {
        const response = await fetch(contextPath + '/retrieve-user');
        let data = await response.json();

        console.log(data);
        loadUser(data);

    } catch (error) {
        console.error(error);
    }

});

const loadUser = (data) => {

    usernameContainer.textContent = data.user.username;
    emailContainer.value = data.user.email;
}


formContainer.addEventListener('submit', async (event) => {
    await submitPost(event, servletUrl, formId);
});



