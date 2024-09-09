package br.edu.ifsp.arq.controller.news;

import br.edu.ifsp.arq.model.dao.CommentaryDAO;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.Commentary;
import br.edu.ifsp.arq.model.entity.NewsArticle;
import br.edu.ifsp.arq.model.entity.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class News
 */
@WebServlet("/news")
@MultipartConfig
public class RetrieveNewsArticlePage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveNewsArticlePage() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        Long id = Long.parseLong(request.getParameter("id"));
        NewsArticle news = NewsArticleDAO.getInstance().getById(id);
        List<Commentary> commentary = CommentaryDAO.getInstance().getCommentsById(id);
        List<String> images = news.getImages();

        Map<String, Object> content = new HashMap<>();

        content.put("news", news);
        content.put("listCommentary", commentary);
        content.put("images", images);

        Gson gson = new Gson();
        String contentStr = gson.toJson(content);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(contentStr);
    } catch (NumberFormatException e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid news ID");
    } catch (Exception e) {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred: " + e.getMessage());
    }
}



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
        String comment = request.getParameter("comment");
        Long newsId = Long.parseLong(request.getParameter("newsId"));
        User user = (User) request.getSession().getAttribute("user");

        if (isLogged != null && isLogged) {
            if (user != null) {
                if (comment != null && !comment.isEmpty()) {
                    Commentary commentary = new Commentary(newsId, user, comment);
                    commentary.setId(newsId);
                    CommentaryDAO.getInstance().add(commentary);
                    response.sendRedirect("views/news/news.html?id=" + newsId);
                }
            }
        }
    }
}
