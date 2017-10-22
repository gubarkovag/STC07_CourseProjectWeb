package com.stc07.gubarkovag.servlets.users;

import com.stc07.gubarkovag.pojo.User;
import com.stc07.gubarkovag.services.UserService;
import com.stc07.gubarkovag.services.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UsersServlet.class);

    private static UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI();
        String action = requestPath.substring(requestPath.lastIndexOf('/') + 1, requestPath.length());

        List<User> users = null;

        try {
            switch(action) {
                case "users":
                    users = userService.getAll();
                    req.setAttribute("users", users);
                    req.getRequestDispatcher("/pages/users/viewusers.jsp").forward(req, resp);
                    break;
                case "deleteUser":
                    userService.deleteById(Integer.valueOf(req.getParameter("id")));
                    resp.sendRedirect("/courseprojectweb/site/users");
                    break;
                case "editUser":
                    req.getRequestDispatcher("/site/editUser").forward(req, resp);
                    break;
            }
        } catch (UserServiceImpl.UserServiceException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
        }
        //req.getRequestDispatcher("/pages/users/viewusers.jsp").forward(req, resp);
    }
}
