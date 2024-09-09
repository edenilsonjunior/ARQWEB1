package br.edu.ifsp.arq.controller.category;

import br.edu.ifsp.arq.controller.utils.Utils;
import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/delete-category")
public class DeleteCategory extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryDAO CATEGORY_DAO = CategoryDAO.getInstance();
    private final NewsArticleDAO NEWS_DAO = NewsArticleDAO.getInstance();

    public DeleteCategory() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var isLogged = Utils.isUserLogged(request);
        if(isLogged == null || !isLogged) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Você não está autorizado a acessar esta página.");
            return;
        }

        String categoryName = request.getParameter("category");

        var category = CATEGORY_DAO.getByCategory(categoryName);
        var newsList = NEWS_DAO.getNewsArticleCategories(category.getId());

        var content = new HashMap<String, Object>();
        if (!newsList.isEmpty()) {
            content.put("error", "Não é possível deletar a categoria pois existem notícias associadas a ela");
        } else {
            var result = CATEGORY_DAO.deleteById(category.getId());

            if (!result)
                content.put("error", "Erro ao deletar a categoria");
        }

        Utils.writeJsonResponse(response, content);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
