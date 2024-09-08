package br.edu.ifsp.arq.controller.news;

import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.NewsArticle;
import br.edu.ifsp.arq.model.entity.NewsArticleCategory;
import br.edu.ifsp.arq.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/updateNewsArticle")
public class UpdateNewsArticle extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static NewsArticleDAO newsArticleDAO;
    private static CategoryDAO categoryDAO;

    public UpdateNewsArticle() {
        super();
        newsArticleDAO = NewsArticleDAO.getInstance();
        categoryDAO = CategoryDAO.getInstance();
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
            request.setAttribute("error", "Erro ao atualizar a notícia");
            getServletContext().getRequestDispatcher("/retrieveNewsArticle").forward(request, response);
        }

        String url = "/updateNewsArticle.html";
        request.setAttribute("newsArticle", newsArticleDAO.getById(id));
        request.setAttribute("categoryList", categoryDAO.getAll());
        request.setAttribute("isLoaded", true);

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "/index.html";
        Long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publishDate = request.getParameter("publishDate");
        String source = request.getParameter("source");
        String summary = request.getParameter("summary");
        String text = request.getParameter("text");
        Long category = Long.parseLong(request.getParameter("category"));
        String image1 = request.getParameter("image1");
        String image2 = request.getParameter("image2");

        try {
            NewsArticleCategory newsArticleCategory = categoryDAO.getById(category);
            List<String> imageList = new ArrayList<>();
            imageList.add(image1);
            imageList.add(image2);
            NewsArticle newsArticle = new NewsArticle(title, author, publishDate, source, summary, text, newsArticleCategory, imageList);
            newsArticle.setId(id);

            newsArticleDAO.editNewsArticle(newsArticle);

        } catch (Exception e) {
            url = "/updateNewsArticle.html";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}