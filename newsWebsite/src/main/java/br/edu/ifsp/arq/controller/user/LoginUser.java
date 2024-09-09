package br.edu.ifsp.arq.controller.user;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.arq.controller.utils.Utils;
import br.edu.ifsp.arq.model.dao.UserDAO;
import br.edu.ifsp.arq.model.entity.User;


@WebServlet("/login")
@MultipartConfig
public class LoginUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final UserDAO USER_DAO = UserDAO.getInstance();

    public LoginUser() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "views/user/login.html";
        response.sendRedirect(url);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = USER_DAO.getUserByEmail(email);
        var responseContent = new HashMap<String, Object>();


        if (user == null) {
            responseContent.put("error", "Não existe usuário com este email");
            Utils.writeJsonResponse(response, responseContent);
            return;
        }

        if (!user.checkPassword(password)) {
            responseContent.put("error", "Não foi possível realizar Login, verifique Email e Senha");
            Utils.writeJsonResponse(response, responseContent);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("isLogged", true);
        response.sendRedirect("index.html");
    }
}
