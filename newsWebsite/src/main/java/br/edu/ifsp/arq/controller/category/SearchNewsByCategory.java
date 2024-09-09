package br.edu.ifsp.arq.controller.category;

import br.edu.ifsp.arq.model.dao.NewsArticleDAO;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/search-by-category")
public class SearchNewsByCategory extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final NewsArticleDAO NEWS_DAO = NewsArticleDAO.getInstance();

    public SearchNewsByCategory() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        var newsList = NEWS_DAO.getNewsArticleCategories(id);

        request.setAttribute("listNews", newsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newsSearch.html");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
