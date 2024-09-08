package br.edu.ifsp.arq.controller.category;

import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.NewsArticle;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/searchByCategory")
public class SearchNewsByCategory extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public SearchNewsByCategory() {
        super();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));

        List<NewsArticle> newsList = NewsArticleDAO.getInstance().getNewsArticleCategories(id);

        request.setAttribute("listNews", newsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newsSearch.html");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
