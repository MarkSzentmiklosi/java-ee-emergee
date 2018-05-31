package com.codecool.amf;

import com.codecool.amf.model.HRequest;
import org.apache.commons.text.StrSubstitutor;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EmailSender {

    public static void send(String to, String sub, String msg) throws javax.mail.MessagingException {
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
        System.out.println("message sent successfully");

    }

    public static String createMsg(HRequest hRequest) {
        String template = "Dear ${partnerName},\n" +
                "\n" +
                "We have a new request for you.\n" +
                "Our client ${clientName} need your help at ${location}\n" +
                "Please get in touch with the client on this number ${clientPhone}.\n" +
                "\n" +
                "Best regard,\n" +
                "AMF Team";

        Map<String, String> data = new HashMap<String, String>();
        data.put("partnerName", hRequest.getPartner().getName());
        data.put("clientName", hRequest.getUser().getName());
        data.put("location", hRequest.getLocationLabel());
        data.put("clientPhone", hRequest.getUser().getPhoneNumber());

        String formattedMsg = StrSubstitutor.replace(template, data);

        return formattedMsg;
    }
}
