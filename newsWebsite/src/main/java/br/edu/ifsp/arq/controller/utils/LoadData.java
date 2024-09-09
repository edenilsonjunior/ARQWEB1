package br.edu.ifsp.arq.controller.utils;

import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.User;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/load-data")
public class LoadData extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final CategoryDAO CATEGORY_DAO = CategoryDAO.getInstance();
    private static final NewsArticleDAO NEWS_ARTICLE_DAO = NewsArticleDAO.getInstance();

    public LoadData() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
        User user = (User) request.getSession().getAttribute("user");

        var categoryList = CATEGORY_DAO.getAll()
                .stream()
                .sorted((c1, c2) -> c2.getCategory().compareTo(c1.getCategory()))
                .toList();

        var newsList = NEWS_ARTICLE_DAO.getAll().stream()
                .sorted((na1, na2) -> na2.getId().compareTo(na1.getId()))
                .toList();

        var content = new HashMap<String, Object>();
        content.put("categoryList", categoryList);
        content.put("newsList", newsList);
        content.put("user", user);
        content.put("isLogged", isLogged != null && isLogged);

        Utils.writeJsonResponse(response, content);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
