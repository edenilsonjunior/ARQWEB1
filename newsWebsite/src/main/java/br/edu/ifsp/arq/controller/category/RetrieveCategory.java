package br.edu.ifsp.arq.controller.category;

import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.NewsArticle;
import br.edu.ifsp.arq.model.entity.NewsArticleCategory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/retrieveCategory")
public class RetrieveCategory extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RetrieveCategory() { super(); }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var categoryDAO = CategoryDAO.getInstance();
        var newsArticleDAO = NewsArticleDAO.getInstance();

        var map = new HashMap<NewsArticleCategory, List<NewsArticle>>();

        for (var category : categoryDAO.getAll()) {
            map.put(category, newsArticleDAO.getNewsArticleCategories(category.getId()));
        }

        String url = "/category/listCategory.jsp";

        request.setAttribute("map", map);
        request.setAttribute("categories", categoryDAO.getAll());


        getServletContext().getRequestDispatcher(url).forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
