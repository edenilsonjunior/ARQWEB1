package br.edu.ifsp.arq.controller.category;

import br.edu.ifsp.arq.model.dao.CategoryDAO;
import br.edu.ifsp.arq.model.entity.NewsArticleCategory;
import br.edu.ifsp.arq.model.entity.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/updateCategory")
public class UpdateCategory extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public UpdateCategory() {
        super();
    }

    /**
     * Recebe a requisicao atraves do botao em listCategory.jsp
     * e redireciona para a pagina de atualizacao de categoria
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
        User user = (User) request.getSession().getAttribute("user");

        if(isLogged == null || !isLogged || user == null) {

            request.setAttribute("error", "Usuário não autenticado!");
            getServletContext().getRequestDispatcher("/retrieveCategory").forward(request, response);
            return;
        }


        String url = "/updateCategory.jsp";


        Long id = null;

        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException ex) {
            request.setAttribute("error", "Erro ao atualizar a categoria");
            getServletContext().getRequestDispatcher("retrieveCategory").forward(request, response);
        }


        var dao = CategoryDAO.getInstance();
        var category = dao.getById(id);

        request.setAttribute("category", category);

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }


    /**
     * Recebe a requisicao atraves do formulario de atualizacao de categoria
     * e redireciona para a pagina de listagem de categorias
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
        User user = (User) request.getSession().getAttribute("user");

        if(isLogged == null || !isLogged || user == null) {

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Você não está autorizado a acessar esta página.");
            return;
        }

        String url = "/retrieveCategory";
        Long id = Long.parseLong(request.getParameter("id"));
        String category = request.getParameter("categoryName");

        var dao = CategoryDAO.getInstance();
        var result = dao.update(new NewsArticleCategory(id, category));

        if (!result) {
            request.setAttribute("error", "Erro ao atualizar a categoria!");
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
