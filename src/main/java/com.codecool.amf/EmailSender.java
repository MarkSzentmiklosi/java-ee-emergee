//package com.codecool.amf;
//
//import com.codecool.amf.model.HelpRequest;
//import org.apache.commons.lang3.text.StrSubstitutor;
//
//import javax.mail.Message;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//import java.util.logging.Logger;
//
//public class EmailSender {
//    static final Logger logger = Logger.getLogger(EmailSender.class+"");
//
//    public void send(String to, String sub, String msg, String service) throws javax.mail.MessagingException {
//        //Get properties object
//        String from = "amf.emergee@gmail.com";
//        String password = "amfemergee123";
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        //get Session
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(from, password);
//                    }
//                });
//        //compose message
//        MimeMessage message = new MimeMessage(session);
//        message.setFrom(from);
//        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//        message.setSubject(sub);
//        message.setText(msg);
//        //send message
//        Transport.send(message);
//        logger.info("Email was sent to: " + to);
//
//    }
//
//    public String createMsg(HelpRequest helpRequest) {
//        String template = "Dear ${partnerName},\n" +
//                "\n" +
//                "We have a new request for you.\n\n" +
//                "Our client ${clientName} need your help at:\n${location}.\n\n" +
//                "Please get in touch with the client on this number:\n${clientPhone}.\n" +
//                "\n" +
//                "Best regard,\n" +
//                "AMF Team";
//
//        Map<String, String> data = new HashMap<String, String>();
//        data.put("partnerName", helpRequest.getPartner().getName());
//        data.put("clientName", helpRequest.getUser().getName());
//        data.put("location", helpRequest.getLocationLabel());
//        data.put("clientPhone", helpRequest.getUser().getPhoneNumber());
//
//        String formattedMsg = StrSubstitutor.replace(template, data);
//
//        return formattedMsg;
//    }
//    public String createConfirmationMessage(HelpRequest helpRequest) {
//        String template = "Dear ${userName},\n" +
//                "\n" +
//                "We would like to let you know that we notified our partner.\n\n" +
//                "${partnerName} will contact you soon.\n\n" +
//                "Best regard,\n" +
//                "AMF Team";
//
//        Map<String, String> data = new HashMap<String, String>();
//        data.put("userName", helpRequest.getUser().getName());
//        data.put("partnerName", helpRequest.getPartner().getName());
//
//        String formattedMsg = StrSubstitutor.replace(template, data);
//
//        return formattedMsg;
//    }
//}
