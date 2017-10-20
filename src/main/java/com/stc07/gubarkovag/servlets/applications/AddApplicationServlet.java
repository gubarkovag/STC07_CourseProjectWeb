package com.stc07.gubarkovag.servlets.applications;

import com.stc07.gubarkovag.pojo.Application;
import com.stc07.gubarkovag.pojo.Book;
import com.stc07.gubarkovag.pojo.User;
import com.stc07.gubarkovag.services.ApplicationService;
import com.stc07.gubarkovag.services.ApplicationServiceImpl;
import com.stc07.gubarkovag.services.BookService;
import com.stc07.gubarkovag.services.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddApplicationServlet extends HttpServlet {
    private static BookService bookService = new BookServiceImpl();
    private static ApplicationService applicationService = new ApplicationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/applications/addapplication.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //User user = (User)(getServletContext().getAttribute("user"));
        User user = (User)(req.getSession().getAttribute("user"));
        int userId = user.getId();

        Book book = new Book(
                userId,
                req.getParameter("name"),
                req.getParameter("genre")
        );
        try {
            bookService.insertOne(book);
            int bookId = bookService.findMaxId();
            Application application = new Application(userId, bookId, Application.Status.WAITING);
            applicationService.insertOne(application);
        } catch (BookServiceImpl.BookServiceException |
                ApplicationServiceImpl.ApplicationServiceException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/site/courseprojectweb/" + String.valueOf(user.getRole()).toLowerCase());
    }
}
