package com.stc07.gubarkovag.controllers;

import com.stc07.gubarkovag.pojo.Application;
import com.stc07.gubarkovag.pojo.Book;
import com.stc07.gubarkovag.pojo.User;
import com.stc07.gubarkovag.services.ApplicationService;
import com.stc07.gubarkovag.services.ApplicationServiceImpl;
import com.stc07.gubarkovag.services.BookService;
import com.stc07.gubarkovag.services.BookServiceImpl;
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

    private ApplicationService applicationService;
    private BookService bookService;

    @Autowired
    public ApplicationController(ApplicationService applicationService, BookService bookService) {
        this.applicationService = applicationService;
        this.bookService = bookService;
    }

    @RequestMapping(value = "/site/adminViewApplications", method = RequestMethod.GET)
    public String adminViewApplications(Model uiModel) {
        try {
            uiModel.addAttribute("applications", bookService.getAppsByStatus("WAITING"));
        } catch (BookServiceImpl.BookServiceException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
        }

        return "applications/adminviewapplications";
    }

    @RequestMapping(value = "/site/approveApplication", method = RequestMethod.GET)
    public String approveApplication(@RequestParam(name = "id") String id) {
        try {
            applicationService.update("APPROVED", Integer.valueOf(id));
        } catch (ApplicationServiceImpl.ApplicationServiceException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
        }

        return "redirect:/site/adminViewApplications";
    }

    @RequestMapping(value = "/site/rejectApplication", method = RequestMethod.GET)
    public String rejectApplication(@RequestParam(name = "id") String id) {
        try {
            applicationService.update("REJECTED", Integer.valueOf(id));
        } catch (ApplicationServiceImpl.ApplicationServiceException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
        }

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
        int userId = user.getId();

        Book book = new Book(
                userId,
                bookdata.getName(),
                bookdata.getGenre()
        );

        try {
            bookService.insertOne(book);
            int bookId = bookService.findMaxId();
            Application application = new Application(userId, bookId, Application.Status.WAITING);
            applicationService.insertOne(application);
        } catch (BookServiceImpl.BookServiceException | ApplicationServiceImpl.ApplicationServiceException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
        }

        return "redirect:/site/" + String.valueOf(user.getRole()).toLowerCase();
    }

    @RequestMapping(value = "/site/viewRejectedApplications", method = RequestMethod.GET)
    public String viewRejectedApplications(Model uiModel, @ModelAttribute User user) {
        int userId = user.getId();

        try {
            uiModel.addAttribute("applications", bookService.getAppsByStatusAndUser("REJECTED", userId));
        } catch (BookServiceImpl.BookServiceException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
        }

        return "applications/viewrejectedapplications";
    }

    @RequestMapping(value = "/site/sendRejectedApplications", method = RequestMethod.GET)
    public String sendRejectedApplications(@ModelAttribute User user) {
        int userId = user.getId();

        try {
            applicationService.putRejectedToWaitingStatus(userId);
        } catch (ApplicationServiceImpl.ApplicationServiceException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
        }

        return "redirect:/site/" + user.getRole().toString().toLowerCase();
    }

    @RequestMapping(value = "/viewApprovedApplications", method = RequestMethod.GET)
    public String viewApprovedApplications(Model uiModel) {
        try {
            uiModel.addAttribute("applications", bookService.getAppsByStatus("APPROVED"));
        } catch (BookServiceImpl.BookServiceException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
        }

        return "applications/viewapprovedapplications";
    }
}
