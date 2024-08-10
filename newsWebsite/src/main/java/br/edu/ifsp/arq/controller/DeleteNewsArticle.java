package br.edu.ifsp.arq.controller;

import br.edu.ifsp.arq.model.dao.CategoryDAO;
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
    private static NewsArticleDAO newsArticleDAO;
    private static CategoryDAO categoryDAO;

    public DeleteNewsArticle() {
        super();
        newsArticleDAO = NewsArticleDAO.getInstance();
        categoryDAO = CategoryDAO.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String url = "/index.jsp";
        var newsArticle = newsArticleDAO.getNewsArticleById(id);
        newsArticleDAO.deleteNewsArticle(newsArticle);
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}