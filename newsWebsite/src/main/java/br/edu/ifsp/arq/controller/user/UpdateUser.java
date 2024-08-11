package br.edu.ifsp.arq.controller.user;

import br.edu.ifsp.arq.model.dao.UserDAO;
import br.edu.ifsp.arq.model.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDAO userDAO;

	public UpdateUser() {
		super();
		userDAO = UserDAO.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null) {
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("/user/login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null) {
			if (password != null && password.equals(confirmPassword)) {
				user.setEmail(email);
				user.setHashPassword(password);

				userDAO.editUser(user);

				session.setAttribute("user", user);

				response.sendRedirect("/user/profile.jsp");
			} else {
				request.setAttribute("msg", "As senhas não coincidem.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/user/profile.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			response.sendRedirect("/user/login.jsp");
		}
	}

}
