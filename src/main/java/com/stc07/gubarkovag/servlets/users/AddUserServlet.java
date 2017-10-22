package com.stc07.gubarkovag.servlets.users;

import com.stc07.gubarkovag.pojo.User;
import com.stc07.gubarkovag.services.AuthorizationService;
import com.stc07.gubarkovag.services.AuthorizationServiceImpl;
import com.stc07.gubarkovag.services.UserService;
import com.stc07.gubarkovag.services.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(AddUserServlet.class);

    private static AuthorizationService as = new AuthorizationServiceImpl();
    private static UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/users/adduser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == "" || password == "") {
            req.setAttribute("wrongReg", "Логин и пароль не должны быть пустыми");
            req.getRequestDispatcher("/pages/users/adduser.jsp").forward(req, resp);
        }
        if (as.auth(login, password) != null) {
            req.setAttribute("wrongReg", "Пользователь с такими данными уже существует");
            req.getRequestDispatcher("/pages/users/adduser.jsp").forward(req, resp);
        } else {
            try {
                userService.insertOne(new User(login, password, User.Role.AUTHORIZEDUSER));
            } catch (UserServiceImpl.UserServiceException e) {
                logger.error(new StringBuilder()
                        .append(e.getMessage()).append(System.lineSeparator())
                        .append(e.getStackTrace()).toString());
                //e.printStackTrace();
            }
            resp.sendRedirect("/courseprojectweb/");
        }
    }
}
