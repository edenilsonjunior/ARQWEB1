package br.edu.ifsp.arq.controller.news;

import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteNewsArticle")
public class DeleteNewsArticle extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static NewsArticleDAO newsArticleDAO;

    public DeleteNewsArticle() {
        super();
        newsArticleDAO = NewsArticleDAO.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
        User user = (User) request.getSession().getAttribute("user");

        if (isLogged == null || !isLogged || user == null) {

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Você não está autorizado a acessar esta página.");
            return;
        }

        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException ex) {
            request.setAttribute("error", "Erro ao deletar a notícia");
            getServletContext().getRequestDispatcher("/retrieveNewsArticle").forward(request, response);
        }

        String url = "/index.jsp";
        var newsArticle = newsArticleDAO.getById(id);
        newsArticleDAO.deleteNewsArticle(newsArticle);
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}