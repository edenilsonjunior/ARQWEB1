package br.edu.ifsp.arq.controller.category;

import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.entity.NewsArticleCategory;
import br.edu.ifsp.arq.model.entity.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

        Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
        User user = (User) request.getSession().getAttribute("user");

        if(isLogged == null || !isLogged || user == null) {

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Você não está autorizado a acessar esta página.");
            return;
        }

        String url = "views/category/createCategory.html";
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //String url = "/retrieveCategory";

        Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
        User user = (User) request.getSession().getAttribute("user");

        if(isLogged == null || !isLogged || user == null) {

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Você não está autorizado a acessar esta página.");
            return;
        }

        String category = request.getParameter("category");

        Map<String, Object> content = new HashMap<>();

        if (category != null && !category.isEmpty()) {

            var dao = CategoryDAO.getInstance();
            var result = dao.add(new NewsArticleCategory(category));

            if (!result) {
                content.put("error", "Erro ao adicionar a categoria!");
            }
        } else {
            request.setAttribute("error", "Preencha o campo corretamente!");
//            url = "/createCategory.html";
        }

//        request.setAttribute("categoryList", categoryList);
//        request.setAttribute("newsList", newsList);
        request.setAttribute("isLoaded", true);

        getServletContext().getRequestDispatcher("/index.html").forward(request, response);
    }
}
