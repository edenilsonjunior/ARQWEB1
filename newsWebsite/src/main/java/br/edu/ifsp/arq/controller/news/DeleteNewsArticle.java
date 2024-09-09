package br.edu.ifsp.arq.controller.news;

import br.edu.ifsp.arq.controller.utils.Utils;
import br.edu.ifsp.arq.model.dao.CommentaryDAO;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteNewsArticle")
public class DeleteNewsArticle extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final NewsArticleDAO NEWS_DAO = NewsArticleDAO.getInstance();
    private static final CommentaryDAO COMMENTARY_DAO = CommentaryDAO.getInstance();

    public DeleteNewsArticle() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Boolean isLogged = Utils.isUserLogged(request);

        if (isLogged == null || !isLogged) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Você não está autorizado a acessar esta página.");
            return;
        }

        Long id = Long.parseLong(request.getParameter("id"));
        var article = NEWS_DAO.getById(id);

        COMMENTARY_DAO.deleteCommentaryByNewsId(id);
        NEWS_DAO.deleteNewsArticle(article);

        response.sendRedirect("index.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}