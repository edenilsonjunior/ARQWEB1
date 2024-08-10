package br.edu.ifsp.arq.controller;

import br.edu.ifsp.arq.model.dao.CommentaryDAO;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.dao.UserDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createNewsArticle")
public class CreateNewsArticle extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static NewsArticleDAO newsArticleDAO;
    private static CommentaryDAO commentaryDAO;
    private static UserDAO userDAO;

    public CreateNewsArticle() {
        super();
        newsArticleDAO = NewsArticleDAO.getInstance();
        commentaryDAO = CommentaryDAO.getInstance();
        userDAO = UserDAO.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}