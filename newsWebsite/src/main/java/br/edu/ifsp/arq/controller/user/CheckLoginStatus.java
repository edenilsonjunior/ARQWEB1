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


@WebServlet("/check-login-status")
@MultipartConfig
public class CheckLoginStatus extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CheckLoginStatus() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var isLogged = Utils.isUserLogged(request);

        var content = new HashMap<String, Object>();
        content.put("isLogged", isLogged != null && isLogged);

        Utils.writeJsonResponse(response, content);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
