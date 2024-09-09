package br.edu.ifsp.arq.controller.utils;

import br.edu.ifsp.arq.model.entity.User;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class Utils {

    public static User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

    public static Boolean isUserLogged(HttpServletRequest request) {
        return (Boolean) request.getSession().getAttribute("user");
    }


    public static void writeJsonResponse(HttpServletResponse response, Map<String, Object> content) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(new Gson().toJson(content));
    }
}
