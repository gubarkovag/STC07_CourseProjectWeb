package com.stc07.gubarkovag.controllersjpa;

import com.stc07.gubarkovag.db.entities.User;
import com.stc07.gubarkovag.servicesjpa.UserServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value = "user", types = User.class)
public class LoginController {
    private UserServiceJPA userServiceJPA;

    @Autowired
    public LoginController(UserServiceJPA userServiceJPA) {
        this.userServiceJPA = userServiceJPA;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showLogin(Authentication authentication, Model uiModel) {
        if (authentication != null) {
            String login = authentication.getName();
            String password = (String)authentication.getCredentials();

            User user = userServiceJPA.getUserByLoginAndPassword(login, password);
            uiModel.addAttribute("user", user);

            return "redirect:/site/" + String.valueOf(user.getRole());
        } else {
            uiModel.addAttribute("authInfo", "Введите данные пользователя");
            return "login";
        }
    }
}
