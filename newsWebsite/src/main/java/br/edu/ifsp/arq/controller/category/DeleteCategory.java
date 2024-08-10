package br.edu.ifsp.arq.controller.category;

import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteCategory")
public class DeleteCategory extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public DeleteCategory() { super(); }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        String url = "/retrieveCategory";

        var categoryDAO = CategoryDAO.getInstance();
        var newsArticleDAO = NewsArticleDAO.getInstance();

        var newsList = newsArticleDAO.getNewsArticleCategories(id);

        if(!newsList.isEmpty()) {
            request.setAttribute("error", "Não é possível deletar a categoria pois existem notícias associadas a ela");
            getServletContext().getRequestDispatcher(url).forward(request, response);
            return;
        }

        var result = categoryDAO.deleteById(id);

        if(!result)
            request.setAttribute("error", "Erro ao deletar a categoria");

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
