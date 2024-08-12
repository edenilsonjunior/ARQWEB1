package br.edu.ifsp.arq.controller.utils;

import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexLoadData
 */
@WebServlet("/index")
public class IndexLoadData extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public IndexLoadData() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var categoryDao = CategoryDAO.getInstance();
        var newsDao = NewsArticleDAO.getInstance();

        var categoryList = categoryDao.getAll()
                .stream()
                .sorted((c1, c2) -> c2.getCategory().compareTo(c1.getCategory()))
                .toList();

        var newsList = newsDao.getAll().stream()
                .sorted((na1, na2) -> na2.getId().compareTo(na1.getId()))
                .toList();


        request.setAttribute("categoryList", categoryList);
        request.setAttribute("newsList", newsList);
        request.setAttribute("isLoaded", true);

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
