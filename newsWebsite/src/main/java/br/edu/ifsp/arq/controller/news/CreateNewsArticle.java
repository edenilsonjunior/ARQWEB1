package br.edu.ifsp.arq.controller.news;

import br.edu.ifsp.arq.controller.utils.Utils;
import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.NewsArticle;
import br.edu.ifsp.arq.model.entity.NewsArticleCategory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createNewsArticle")
public class CreateNewsArticle extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static NewsArticleDAO newsArticleDAO;
    private static CategoryDAO categoryDAO;

    public CreateNewsArticle() {
        super();
        newsArticleDAO = NewsArticleDAO.getInstance();
        categoryDAO = CategoryDAO.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Boolean isLogged = Utils.isUserLogged(request);

        if(isLogged == null || !isLogged) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Você não está autorizado a acessar esta página.");
            return;
        }

        response.sendRedirect("views/news/createNewsArticle.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/index.html";
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publishDate = request.getParameter("publishDate");
        String source = request.getParameter("source");
        String summary = request.getParameter("summary");
        String text = request.getParameter("text");
        Long category = Long.parseLong(request.getParameter("category"));
        String image1 = request.getParameter("image1");
        String image2 = request.getParameter("image2");

        var isValidImage1 = validateImage(image1);
        var isValidImage2 = validateImage(image2);

        if (!isValidImage1 || !isValidImage2) {
            url = "/createNewsArticle.html";
            request.setAttribute("error", "A URL da imagem é invalida!");
            getServletContext().getRequestDispatcher(url).forward(request, response);
            return;
        }

        try {
            NewsArticleCategory newsArticleCategory = categoryDAO.getById(category);
            List<String> imageList = new ArrayList<>();
            imageList.add(image1);
            imageList.add(image2);
            NewsArticle newsArticle = new NewsArticle(title, author, publishDate, source, summary, text, newsArticleCategory, imageList);
            newsArticleDAO.add(newsArticle);

        } catch (Exception e) {
            url = "/createNewsArticle.html";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    private boolean validateImage(String imageUrl) {
        boolean isValid = false;

        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();

            String contentType = connection.getContentType();
            if (contentType != null && contentType.startsWith("image/")) {
                isValid = true;
            }
        } catch (Exception e) {
            isValid = false;
        }

        return isValid;
    }
}