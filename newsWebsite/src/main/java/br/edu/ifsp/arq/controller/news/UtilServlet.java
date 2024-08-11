package br.edu.ifsp.arq.controller.news;

import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.entity.NewsArticleCategory;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/utilServlet")
public class UtilServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static CategoryDAO categoryDAO;

    public UtilServlet() {
        super();
        categoryDAO = CategoryDAO.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<NewsArticleCategory> categoryList = categoryDAO.getAll();
        request.setAttribute("categoryList", categoryList);
        getServletContext().getRequestDispatcher("/news/createNewsArticle.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}