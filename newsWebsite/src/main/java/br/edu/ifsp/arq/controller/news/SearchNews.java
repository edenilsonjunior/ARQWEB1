package br.edu.ifsp.arq.controller.news;

import br.edu.ifsp.arq.controller.utils.Utils;
import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.NewsArticle;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchNews extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchNews() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        List<NewsArticle> newsList = NewsArticleDAO.getInstance().getNewsArticleSearched(keyword);

        var content = new HashMap<String, Object>();
        content.put("newsList", newsList);
        Utils.writeJsonResponse(response, content);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}