package br.edu.ifsp.arq.controller;

import br.edu.ifsp.arq.model.dao.MockCommentaryDAO;
import br.edu.ifsp.arq.model.dao.MockNewsArticleDAO;
import br.edu.ifsp.arq.model.entity.Commentary;
import br.edu.ifsp.arq.model.entity.NewsArticle;
import br.edu.ifsp.arq.model.entity.User;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class News
 */
@WebServlet("/news")
public class News extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public News() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            NewsArticle news = MockNewsArticleDAO.getInstance().getNewsArticleById(id);
            request.setAttribute("news", news);

            List<Commentary> commentary = MockCommentaryDAO.getInstance().getCommentsById(id);
            request.setAttribute("listCommentary", commentary);

            getServletContext().getRequestDispatcher("/news.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid news ID");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
        String comment = request.getParameter("comment");
        Long newsId = Long.parseLong(request.getParameter("newsId"));
        User user = (User) request.getSession().getAttribute("user");

        if (isLogged != null && isLogged) {
            if (user != null) {
                if (comment != null && !comment.isEmpty()) {
                    Commentary commentary = new Commentary(newsId, user, comment);
                    MockCommentaryDAO.getInstance().addCommentary(commentary, newsId, user);
                    response.sendRedirect("news?id=" + newsId);
                }
            }
        }
    }
}
