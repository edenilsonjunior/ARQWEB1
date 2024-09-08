import {contextPath} from '../components/global.js';


const submitLogin = async (event) => {

    event.preventDefault();

    const form = document.getElementById('login');
    const formData = new FormData(form);

    try {
        const response = await fetch(contextPath + '/loginUser', {
            method: 'POST',
            body: formData
        });

        if (response.redirected) {
            window.location.href = response.url;
            return;
        }

        const data = await response.json();

        if (data.error) {
            const errorMessageElement = document.getElementById('error-message');
            errorMessageElement.textContent = data.error;
            errorMessageElement.style.display = 'block';
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

document.getElementById('loginForm').addEventListener('submit', submitLogin);
