package br.edu.ifsp.arq.controller.news;

import br.edu.ifsp.arq.controller.utils.Utils;
import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.NewsArticle;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/create-news")
@MultipartConfig
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

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publishDate = request.getParameter("publishDate");
        String source = request.getParameter("source");
        String summary = request.getParameter("summary");
        String text = request.getParameter("text");
        Long category = Long.parseLong(request.getParameter("category"));
        String image1 = request.getParameter("image1");
        String image2 = request.getParameter("image2");

        var content = new HashMap<String, Object>();

        var isValidImage1 = validateImage(image1);
        var isValidImage2 = validateImage(image2);

        if (!isValidImage1 || !isValidImage2) {

            content.put("error", "A URL da imagem é invalida!");
            Utils.writeJsonResponse(response, content);
            return;
        }

        try {
            var newsArticleCategory = categoryDAO.getById(category);
            var imageList = new ArrayList<>(List.of(image1, image2));
            var newsArticle = new NewsArticle(title, author, publishDate, source, summary, text, newsArticleCategory, imageList);

            newsArticleDAO.add(newsArticle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("index.html");
    }

    private boolean validateImage(String imageUrl) {
        boolean isValid = false;

        try {
            URI uri = new URI(imageUrl);
            URL url = uri.toURL();
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