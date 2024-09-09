package br.edu.ifsp.arq.controller.user;

import br.edu.ifsp.arq.controller.utils.Utils;
import br.edu.ifsp.arq.model.entity.User;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/retrieve-user")
public class RetrieveUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RetrieveUser() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        var content = new HashMap<String, Object>();
        content.put("user",user);

        Utils.writeJsonResponse(response, content);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
