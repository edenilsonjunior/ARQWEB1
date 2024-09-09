package br.edu.ifsp.arq.controller.category;

import br.edu.ifsp.arq.controller.utils.Utils;
import br.edu.ifsp.arq.model.dao.CategoryDAO;

import java.io.IOException;
import java.util.LinkedHashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/retrieve-category")
public class RetrieveCategory extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final CategoryDAO CATEGORY_DAO = CategoryDAO.getInstance();

    public RetrieveCategory() { super(); }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var categories = CATEGORY_DAO.getAll();

        var map = new LinkedHashMap<String, Object>();

        if(!categories.isEmpty())
            map.put("categories", categories);
        else
            map.put("error", "Não existem categorias cadastradas.");


        Utils.writeJsonResponse(response, map);
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
