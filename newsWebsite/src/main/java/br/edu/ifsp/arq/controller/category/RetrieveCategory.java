package br.edu.ifsp.arq.controller.category;

import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.NewsArticle;
import br.edu.ifsp.arq.model.entity.NewsArticleCategory;

import java.io.IOException;
import java.util.List;
import java.util.LinkedHashMap;
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

        String url = "/category/listCategory.jsp";

        var categoryDAO = CategoryDAO.getInstance();
        var newsArticleDAO = NewsArticleDAO.getInstance();

        var map = new LinkedHashMap<NewsArticleCategory, List<NewsArticle>>();

        for (var category : categoryDAO.getAll()) {
            map.put(category, newsArticleDAO.getNewsArticleCategories(category.getId()));
        }

        request.setAttribute("map", map);

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
