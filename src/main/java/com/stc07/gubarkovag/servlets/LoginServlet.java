package com.stc07.gubarkovag.servlets;

import com.stc07.gubarkovag.pojo.User;
import com.stc07.gubarkovag.services.AuthorizationService;
import com.stc07.gubarkovag.services.AuthorizationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    private static AuthorizationService as = new AuthorizationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Map<Boolean, User> authInfo;
        if ((authInfo = as.auth(login, password)) != null) {
            req.getSession().setAttribute("isAuth", true);
            resp.sendRedirect("/courseprojectweb/" + authInfo.get(true).getRole().toString().toLowerCase());
        } else {
            req.setAttribute("isUserNotExistMessage", "Пользователь с такими данными не существует");
            req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }
    }
}
