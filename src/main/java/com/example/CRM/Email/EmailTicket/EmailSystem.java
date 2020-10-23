package com.example.CRM.Email.EmailTicket;

import com.example.CRM.Email.Email;
import com.example.CRM.Email.EmailGeneral.EmailGeneral;
import com.example.CRM.Email.EmailGeneral.EmailGeneralRepository;
import com.example.CRM.Email.EmailSent.EmailSent;
import com.example.CRM.Email.EmailSent.EmailSentRepository;
import com.example.CRM.Email.Setiings.EmailSettings;
import com.example.CRM.JCode.EmailDBHandler;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.FlagTerm;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailSystem {
    private static final String mailStoreType = "imaps";
    private static String ATTACH = "";
    private final EmailDBHandler emailDBHandler;

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private static final Logger log = LoggerFactory.getLogger(EmailSystem.class);

    public EmailSystem(EmailDBHandler emailDBHandler) {
        this.emailDBHandler = emailDBHandler;
    }


    public void sendEmail(Email email) throws AddressException, MessagingException, IOException {
        EmailSettings emailSettings = emailDBHandler.getSettings();
        String username = emailSettings.getEmail();
        String password = emailSettings.getPassword();
        String host = emailSettings.getHost();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(username, false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getToAddress()));
        msg.setSubject(email.getSubject());
        msg.setSentDate(email.getTimestamp());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        String body = email.getBody();
        messageBodyPart.setText(body, "utf-8", "html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        if (email.getAttachment() == null || email.getAttachment().equals("")) {
        } else {
            List<File> newFileList = new ArrayList<>();
            for (String c : email.getAttachment().split("\\^")) {
                newFileList.add(new File(c));
            }
            if (newFileList == null) {
            } else if (!(newFileList.size() < 0)) {
                // Part two is attachment
                for (File f : newFileList) {
                    MimeBodyPart attachment = new MimeBodyPart();
                    if (f.exists()) {
//                        String getFile = "C:\\Users\\BitS\\Documents\\" + f.getName();
                        DataSource source = new FileDataSource(getPath("send",f.getName()));
                        attachment.setDataHandler(new DataHandler(source));
                        attachment.setFileName(f.getName());
                        multipart.addBodyPart(attachment);
                    } else {
                    }
                }
            }
        }
        msg.setContent(multipart);
        new Thread(() -> {
            try {
                Transport.send(msg);
                log.info("  --Timestamp-- " + timestamp + "  Sent E-Mail to: " + email.getToAddress());
                emailDBHandler.insertEmail(email);
            } catch (MessagingException ex) {
                ex.printStackTrace();
                EmailSent emailSent = (EmailSent) email;
                emailSent.setSent(0);
                emailDBHandler.insertEmail(emailSent);

            }
        }).start();

    }

    @Scheduled(fixedRate = 5000)
    public String receiveEmail() {
        EmailSettings emailSettings = emailDBHandler.getSettings();
        String username = emailSettings.getEmail();
        String password = emailSettings.getPassword();
        String host = emailSettings.getHost();
        try {
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");

            Session emailSession = Session.getDefaultInstance(properties,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            Store emailStore = emailSession.getStore(mailStoreType);
            emailStore.connect(host, username, password);

            Folder emailFolder = emailStore.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);

            FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);

            Message[] messages = emailFolder.search(ft);
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                log.info("  --Timestamp-- " + timestamp + "   Email subject:" + message.getSubject());
                int tix = 1;    //2 means ticket 1 means general

                for (String t : emailDBHandler.whiteListDomain(1)) {
                    for (Address e : message.getFrom()) {
                        if (e.toString().contains(t)) {
                            tix = 2;
                            break;
                        }
                    }
                }

                if (tix == 2) {
                    Email emailTickets = setEmail(message, EmailTickets.class);
                    emailDBHandler.insertEmail(emailTickets);
                    autoReplyTicket(emailTickets);
                } else {
                    Email emailGeneral = setEmail(message, EmailGeneral.class);
                    emailDBHandler.insertEmail(emailGeneral);
                }
                message.setFlag(Flags.Flag.SEEN, true);
            }
            emailFolder.close(false);
            emailStore.close();
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public Email setEmail(Message message, Class className) throws MessagingException, IOException {
        Email email = null;
        if (className == EmailTickets.class) {
            email = new EmailTickets();
        } else if (className == EmailGeneral.class) {
            email = new EmailGeneral();
        }
        String result = "";
        String subject;
        ATTACH = "";

        Address[] fromAddress = message.getFrom();
        Address[] toAddress = message.getRecipients(Message.RecipientType.TO);
        Address[] ccAddress = message.getRecipients(Message.RecipientType.CC);

        subject = message.getSubject();
        if (subject == null) {
            subject = "";
        }

        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            result = parseMultipart((MimeMultipart) message.getContent(), fromAddress);
        }

        email.setMessageNo(message.getMessageNumber());

        try {
            Timestamp timestamp = new Timestamp(message.getSentDate().getTime());
            email.setTimestamp(timestamp);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        email.setFromAddress(getAddressString(fromAddress));
        email.setToAddress(getAddressString(toAddress));
        email.setCcAddress(getAddressString(ccAddress));
        email.setSubject(subject);
        email.setBody(result);
        email.setAttachment(ATTACH);
        email.setFreeze(0);


        return email;
    }

    public boolean solveResponder(Email email) throws IOException, MessagingException {
        String autoReplySolvedSubject = "Ticket Number: " + email.getCode() + " Resolved";
        String bodySolved =
                "<br><br><br>------------------- Ticket " + email.getCode() + " -------------------" +
                        "<br><br>Timestamp:     <b>" + email.getTimestamp() + "</b>" +
                        "<br><br>Subject:       <b>" + email.getSubject() + "</b>" +
                        "<br><br>" + email.getBody();
        EmailSent e = new EmailSent();
        e.setSubject(autoReplySolvedSubject);
        e.setBody(bodySolved);
        e.setToAddress(email.getFromAddress());
        sendEmail(e);
        return true;
    }

    public boolean autoReplyTicket(Email email) throws IOException, MessagingException {
        EmailSettings emailSettings = emailDBHandler.getSettings();
        String autoReplySubject = "Burhani Customer Support - Ticket Number: ";
        String bodyReply = "<h3>The Ticket Number Issued to you is: <b>" + email.getCode() + "</b></h3> "
                + emailSettings.getAutoText() + "<br><br><br>" + "--------In Reply To--------" +
                "<br><br><h4>Subject:   <b>" + email.getSubject() + "</b></h4><br><br>" + email.getBody();

        EmailSent e = new EmailSent();
        e.setSubject(autoReplySubject + email.getCode());
        e.setBody(bodyReply);
        e.setToAddress(email.getFromAddress());
        e.setTimestamp(email.getTimestamp());
        e.setAttachment(email.getAttachment());
        sendEmail(e);

        return true;
    }


    private String parseMultipart(MimeMultipart mimeMultipart, Address[] fromAddress) throws MessagingException, IOException {
        EmailSettings emailSettings = emailDBHandler.getSettings();
        String pathFile = emailSettings.getFilePath();
        String result = "";
        int numberOfParts = mimeMultipart.getCount();
        for (int partcounts = 0; partcounts < numberOfParts; partcounts++) {

            MimeBodyPart part = (MimeBodyPart) mimeMultipart.getBodyPart(partcounts);
            if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition()) || Part.INLINE.equalsIgnoreCase(part.getDisposition())) {
                //Add Files in a folder created specifically for that email
                String folderName = "";
                try {
                    folderName = fromAddress[0].toString().trim().split("<")[1].replace(">", "");
                } catch (Exception e) {
                    folderName = "Others";
                }
                createDirectoryIfDoesNotExist(pathFile + "\\" + folderName + "\\");

                String filename = pathFile + "\\"  +folderName + "\\" + part.getFileName();

                ATTACH += filename + "^";  //AttachFiles string is to be inserted into Database

                part.saveFile(filename);
            } else if (part.isMimeType("text/plain")) {
                result = result + "\n" + part.getContent();
            } else if (part.isMimeType("text/html")) {
                result = (String) part.getContent();
            } else if (part.isMimeType("multipart/*")) {
                result = parseMultipart((MimeMultipart) part.getContent(), fromAddress);
            }
        }
        return result;
    }



    public String getPath( String type,String name) {
        EmailSettings emailSettings = emailDBHandler.getSettings();
        String pathFile = emailSettings.getFilePath();
        createDirectoryIfDoesNotExist(pathFile + "\\" + type + "\\");
        return pathFile + "\\" + type + "\\"  + name;
    }

    private static void createDirectoryIfDoesNotExist(String s) {
        File directory = new File(s);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private static String getAddressString(Address[] addresses) {
        String s = "";
        if (addresses == null)
            return s;
        for (Address ad : addresses) {
            if (ad != null)
                s = s + "^" + ad;
        }
        return s;
    }

}
