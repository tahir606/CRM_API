package com.example.CRM.Email.EmailStore;

import com.example.CRM.JCode.EmailDBHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.FlagTerm;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Properties;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailSystem {
    private static final String host = "smtp.gmail.com";
    private static final String mailStoreType = "imaps";
    private static final String username = "isko20951@gmail.com";
    private static final String password = "khan2323";
    private static String ATTACH = "";
    private final EmailRepository emailRepository;
    private static final Logger log = LoggerFactory.getLogger(EmailSystem.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public EmailSystem(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public void sendmail(EmailTickets emailTickets) throws AddressException, MessagingException, IOException {
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
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTickets.getToAddress()));
        msg.setSubject(emailTickets.getSubject());
        msg.setSentDate(new Date(2020 - 8 - 8));

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(emailTickets.getBody(), "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        MimeBodyPart attachPart = new MimeBodyPart();
        attachPart.attachFile(emailTickets.getAttachment());
        multipart.addBodyPart(attachPart);

        msg.setContent(multipart);

        Transport.send(msg);
    }

    @Scheduled(fixedRate = 5000)
    public String receiveEmail() {
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
                System.out.println(i);
                System.out.println("Email subject:" + message.getSubject());
                storeFile(message);

                message.setFlag(Flags.Flag.SEEN, true);
            }
            emailFolder.close(false);
            emailStore.close();
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void storeFile(Message message) throws MessagingException, IOException {
        EmailTickets emailTickets = new EmailTickets();
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        try {
            System.out.println(dateFormat.format(message.getSentDate()));
        } catch (Exception e) {
            System.out.println(e);
        }

        emailTickets.setMessageNo(message.getMessageNumber());
        try {
            emailTickets.setTimesTamp(dateFormat.format(message.getSentDate()).substring(1));
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        emailTickets.setFromAddress(getAddressString(fromAddress));
        emailTickets.setToAddress(getAddressString(toAddress));
        emailTickets.setCcAddress(getAddressString(ccAddress));
        emailTickets.setSubject(subject);
        emailTickets.setBody(result);
        emailTickets.setAttachment(ATTACH);
        emailTickets.setLocked(0);
        emailTickets.setFreeze(1);

        EmailDBHandler emailDBHandler = new EmailDBHandler(emailRepository);
        emailDBHandler.emailDataInsert(emailTickets);
        autoReply(emailTickets);
    }
    public static String autoReplySubject = "Burhani Customer Support - Ticket Number: ";
    public  static String autoText= "Thank you for contacting Burhani Customer Service.";
    private void autoReply(EmailTickets emailTickets) throws IOException, MessagingException {
        System.out.println("auto text:"+autoText);
        String body = "<h3>The Ticket Number Issued to you is: <b>" + emailTickets.getCode() + "</b></h3>\n " + autoText;

        EmailTickets e = new EmailTickets();
        e.setSubject(autoReplySubject +emailTickets.getCode());
        e.setToAddress(emailTickets.getFromAddress());
        e.setBody(body + "<br><br><br>" + "--------In Reply To--------" + "<br><br><h4>Subject:   <b>" + emailTickets.getSubject() + "</b></h4><br><br>" + emailTickets.getBody());
        e.setAttachment(ATTACH);
        sendmail(e);
    }



    private static String parseMultipart(MimeMultipart mimeMultipart, Address[] fromAddress) throws MessagingException, IOException {
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
                String pathFile = "D:\\work\\FilesApi";

                createDirectoryIfDoesNotExist(pathFile + "\\" + folderName + "\\");

                String filename = pathFile + "\\" + folderName + "\\" + part.getFileName();

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
