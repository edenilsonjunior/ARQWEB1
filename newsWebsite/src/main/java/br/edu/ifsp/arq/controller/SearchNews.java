package br.edu.ifsp.arq.controller;

import br.edu.ifsp.arq.model.dao.MockNewsArticleDAO;
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
        String keyword = request.getParameter("keyword");
        List<NewsArticle> newsList = MockNewsArticleDAO.getInstance().getNewsArticleSearched(keyword);
        List<NewsArticle> filteredNewsList = newsList.stream()
            .filter(newsArticle -> newsArticle.getSummary().contains(keyword) ||
               newsArticle.getText().contains(keyword) ||
               newsArticle.getTitle().contains(keyword) ||
               newsArticle.getAuthor().contains(keyword) ||
               newsArticle.getSource().contains(keyword))
               .collect(Collectors.toList());

        request.setAttribute("listNews", filteredNewsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("newsSearch.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}