package br.edu.ifsp.arq.controller.news;

import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.NewsArticle;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/update-news")
@MultipartConfig
public class UpdateNewsArticle extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static NewsArticleDAO newsArticleDAO = NewsArticleDAO.getInstance();
    private static CategoryDAO categoryDAO = CategoryDAO.getInstance();

    public UpdateNewsArticle() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "index.html";
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
            var newsArticleCategory = categoryDAO.getById(category);
            List<String> imageList = new ArrayList<>();
            imageList.add(image1);
            imageList.add(image2);
            NewsArticle newsArticle = new NewsArticle(title, author, publishDate, source, summary, text, newsArticleCategory, imageList);
            newsArticle.setId(id);

            newsArticleDAO.editNewsArticle(newsArticle);

        } catch (Exception e) {
            url = "views/news/updateNewsArticle.html";
        }

        response.sendRedirect(url);
    }
}