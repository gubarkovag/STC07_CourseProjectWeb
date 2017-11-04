package com.stc07.gubarkovag.controllersjpa;

import com.stc07.gubarkovag.db.entities.Application;
import com.stc07.gubarkovag.db.entities.Book;
import com.stc07.gubarkovag.db.entities.User;
import com.stc07.gubarkovag.servicesjpa.ApplicationServiceJPA;
import com.stc07.gubarkovag.servicesjpa.BookServiceJPA;
import com.stc07.gubarkovag.springhelperclasses.Loggable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes(value = "user", types = User.class)
@Loggable
public class ApplicationController {
    private Logger logger;

    private ApplicationServiceJPA applicationServiceJPA;
    private BookServiceJPA bookServiceJPA;

    @Autowired
    public ApplicationController(ApplicationServiceJPA applicationServiceJPA, BookServiceJPA bookServiceJPA) {
        this.applicationServiceJPA = applicationServiceJPA;
        this.bookServiceJPA = bookServiceJPA;
    }

    @RequestMapping(value = "/site/adminViewApplications", method = RequestMethod.GET)
    public String adminViewApplications(Model uiModel) {
        uiModel.addAttribute("applications",
                bookServiceJPA.getAppsByStatus(Application.Status.valueOf("WAITING")));

        return "applications/adminviewapplications";
    }

    @RequestMapping(value = "/site/approveApplication", method = RequestMethod.GET)
    public String approveApplication(@RequestParam(name = "id") String id) {
        Book book = bookServiceJPA.getById(Long.valueOf(id));
        Application application = applicationServiceJPA.getByBookId(book);
        application.setStatus(Application.Status.APPROVED);
        applicationServiceJPA.save(application);

        return "redirect:/site/adminViewApplications";
    }

    @RequestMapping(value = "/site/rejectApplication", method = RequestMethod.GET)
    public String rejectApplication(@RequestParam(name = "id") String id) {
        Book book = bookServiceJPA.getById(Long.valueOf(id));
        Application application = applicationServiceJPA.getByBookId(book);
        application.setStatus(Application.Status.REJECTED);
        applicationServiceJPA.save(application);

        return "redirect:/site/adminViewApplications";
    }

    @RequestMapping(value = "/site/addApplication", method = RequestMethod.GET)
    public String showAddApplication(Model uiModel) {
        uiModel.addAttribute("bookdata", new Book());
        return "applications/addapplication";
    }

    @RequestMapping(value = "/site/addApplication", method = RequestMethod.POST)
    public String processAddApplication(@ModelAttribute Book bookdata,
                                        @ModelAttribute User user) {

        Book book = new Book(
                user,
                bookdata.getName(),
                bookdata.getGenre()
        );

        bookServiceJPA.save(book);
        Application application = new Application(user, book, Application.Status.WAITING);
        applicationServiceJPA.save(application);

        return "redirect:/site/" + String.valueOf(user.getRole());
    }

    @RequestMapping(value = "/site/viewRejectedApplications", method = RequestMethod.GET)
    public String viewRejectedApplications(Model uiModel, @ModelAttribute User user) {

        uiModel.addAttribute("applications",
                bookServiceJPA.getAppsByStatusAndUser(Application.Status.valueOf("REJECTED"), user));

        return "applications/viewrejectedapplications";
    }

    @RequestMapping(value = "/site/sendRejectedApplications", method = RequestMethod.GET)
    public String sendRejectedApplications(@ModelAttribute User user) {

        applicationServiceJPA.putRejectedToWaitingStatus(user);

        return "redirect:/site/" + String.valueOf(user.getRole());
    }

    @RequestMapping(value = "/viewApprovedApplications", method = RequestMethod.GET)
    public String viewApprovedApplications(Model uiModel) {
            uiModel.addAttribute("applications",
                    bookServiceJPA.getAppsByStatus(Application.Status.APPROVED));

        return "applications/viewapprovedapplications";
    }
}
