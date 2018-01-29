package com.stc07.gubarkovag.controllersspringdatajpa;

import com.stc07.gubarkovag.db.entities.User;
import com.stc07.gubarkovag.servicesspringdatajpa.UserService;
import com.stc07.gubarkovag.springhelperclasses.Loggable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/site/ROLE_USER", method = RequestMethod.GET)
    public String processAuthorizedUser() {
        return "users/authorizeduser";
    }

    @RequestMapping(value = "/site/ROLE_ADMIN", method = RequestMethod.GET)
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
        } else {
            userService.save(new User(userdata.getLogin(), userdata.getPassword(), userdata.getRole()));
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/site/users", method = RequestMethod.GET)
    public String showUsers(Model uiModel) {
        uiModel.addAttribute("users", userService.getAll());
        return "users/viewusers";
    }

    @RequestMapping(value = "/site/editUser", method = RequestMethod.GET)
    public String editUser(@RequestParam(name = "id") String id, Model uiModel) {
        User user = userService.getById(Long.valueOf(id));

        uiModel.addAttribute("userdata", user);
        return "users/edituser";
    }

    @RequestMapping(value = "/site/editUser", method = RequestMethod.POST)
    public String confirmEditUser(@ModelAttribute User userdata) {
        User user = new User(
                Long.valueOf(userdata.getId()),
                userdata.getLogin(),
                userdata.getPassword(),
                userdata.getRole()
        );

        userService.save(user);

        return "redirect:/site/users";
    }

    @RequestMapping(value = "/site/deleteUser", method = RequestMethod.GET)
    public String deleteUser(@RequestParam(name = "id") String id) {
        userService.deleteById(Long.valueOf(id));

        return "redirect:/site/users";
    }
}
