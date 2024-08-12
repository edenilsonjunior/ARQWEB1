<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="includes/navbar/header-default.jsp" %>

<!-- Navbar -->
<c:choose>
    <c:when test="${sessionScope.isLogged == true}">
        <c:import url="includes/navbar/navbar-logged-in.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="includes/navbar/navbar-logged-out.jsp"/>
    </c:otherwise>
</c:choose>


<main class="container">

    <h1 class="my-5 text-center">Sobre o Sistema</h1>

    <section class="about">
        <p>Bem-vindo ao IFNews! Este sistema foi desenvolvido para oferecer uma plataforma completa e intuitiva para a
            gestão e visualização de notícias. Abaixo estão alguns dos principais recursos e funcionalidades do
            sistema:</p>
        <ul>
            <li><strong>Adição de Notícias:</strong> Apenas usuários registrados têm a permissão para adicionar novas
                notícias ao sistema. Isso garante que todas as informações sejam validadas e revisadas antes de serem
                publicadas.
            </li>
            <li><strong>Visualização de Notícias:</strong> Todos os usuários, sejam registrados ou não, podem visualizar
                as notícias. Cada notícia inclui informações detalhadas como título, autor, data de publicação, fonte,
                resumo, texto completo e imagens.
            </li>
            <li><strong>Atualização e Exclusão de Notícias:</strong> Usuários registrados podem atualizar ou remover
                notícias existentes. Isso assegura que o conteúdo esteja sempre atualizado e relevante.
            </li>
            <li><strong>Gerenciamento de Categorias:</strong> O sistema permite a criação, edição e remoção de
                categorias de notícias, como política, esportes, entretenimento e tecnologia. Isso ajuda na organização
                e na fácil filtragem das notícias.
            </li>
            <li><strong>Busca de Notícias:</strong> Oferecemos uma funcionalidade de busca que permite encontrar
                notícias por título, autor, categoria, data de publicação e outros critérios relevantes.
            </li>
            <li><strong>Comentários:</strong> Somente usuários registrados podem adicionar comentários às notícias,
                promovendo a interação e o engajamento com o conteúdo.
            </li>
            <li><strong>Segurança:</strong> A segurança é uma prioridade. As senhas dos usuários são criptografadas no
                banco de dados para proteger suas informações pessoais.
            </li>
        </ul>
        <p>Para mais informações ou se tiver dúvidas, por favor, entre em contato conosco através da nossa página de
            contato.</p>
    </section>

</main>

<%@ include file="includes/footer/footer.jsp" %>
<%@ include file="includes/footer/footer-default.jsp" %>


