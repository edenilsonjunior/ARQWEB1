import {checkLoginStatus, contextPath} from './global.js';

const navbarContainer = document.getElementById('nav-bar');

document.addEventListener('DOMContentLoaded', async () => {

    const response = await fetch(`${contextPath}/assets/js/components/navbar-template.html`);
    navbarContainer.innerHTML = await response.text();

    let data = await checkLoginStatus();
    await customizeNavbar(data.isLogged);
});

const customizeNavbar = async (isLogged) => {

    document.getElementById('nav-logo').src = `${contextPath}/assets/images/IFNews.png`;
    loadNavbarLinks(isLogged);

    document.getElementById('search-form').action = `${contextPath}/views/news/newsSearch.html`;

    loadAuthLinks(isLogged);
};

const loadNavbarLinks = (isLogged) =>{

    document.getElementById('nav-links').innerHTML = `
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${contextPath}/index.html">Página Inicial</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${contextPath}/retrieve-category">Categorias</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${contextPath}/about.html">Sobre</a>
        </li>
        ${isLogged ? `
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/views/news/createNewsArticle.html">Cadastrar Noticia</a>
            </li>
        ` : ''}
    `;
}

const loadAuthLinks = (isLogged)  =>{
    const authLinks = document.getElementById('auth-links');
    if (!isLogged) {
        authLinks.innerHTML = `
            <li class="nav-item">
                <a href="${contextPath}/signup" class="btn btn-outline-secondary" role="button">SignUp</a>
                <a href="${contextPath}/login" class="btn btn-secondary" role="button">Login</a>
            </li>
        `;
    } else {
        authLinks.innerHTML = `
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="https://cdn-icons-png.flaticon.com/512/1144/1144760.png" alt="user" width="30px">
                </a>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="${contextPath}/views/user/profile.html">Perfil</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="${contextPath}/logout">Sair</a></li>
                </ul>
            </li>
        `;
    }

}
