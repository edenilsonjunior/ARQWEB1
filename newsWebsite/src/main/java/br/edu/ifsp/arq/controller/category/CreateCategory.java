package br.edu.ifsp.arq.controller.category;

import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.entity.NewsArticleCategory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/createCategory")
public class CreateCategory extends HttpServlet {

    private static final long serialVersionUID = 1L;


    public CreateCategory() { super(); }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/retrieveCategory";
        String category = request.getParameter("category");

        if (category != null && !category.isEmpty()) {
            var dao = CategoryDAO.getInstance();
            var result = dao.add(new NewsArticleCategory(category));

            if(!result) {
                String message = "Erro ao adicionar a categoria!";
                request.setAttribute("message", message);
                url = "/category/createCategory.jsp";
            }
        } else {
            String message = "Preencha o campo corretamente!";
            request.setAttribute("message", message);
            url = "/category/createCategory.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
