package br.edu.ifsp.arq.controller.user;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifsp.arq.model.dao.UserDAO;
import br.edu.ifsp.arq.model.entity.User;
import com.google.gson.Gson;

@WebServlet("/signupUser")
@MultipartConfig
public class SignupUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = UserDAO.getInstance();
    }

    public SignupUser() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        var responseContent = new HashMap<String, Object>();

        if (userDAO.validateEmail(email)) {
            if (!username.isEmpty() && !password.isEmpty()) {
                User user = new User(username, email, password);
                userDAO.addUser(user);
                response.sendRedirect("views/user/login.html");
                return;
            } else {
                responseContent.put("error", "Você deve preencher todos os campos");
            }
        } else {
            responseContent.put("error", "Email já existente");
        }

        Gson gson = new Gson();
        String contentStr = gson.toJson(responseContent);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(contentStr);
    }
}
