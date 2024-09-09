package br.edu.ifsp.arq.controller.category;


import br.edu.ifsp.arq.controller.utils.Utils;
import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.entity.NewsArticleCategory;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/create-category")
@MultipartConfig
public class CreateCategory extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateCategory() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var isLogged = Utils.isUserLogged(request);
        if (isLogged == null || !isLogged) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Você não está autorizado a acessar esta página.");
            return;
        }

        response.sendRedirect("views/category/createCategory.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var isLogged = Utils.isUserLogged(request);
        if (isLogged == null || !isLogged) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Você não está autorizado a acessar esta página.");
            return;
        }

        String category = request.getParameter("category");
        var content = new HashMap<String, Object>();
        String url = "index.html";

        if (category != null && !category.isEmpty()) {

            var dao = CategoryDAO.getInstance();
            var result = dao.add(new NewsArticleCategory(category));

            if (!result) {
                content.put("error", "Erro ao adicionar a categoria!");
                url = "views/category/createCategory.html";
            }
        } else {
            content.put("error", "Preencha o campo corretamente!");
            url = "views/category/createCategory.html";
        }

        Utils.writeJsonResponse(response, content);
        response.sendRedirect(url);
    }
}
