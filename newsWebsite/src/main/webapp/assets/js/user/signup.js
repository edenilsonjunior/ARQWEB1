import {contextPath} from '../components/global.js';

const submitSignup = async (event) => {
    event.preventDefault();

    const formData = new FormData(document.getElementById('signupForm'));

    try {
        const response = await fetch(`${contextPath}/signupUser`, {
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
        console.error('Error: ', error);
    }
}

document.getElementById('signupForm').addEventListener('submit', submitSignup);
