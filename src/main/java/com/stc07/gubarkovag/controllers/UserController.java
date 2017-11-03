package com.stc07.gubarkovag.controllers;

import com.stc07.gubarkovag.pojo.User;
import com.stc07.gubarkovag.services.AuthorizationService;
import com.stc07.gubarkovag.services.UserService;
import com.stc07.gubarkovag.services.UserServiceImpl;
import com.stc07.gubarkovag.springhelperclasses.Loggable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Loggable
public class UserController {
    private Logger logger;

    private AuthorizationService as;
    private UserService userService;

    @Autowired
    public UserController(AuthorizationService as, UserService userService) {
        this.as = as;
        this.userService = userService;
    }

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/site/authorizeduser", method = RequestMethod.GET)
    public String processAuthorizedUser() {
        return "users/authorizeduser";
    }

    @RequestMapping(value = "/site/admin", method = RequestMethod.GET)
    public String processAdmin() {
        return "users/admin";
    }

    @RequestMapping(value = "/site/addUser", method = RequestMethod.GET)
    public String showAddUser(Model uiModel) {
        uiModel.addAttribute("userdata", new User());
        return "users/adduser";
    }

    @RequestMapping(value = "/site/addUser", method = RequestMethod.POST)
    public String processAddUser(@ModelAttribute User userdata, Model uiModel) {

        if (userdata.getLogin() == "" || userdata.getPassword() == "") {
            uiModel.addAttribute("wrongReg", "Логин и пароль не должны быть пустыми");
            return "users/adduser";
        }
        if (as.auth(userdata.getLogin(), userdata.getPassword()) != null) {
            uiModel.addAttribute("wrongReg", "Пользователь с такими данными уже существует");
            return "users/adduser";
        } else {
            try {
                userService.insertOne(new User(userdata.getLogin(), userdata.getPassword(), userdata.getRole()));
            } catch (UserServiceImpl.UserServiceException e) {
                logger.error(new StringBuilder()
                        .append(e.getMessage()).append(System.lineSeparator())
                        .append(e.getStackTrace()).toString());
            }
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/site/users", method = RequestMethod.GET)
    public String showUsers(Model uiModel) {
        try {
            uiModel.addAttribute("users", userService.getAll());
        } catch (UserServiceImpl.UserServiceException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
        }
        return "users/viewusers";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/site/editUser", method = RequestMethod.GET)
    public String editUser(@RequestParam(name = "id") String id, Model uiModel) {
        User user = null;
        try {
            user = userService.getById(Integer.valueOf(id));
        } catch (UserServiceImpl.UserServiceException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
        }
        uiModel.addAttribute("userdata", user);
        return "users/edituser";
    }

    @RequestMapping(value = "/site/editUser", method = RequestMethod.POST)
    public String confirmEditUser(@ModelAttribute User userdata) {
        User user = new User(
                Integer.valueOf(userdata.getId()),
                userdata.getLogin(),
                userdata.getPassword(),
                userdata.getRole()
        );

        try {
            userService.update(user, user.getId());
        } catch (UserServiceImpl.UserServiceException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
        }

        return "redirect:/site/users";
    }

    @RequestMapping(value = "/site/deleteUser", method = RequestMethod.GET)
    public String deleteUser(@RequestParam(name = "id") String id) {
        try {
            userService.deleteById(Integer.valueOf(id));
        } catch (UserServiceImpl.UserServiceException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
        }
        return "redirect:/site/users";
    }
}
