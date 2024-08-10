package br.edu.ifsp.arq.controller.category;

import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.entity.NewsArticleCategory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/updateCategory")
public class UpdateCategory extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public UpdateCategory() { super(); }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        String url = "/category/updateCategory.jsp";

        var dao = CategoryDAO.getInstance();
        var category = dao.getById(id);

        request.setAttribute("category", category);

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/retrieveCategory";
        Long id = Long.parseLong(request.getParameter("id"));
        String category = request.getParameter("categoryName");

        var dao = CategoryDAO.getInstance();
        var result = dao.update(new NewsArticleCategory(id, category));

        if(!result) {
            String message = "Erro ao atualizar a categoria!";
            request.setAttribute("message", message);
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
