package br.edu.ifsp.arq.controller;

//import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.NewsArticle;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchNews
 */
@WebServlet("/search")
public class SearchNews extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchNews() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String keyword = request.getParameter("keyword");
//
////        List<NewsArticle> newsList = NewsArticleDAO.getInstance().searchNews(keyword);
//
//        request.setAttribute("news_list", newsList);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("news.jsp");
//        dispatcher.forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}