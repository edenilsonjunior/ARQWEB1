package br.edu.ifsp.arq.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.arq.model.dao.UserDAO;
import br.edu.ifsp.arq.model.entity.User;


@WebServlet("/loginUser")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginUser() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDAO udao = UserDAO.getInstance();
		User user = udao.getUserByEmail(email);
		
		if(user != null) {
			if(user.checkPassword(password)) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);	
				session.setAttribute("isLogged", true);	
				response.sendRedirect("index.jsp");
			}else {
				request.setAttribute("msg", "Não foi possível realizar Login, verifique Email e Senha");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/user/login.jsp");
				dispatcher.forward(request, response);
			}
		}	
	}

}
