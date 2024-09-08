package br.edu.ifsp.arq.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifsp.arq.model.dao.UserDAO;
import br.edu.ifsp.arq.model.entity.User;
import com.google.gson.Gson;


@WebServlet("/check-login-status")
@MultipartConfig
public class CheckLoginStatus extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final UserDAO USER_DAO = UserDAO.getInstance();


    public CheckLoginStatus() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Boolean isLogged = (Boolean) request.getSession().getAttribute("isLogged");
        User user = (User) request.getSession().getAttribute("user");

        Map<String, Object> content = new HashMap<>();

        content.put("user", user);
        content.put("isLogged", isLogged != null && isLogged);

        String contentStr = new Gson().toJson(content);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(contentStr);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
