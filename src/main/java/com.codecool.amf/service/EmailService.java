package com.codecool.amf.service;

import com.codecool.amf.model.HelpRequest;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

@Service
public class EmailService {

    Logger logger = Logger.getLogger(EmailService.class + "");

    private void send(String to, String sub, String msg, String service) throws javax.mail.MessagingException {
        //Get properties object
        String from = "amf.emergee@gmail.com";
        String password = "amfemergee123";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        //get Session
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
        //compose message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(from);
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(sub);
        message.setText(msg);
        //send message
        Transport.send(message);
        logger.info("Email was sent to: " + to);

    }

    private String createMsg(HelpRequest helpRequest) {
        String template = "Dear ${partnerName},\n" +
                "\n" +
                "We have a new request for you.\n\n" +
                "Our client ${clientName} need your help at:\n${location}.\n\n" +
                "Please get in touch with the client on this number:\n${clientPhone}.\n" +
                "\n" +
                "Best regard,\n" +
                "AMF Team";

        Map<String, String> data = new HashMap<String, String>();
        data.put("partnerName", helpRequest.getPartner().getName());
        data.put("clientName", helpRequest.getUser().getName());
        data.put("location", helpRequest.getLocationLabel());
        data.put("clientPhone", helpRequest.getUser().getPhoneNumber());

        String formattedMsg = StrSubstitutor.replace(template, data);

        return formattedMsg;
    }

    private String createConfirmationMessage(HelpRequest helpRequest) {
        String template = "Dear ${userName},\n" +
                "\n" +
                "We would like to let you know that we notified our partner.\n\n" +
                "${partnerName} will contact you soon.\n\n" +
                "Best regard,\n" +
                "AMF Team";

        Map<String, String> data = new HashMap<String, String>();
        data.put("userName", helpRequest.getUser().getName());
        data.put("partnerName", helpRequest.getPartner().getName());

        String formattedMsg = StrSubstitutor.replace(template, data);

        return formattedMsg;
    }

    public void sendConfirmationForUser(String serviceType, HelpRequest helpRequest) {
        String message = createConfirmationMessage(helpRequest);
        try {
            String subject = "Request " + helpRequest.getCreationDate() + " confirmation";
            String userEmail = helpRequest.getUser().getEmail();
            send(userEmail, subject, message, serviceType);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void notifyPartner(String service, HelpRequest helpRequest) {
        String msg = createMsg(helpRequest);

        try {
            String subject = "Request " + helpRequest.getCreationDate();
            String partnerEmail = helpRequest.getPartner().getEmail();
            String serviceType = service;

            send(partnerEmail, subject, msg, serviceType);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
