package br.edu.ifsp.arq.controller.utils;

import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.NewsArticleCategory;

import java.io.IOException;
import java.util.Comparator;
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

        var categoryList = categoryDao.getAll().stream().sorted(Comparator.comparing(na -> na.getCategory().length())).toList();
        var newsList = newsDao.getAll().stream().sorted(Comparator.comparing(na -> na.getPublishDate())).toList();


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
