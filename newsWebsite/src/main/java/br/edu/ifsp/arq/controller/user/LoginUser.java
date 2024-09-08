package br.edu.ifsp.arq.controller.user;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.arq.model.dao.UserDAO;
import br.edu.ifsp.arq.model.entity.User;
import com.google.gson.Gson;


@WebServlet("/loginUser")
public class LoginUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final UserDAO USER_DAO = UserDAO.getInstance();


    public LoginUser() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = USER_DAO.getUserByEmail(email);

        var responseContent = new HashMap<String, Object>();

        if (user != null) {
            if (user.checkPassword(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("isLogged", true);
                response.sendRedirect("index.html");
                return;
            }else {
                responseContent.put("error", "Não foi possível realizar Login, verifique Email e Senha");
            }
        }else{
            responseContent.put("error", "Não existe usuário com este email");
        }

        Gson gson = new Gson();
        String contentStr = gson.toJson(responseContent);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(contentStr);
    }
}
