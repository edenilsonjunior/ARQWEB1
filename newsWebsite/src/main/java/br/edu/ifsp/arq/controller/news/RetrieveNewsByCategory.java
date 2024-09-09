package br.edu.ifsp.arq.controller.news;

import br.edu.ifsp.arq.controller.utils.Utils;
import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.NewsArticle;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/news-by-category")
public class RetrieveNewsByCategory extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryDAO CATEGORY_DAO = CategoryDAO.getInstance();
    private final NewsArticleDAO NEWS_DAO = NewsArticleDAO.getInstance();

    public RetrieveNewsByCategory() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var content = new HashMap<String, Object>();

        var map = new LinkedHashMap<String, List<NewsArticle>>();
        var categories = CATEGORY_DAO.getAll();

        if (categories.isEmpty()) {
            content.put("error", "Não existem categorias cadastradas.");
        } else {
            categories.forEach(c -> {
                var articles = NEWS_DAO.getNewsArticleCategories(c.getId());
                map.put(c.getCategory(), articles);
            });
            content.put("newsByCategory", map);
        }

        Utils.writeJsonResponse(response, content);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
