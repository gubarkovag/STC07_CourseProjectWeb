package com.stc07.gubarkovag.logandmailutils;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailSender {
    private static final Logger logger = Logger.getLogger(MailSender.class);

    public static void sendEmail(String login) {
        Properties props = new Properties();
        try {
            InputStream inputStream =
                    MailSender.class.getClassLoader().getResourceAsStream("mailsender.properties");
            props.load(inputStream);
        } catch (IOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString());
        }

        Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                            "alexey.gubarkov@gmail.com", "alexey1030");
                }
            }
        );

        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress("a.gubarkov.stc@innopolis.ru"));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("a.gubarkov.stc@innopolis.ru"));
            message.setSubject("Authentication info");
            message.setText("User with login " + login + " authenticated");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
