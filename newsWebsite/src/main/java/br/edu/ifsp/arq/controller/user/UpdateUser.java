package br.edu.ifsp.arq.controller.user;

import br.edu.ifsp.arq.controller.utils.Utils;
import br.edu.ifsp.arq.model.dao.UserDAO;
import br.edu.ifsp.arq.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


@WebServlet("/update-user")
@MultipartConfig
public class UpdateUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final UserDAO USER_DAO = UserDAO.getInstance();


    public UpdateUser() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "views/user/profile.html";
        response.sendRedirect(url);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        var session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("views/user/login.html");
            return;
        }

        if (password != null && password.equals(confirmPassword)) {
            user.setEmail(email);
            user.setHashPassword(password);
            USER_DAO.editUser(user);
        } else {
            var content = new HashMap<String, Object>();
            content.put("error", "As senhas não coincidem.");
            Utils.writeJsonResponse(response, content);
        }

        response.sendRedirect("views/user/profile.html");
    }
}
