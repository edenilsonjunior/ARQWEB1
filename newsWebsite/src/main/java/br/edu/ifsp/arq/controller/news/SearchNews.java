package br.edu.ifsp.arq.controller.news;

import br.edu.ifsp.arq.model.dao.NewsArticleDAO;
import br.edu.ifsp.arq.model.entity.NewsArticle;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        List<NewsArticle> newsList = NewsArticleDAO.getInstance().getNewsArticleSearched(keyword);

        Map<String, Object> content = new HashMap<>();

        content.put("newsList",newsList);
        Gson gson = new Gson();
        String contentStr = gson.toJson(content);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(contentStr);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}