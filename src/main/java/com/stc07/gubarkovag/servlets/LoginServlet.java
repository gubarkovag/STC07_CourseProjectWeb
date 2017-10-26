package com.stc07.gubarkovag.servlets;

import com.stc07.gubarkovag.logandmailutils.MailSender;
import com.stc07.gubarkovag.pojo.User;
import com.stc07.gubarkovag.services.AuthorizationService;
import com.stc07.gubarkovag.services.AuthorizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    //private static AuthorizationService as = new AuthorizationServiceImpl();
    @Autowired
    private AuthorizationService as;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Map<Boolean, User> authInfo;
        if ((authInfo = as.auth(login, password)) != null) {
            //MailSender.sendEmail(login);
            //req.getSession().setAttribute("isAuth", true);

            User user = authInfo.get(true);

            req.getSession().setAttribute("user", user);

            resp.sendRedirect("/courseprojectweb/site/" + authInfo.get(true).getRole().toString().toLowerCase());
        } else {
            if (login == "" || password == "")
                req.setAttribute("wrongAuth", "Логин и пароль не должны быть пустыми");
            else
                req.setAttribute("wrongAuth", "Пользователь с такими данными не существует");
            req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }
    }
}
