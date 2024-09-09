package br.edu.ifsp.arq.controller.user;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifsp.arq.controller.utils.Utils;
import br.edu.ifsp.arq.model.dao.UserDAO;
import br.edu.ifsp.arq.model.entity.User;


@WebServlet("/signup")
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "views/user/signup.html";
        response.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        var content = new HashMap<String, Object>();

        if (userDAO.validateEmail(email)) {
            if (!username.isEmpty() && !password.isEmpty()) {
                User user = new User(username, email, password);
                userDAO.addUser(user);
                response.sendRedirect("views/user/login.html");
                return;
            } else {
                content.put("error", "Você deve preencher todos os campos");
            }
        } else {
            content.put("error", "Email já existente");
        }

        Utils.writeJsonResponse(response, content);
    }
}
