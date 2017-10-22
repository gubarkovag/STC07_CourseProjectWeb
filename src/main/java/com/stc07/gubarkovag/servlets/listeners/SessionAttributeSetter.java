package com.stc07.gubarkovag.servlets.listeners;

import com.stc07.gubarkovag.logandmailutils.MailSender;
import com.stc07.gubarkovag.pojo.User;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionAttributeSetter implements HttpSessionAttributeListener {
    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        if ("user".equals(se.getName()))
            MailSender.sendEmail(((User)se.getValue()).getLogin());
    }
}
