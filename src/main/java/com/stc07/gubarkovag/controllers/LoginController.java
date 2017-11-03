package com.stc07.gubarkovag.controllers;

import com.stc07.gubarkovag.pojo.User;
import com.stc07.gubarkovag.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@SessionAttributes(value = "user", types = User.class)
public class LoginController {
    private AuthorizationService as;

    @Autowired
    public LoginController(AuthorizationService as) {
        this.as = as;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showLogin(HttpServletRequest req, Authentication authentication, Model uiModel) {
        //req.getSession().removeAttribute("user");

        if (authentication != null) {
            Map<Boolean, User> authInfo;
            String login = authentication.getName();
            String password = (String)authentication.getCredentials();

            if ((authInfo = as.auth(login, password)) != null) {
                User user = authInfo.get(true);
                uiModel.addAttribute("user", user);

                return "redirect:/site/" + authInfo.get(true).getRole().toString().toLowerCase();
            } else {
                /*if (login == "" || password == "")
                    uiModel.addAttribute("wrongAuth", "Логин и пароль не должны быть пустыми");
                else
                    uiModel.addAttribute("wrongAuth", "Пользователь с такими данными не существует");*/
                uiModel.addAttribute("authInfo", "Введите данные пользователя");
                return "login";
            }
        } else {
            uiModel.addAttribute("authInfo", "Введите данные пользователя");
            return "login";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String processAuth(@RequestParam(name = "login", required = true) String login,
                              @RequestParam(name = "password", required = true) String password,
                              Model uiModel) {

        Map<Boolean, User> authInfo;
        if ((authInfo = as.auth(login, password)) != null) {
            User user = authInfo.get(true);
            uiModel.addAttribute("user", user);

            return "redirect:/site/" + authInfo.get(true).getRole().toString().toLowerCase();
        } else {
            if (login == "" || password == "")
                uiModel.addAttribute("wrongAuth", "Логин и пароль не должны быть пустыми");
            else
                uiModel.addAttribute("wrongAuth", "Пользователь с такими данными не существует");
            return "login";
        }
    }
}
