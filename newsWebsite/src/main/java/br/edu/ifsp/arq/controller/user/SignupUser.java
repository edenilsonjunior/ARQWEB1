package br.edu.ifsp.arq.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifsp.arq.model.dao.UserDAO;
import br.edu.ifsp.arq.model.entity.User;

@WebServlet("/signupUser")
public class SignupUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDAO userDAO;

	@Override
    public void init() throws ServletException {
        super.init();
        userDAO = UserDAO.getInstance();
    }

    public SignupUser() {}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/login.jsp";
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userDAO.validateEmail(email)) {
            if (username.isEmpty() || password.isEmpty()) {
                url = "/signup.jsp";
                request.setAttribute("error", "Você deve preencher todos os campos");
            } else {
                User user = new User(username, email, password);
                userDAO.addUser(user);
            }
        } else {
            url = "/signup.jsp";
            request.setAttribute("error", "Email já existente");
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }


}
