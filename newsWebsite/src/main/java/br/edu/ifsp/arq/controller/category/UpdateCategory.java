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


@WebServlet("/update-category")
@MultipartConfig
public class UpdateCategory extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public UpdateCategory() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String originalCategoryName = request.getParameter("id");
        String newCategoryName = request.getParameter("categoryName");
        var content = new HashMap<String, Object>();

        var dao = CategoryDAO.getInstance();

        var category = dao.getByCategory(originalCategoryName);

        var result = dao.update(new NewsArticleCategory(category.getId(), newCategoryName));

        if (!result) {
            Utils.writeJsonResponse(response, content);
        }

        response.sendRedirect("views/category/listCategory.html");
    }
}
