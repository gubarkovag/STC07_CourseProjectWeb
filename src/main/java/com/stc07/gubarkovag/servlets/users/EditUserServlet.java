package com.stc07.gubarkovag.servlets.users;

import com.stc07.gubarkovag.pojo.User;
import com.stc07.gubarkovag.services.UserService;
import com.stc07.gubarkovag.services.UserServiceImpl;
import com.stc07.gubarkovag.springhelperclasses.Loggable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Loggable
public class EditUserServlet extends HttpServlet {
    private Logger logger;

    //private static UserService userService = new UserServiceImpl();
    @Autowired
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        try {
            user = userService.getById(Integer.valueOf(req.getParameter("id")));
        } catch (UserServiceImpl.UserServiceException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
        }
        req.setAttribute("user", user);
        req.getRequestDispatcher("/pages/users/edituser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(
                Integer.valueOf(req.getParameter("id")),
                req.getParameter("login"),
                req.getParameter("password"),
                User.Role.valueOf(req.getParameter("role").toUpperCase())
        );
        try {
            userService.update(user, user.getId());
        } catch (UserServiceImpl.UserServiceException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
        }

        resp.sendRedirect("/courseprojectweb/site/users");
    }
}
