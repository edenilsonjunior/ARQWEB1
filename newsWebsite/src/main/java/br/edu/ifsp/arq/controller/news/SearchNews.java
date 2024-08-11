package br.edu.ifsp.arq.controller.news;

import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.NewsArticle;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchNews extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchNews() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String keyword = request.getParameter("keyword").toLowerCase();

        List<NewsArticle> newsList = NewsArticleDAO.getInstance().getNewsArticleSearched(keyword);
        List<NewsArticle> filteredNewsList = newsList.stream()
            .filter(newsArticle -> newsArticle.getSummary().contains(keyword) ||
               newsArticle.getText().toLowerCase().contains(keyword) ||
               newsArticle.getTitle().toLowerCase().contains(keyword) ||
               newsArticle.getAuthor().toLowerCase().contains(keyword) ||
               newsArticle.getSource().toLowerCase().contains(keyword))
               .collect(Collectors.toList());

        request.setAttribute("listNews", filteredNewsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/news/newsSearch.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}