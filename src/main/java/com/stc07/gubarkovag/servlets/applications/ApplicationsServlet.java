package com.stc07.gubarkovag.servlets.applications;

import com.stc07.gubarkovag.pojo.Book;
import com.stc07.gubarkovag.pojo.User;
import com.stc07.gubarkovag.services.ApplicationService;
import com.stc07.gubarkovag.services.ApplicationServiceImpl;
import com.stc07.gubarkovag.services.BookService;
import com.stc07.gubarkovag.services.BookServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ApplicationsServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ApplicationsServlet.class);

    private static ApplicationService applicationService = new ApplicationServiceImpl();
    private static BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI();
        String action = requestPath.substring(requestPath.lastIndexOf('/') + 1, requestPath.length());

        Object userAttribute = req.getSession().getAttribute("user");
        User user = userAttribute != null ? ((User)userAttribute) : null;
        int userId = user != null ? user.getId() : 0;
        /*User user = (User) (req.getSession().getAttribute("user"));
        int userId = user.getId();*/

        List<Book> books = null;

        try {
            switch(action) {
                case "viewApprovedApplications" :
                    books = bookService.getAppsByStatus("APPROVED");
                    req.setAttribute("applications", books);
                    req.getRequestDispatcher("/pages/applications/viewapprovedapplications.jsp").forward(req, resp);
                    break;
                case "viewRejectedApplications" : {
                    books = bookService.getAppsByStatusAndUser("REJECTED", userId);
                    req.setAttribute("applications", books);
                    req.getRequestDispatcher("/pages/applications/viewrejectedapplications.jsp").forward(req, resp);
                    break;
                }
                case "sendRejectedApplications" : {
                    applicationService.putRejectedToWaitingStatus(userId);
                    resp.sendRedirect("/courseprojectweb/" + user.getRole().toString().toLowerCase());
                    break;
                }
                case "adminViewApplications" :
                    books = bookService.getAppsByStatus("WAITING");
                    req.setAttribute("applications", books);
                    req.getRequestDispatcher("/pages/applications/adminviewapplications.jsp").forward(req, resp);
                    break;
                case "approveApplication" :
                    applicationService.update("APPROVED", Integer.valueOf(req.getParameter("id")));
                    resp.sendRedirect("/courseprojectweb/adminViewApplications");
                    break;
                case "rejectApplication" :
                    applicationService.update("REJECTED", Integer.valueOf(req.getParameter("id")));
                    resp.sendRedirect("/courseprojectweb/adminViewApplications");
                    break;
            }
        } catch (ApplicationServiceImpl.ApplicationServiceException | BookServiceImpl.BookServiceException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
        }
    }
}
