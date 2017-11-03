package com.stc07.gubarkovag;

import com.stc07.gubarkovag.db.dao.ApplicationDAOImpl;
import com.stc07.gubarkovag.db.dao.BookDAOImpl;
import com.stc07.gubarkovag.db.dao.UserDAOImpl;
import static com.stc07.gubarkovag.jaxbutilities.JAXBActions.*;

import com.stc07.gubarkovag.jaxbwrappers.Applications;
import com.stc07.gubarkovag.jaxbwrappers.Books;
import com.stc07.gubarkovag.jaxbwrappers.Users;
import com.stc07.gubarkovag.pojo.Application;
import com.stc07.gubarkovag.pojo.Book;
import com.stc07.gubarkovag.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    /*private static void clearDatabase() {
        try {
            ApplicationDAOImpl.deleteAll();
            UserDAOImpl.deleteAll();
            BookDAOImpl.deleteAll();
        } catch (UserDAOImpl.UserDAOException |
                 BookDAOImpl.BookDAOException |
                 ApplicationDAOImpl.ApplicationDAOException e) {
            e.printStackTrace();
        }
    }

    private static List<User> fillUsersListBeforeInsert() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            User user = new User();
            user.setId(i);
            user.setUsername("user" + i);
            user.setPassword("password" + i);
            user.setRole(i % 3 == 1 ? User.Role.ADMIN :
                    i % 3 == 2 ? User.Role.AUTHORIZEDUSER :
                            User.Role.NONAUTHORIZEDUSER);

            users.add(user);
        }

        return users;
    }

    private static List<Book> fillBooksListBeforeInsert() {
        List<Book> books = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            Book book = new Book();
            book.setId(i);
            book.setName("book" + i);
            book.setGenre("genre" + i);

            books.add(book);
        }

        return books;
    }

    private static List<Application> insertApplications(List<Integer> usersIds, List<Integer> booksIds) {
        class AppPrimaryKey {
            private int user_id;
            private int book_id;

            public AppPrimaryKey(int user_id, int book_id) {
                this.user_id = user_id;
                this.book_id = book_id;
            }

            public boolean isKeyUnique(List<AppPrimaryKey> appPrimaryKeysList) {
                for (AppPrimaryKey currentPrimaryKeys : appPrimaryKeysList) {
                    if (currentPrimaryKeys.equals(this))
                        return false;
                }

                return true;
            }

            @Override
            public boolean equals(Object obj) {
                if (!(obj.getClass() == AppPrimaryKey.class))
                    return false;

                if ((user_id == ((AppPrimaryKey)obj).user_id) &&
                        (book_id == ((AppPrimaryKey)obj).book_id))
                    return true;

                return false;
            }
        }// AppPrimaryKey

        List<Application> applications = new ArrayList<>();
        int user_id, book_id;
        List<AppPrimaryKey> appPrimaryKeysList = new ArrayList<>();
        Random r = new Random();
        for (int i = 1; i < 4; i++) {
            AppPrimaryKey appPrimaryKey;
            do {
                user_id = usersIds.get(r.nextInt(usersIds.size()));
                book_id = booksIds.get(r.nextInt(booksIds.size()));
                appPrimaryKey = new AppPrimaryKey(user_id, book_id);
            } while (!appPrimaryKey.isKeyUnique(appPrimaryKeysList));

            appPrimaryKeysList.add(appPrimaryKey);
            Application application = new Application();
            application.setUser_id(user_id);
            application.setBook_id(book_id);
            application.setStatus(i % 3 == 1 ? Application.Status.WAITING :
                    i % 3 == 2 ? Application.Status.APPROVED :
                            Application.Status.REJECTED);

            applications.add(application);
        }

        try {
            ApplicationDAOImpl.insertAll(applications);
            return applications;
        } catch (ApplicationDAOImpl.ApplicationDAOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }


    public static void main(String[] args) {
        List<User> users = fillUsersListBeforeInsert();
        List<Book> books = fillBooksListBeforeInsert();

        clearDatabase();

        //System.exit(1);

        try {
            UserDAOImpl.insertAll(users);
            BookDAOImpl.insertAll(books);
            //users = UserDAOImpl.getAll();
            //books = BookDAOImpl.getAll();
        } catch (UserDAOImpl.UserDAOException | BookDAOImpl.BookDAOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        List<Integer> usersIds = new ArrayList<>();
        for (final User user : users) {
            usersIds.add(user.getId());
        }
        List<Integer> booksIds = new ArrayList<>();
        for (final Book book : books) {
            booksIds.add(book.getId());
        }

        List<Application> applications = insertApplications(usersIds, booksIds);

        jaxbMarshalling(Users.class, "user.dat", new Users(users));
        jaxbMarshalling(Books.class, "book.dat", new Books(books));
        jaxbMarshalling(Applications.class, "application.dat", new Applications(applications));

        //System.exit(1);

        System.out.println();
        System.out.println();

        Users unMarshalledUsers = (Users)jaxbUnmarshalling(Users.class, "user.dat");
        Books unMarshalledBooks = (Books)jaxbUnmarshalling(Books.class, "book.dat");
        Applications unMarshalledApplications =
                (Applications)jaxbUnmarshalling(Applications.class, "application.dat");

        clearDatabase();

        *//*Lock lock = new ReentrantLock();
        Condition blockingPoolA = lock.newCondition();

        new Thread("UnmarshallUsers") {
            @Override
            public void run() {
                lock.lock();
                try {
                    blockingPoolA.await();
                    UserDAOImpl.insertAll(unMarshalledUsers.getUsers());
                } catch (UserDAOImpl.UserDAOException | InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }.start();

        new Thread("UnmarshallBooks") {
            @Override
            public void run() {
                try {
                    BookDAOImpl.insertAll(unMarshalledBooks.getBooks());
                } catch (BookDAOImpl.BookDAOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread("UnmarshallApplications") {
            @Override
            public void run() {
                try {
                    ApplicationDAOImpl.insertAll(unMarshalledApplications.getApplications());
                } catch (ApplicationDAOImpl.ApplicationDAOException e) {
                    e.printStackTrace();
                }
            }
        }.start();*//*

        try {
            UserDAOImpl.insertAll(unMarshalledUsers.getUsers());
            BookDAOImpl.insertAll(unMarshalledBooks.getBooks());
            ApplicationDAOImpl.insertAll(unMarshalledApplications.getApplications());
        } catch (UserDAOImpl.UserDAOException | BookDAOImpl.BookDAOException | ApplicationDAOImpl.ApplicationDAOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }*/
}
