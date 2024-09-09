package br.edu.ifsp.arq.controller.category;

import br.edu.ifsp.arq.controller.utils.Utils;
import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.entity.NewsArticleCategory;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/update-category")
public class UpdateCategory extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final CategoryDAO CATEGORY_DAO = CategoryDAO.getInstance();

    public UpdateCategory() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var isLogged = Utils.isUserLogged(request);
        if(isLogged == null || !isLogged) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Você não está autorizado a acessar esta página.");
            return;
        }

        Long id = Long.parseLong(request.getParameter("id"));

        var content = new HashMap<String, Object>();
        content.put("category", CATEGORY_DAO.getById(id));
        Utils.writeJsonResponse(response, content);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        String category = request.getParameter("categoryName");
        var content = new HashMap<String, Object>();

        var dao = CategoryDAO.getInstance();
        var result = dao.update(new NewsArticleCategory(id, category));

        if (!result) {
            Utils.writeJsonResponse(response, content);
        }
    }
}
