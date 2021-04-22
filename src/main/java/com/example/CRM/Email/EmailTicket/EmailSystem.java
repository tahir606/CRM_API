package com.example.CRM.Email.EmailTicket;

import com.example.CRM.Domain.Domain;
import com.example.CRM.Email.Email;
import com.example.CRM.Email.EmailGeneral.EmailGeneral;
import com.example.CRM.Email.EmailList.EmailList;
import com.example.CRM.Email.EmailSent.EmailSent;
import com.example.CRM.Email.History.TicketHistory;
import com.example.CRM.Email.Setiings.EmailSettings;
import com.example.CRM.JCode.EmailDBHandler;
import com.example.CRM.attachmentProperty.UploadFileResponse;
import com.example.CRM.keyword.KeywordController;
import org.omg.CosNaming.NamingContextExtPackage.InvalidAddress;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.FlagTerm;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;


import static com.example.CRM.Email.EmailTicket.Status.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class EmailSystem {

    private static List<String> ATTACH = new ArrayList<>();
    private final EmailDBHandler emailDBHandler;
    private final EmailTicketCustomQueryRepository emailTicketCustomQueryRepository;
    private static final Logger log = LoggerFactory.getLogger(EmailSystem.class);
    private static String replacementKeyword;
    private static List<String> blackListKeyword;
    private final KeywordController keywordController;

    public EmailSystem(EmailDBHandler emailDBHandler, EmailTicketCustomQueryRepository emailTicketCustomQueryRepository, KeywordController keywordController) {
        this.emailDBHandler = emailDBHandler;
        this.emailTicketCustomQueryRepository = emailTicketCustomQueryRepository;
        this.keywordController = keywordController;
    }


    public void sendEmail(Email email) throws AddressException, MessagingException, IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        EmailSettings emailSettings = emailDBHandler.getSettings();
        replacementKeyword = emailSettings.getReplacementKeyword();
        blackListKeyword = emailDBHandler.getKeywords();
        String username = emailSettings.getEmail();
        String password = emailSettings.getPassword();
        String host = emailSettings.getHost();
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp"); // addition
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false"); //check for false
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "26");


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        truncateBlacklistedKeywords(email);

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailSettings.getGeneraEmail())); //,"false
        List<Address> to_Emails = new ArrayList<>();
        for (String toAdd : email.getToAddress()) {
            if (!toAdd.equals("")) {
                Address to = new InternetAddress(toAdd);
                to_Emails.add(to);
            }
        }
        Address to[] = to_Emails.toArray(new Address[to_Emails.size()]);

        for (Address address : to) {
            msg.addRecipient(Message.RecipientType.TO, address);
        }

        List<Address> cc_Emails = new ArrayList<>();
        if (email.getCcAddress() != null) {
            for (String ccAdd : email.getCcAddress()) {
                if (!ccAdd.equals("")) {
                    Address cc = new InternetAddress(ccAdd);
                    cc_Emails.add(cc);
                }
            }
        }
        Address cc[] = cc_Emails.toArray(new Address[cc_Emails.size()]);
        for (Address address : cc) {
            msg.addRecipient(Message.RecipientType.CC, address);
        }

        List<Address> bcc_Emails = new ArrayList<>();
        if (email.getBccAddress() != null) {
            for (String bccAdd : email.getBccAddress()) {
                if (!bccAdd.equals("")) {
                    Address bcc = new InternetAddress(bccAdd);
                    cc_Emails.add(bcc);
                }
            }
        }
        Address bcc[] = bcc_Emails.toArray(new Address[bcc_Emails.size()]);
        for (Address address : bcc) {
            msg.addRecipient(Message.RecipientType.BCC, address);
        }

        msg.setSubject(email.getSubject());
        msg.setSentDate(email.getTimestamp());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        String body = email.getBody();
        messageBodyPart.setText(body, "utf-8", "html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        if (email.getAttachment() == null || email.getAttachment().isEmpty()) {
        } else {
            List<File> newFileList = new ArrayList<>();
            for (String c : email.getAttachment()) {
                if (!c.equals("")) {
                    newFileList.add(new File(c));
                }
            }
            if (newFileList == null || newFileList.isEmpty()) {
            } else if (!(newFileList.size() < 0)) {
                // Part two is attachment
                for (File f : newFileList) {
                    MimeBodyPart attachment = new MimeBodyPart();
                    if (f.exists()) {
                        DataSource source = new FileDataSource(f.getPath());
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
                email.setFromAddress(Collections.singletonList(emailSettings.getGeneraEmail()));

                EmailSent emailSent = (EmailSent) email;
                emailSent.setSent(1);
                emailDBHandler.insertEmail(email);
            } catch (MessagingException  ex ) {

                EmailSent emailSent = (EmailSent) email;
                emailSent.setSent(0);
                emailDBHandler.insertEmail(emailSent);
                ex.printStackTrace();
            }
        }).start();

    }

    //  replacing keyword
    public static void truncateBlacklistedKeywords(Email email) {
        for (int i = 0; i < blackListKeyword.size(); i++) {
            String value = "(?i)" + blackListKeyword.get(i); // (?i) this key is used for ignoring case
            email.setSubject(email.getSubject().replaceAll(value, replacementKeyword)); // replaceAll will replace keyword
            email.setBody(email.getBody().replaceAll(value, replacementKeyword));
        }

    }

    @Scheduled(fixedRate = 5000)
    public String receiveEmail() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        EmailSettings emailSettings = emailDBHandler.getSettings();
        String username = emailSettings.getEmail();
        String password = emailSettings.getPassword();
        String host = emailSettings.getHost();
        try {
//            Properties properties = new Properties();
            Properties properties = System.getProperties();
//            properties.put("mail.store.protocol", "imaps");
            properties.setProperty("mail.store.protocol", "imaps");
            Session emailSession = Session.getDefaultInstance(properties, null);
//                    new javax.mail.Authenticator() {
//                        protected PasswordAuthentication getPasswordAuthentication() {
//                            return new PasswordAuthentication(username, password);
//                        }
//                    }
//                    );

            Store emailStore = emailSession.getStore("imaps");

            emailStore.connect(host, username, password);

            Folder emailFolder = emailStore.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);

            FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);

            Message[] messages = emailFolder.search(ft);
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                log.info("  --Timestamp-- " + timestamp + "   Email subject:" + message.getSubject());
                int tix = 1;    //2 means ticket 1 means general

                for (Domain t : emailDBHandler.whiteListDomain(1)) {
                    for (Address e : message.getFrom()) {
                        if (e.toString().contains(t.getName())) {
                            tix = 2;
                            break;
                        }
                    }
                }
                if (tix == 2) {
                    EmailTickets emailTickets = (EmailTickets) setEmail(message, EmailTickets.class);
                    emailTickets.setTicketNo(emailDBHandler.maxTicketNo());
                    emailDBHandler.insertEmail(emailTickets);
                    if (emailSettings.getAutoCheck() != 0) {
                        autoReplyTicket(emailTickets);
                    }
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
        ATTACH = new ArrayList<>();

        Address[] fromAddress = message.getFrom();
        Address[] toAddress = message.getRecipients(Message.RecipientType.TO);
        Address[] ccAddress = message.getRecipients(Message.RecipientType.CC);
        Address[] bccAddress = message.getRecipients(Message.RecipientType.BCC);
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

        combinedEmailList(fromAddress,ccAddress,bccAddress);


        email.setFromAddress(getAddress(getAddressListString(fromAddress)));
        email.setToAddress(getAddress(getAddressListString(toAddress)));
        email.setCcAddress(getAddress(getAddressListString(ccAddress)));
        email.setBccAddress(getAddress(getAddressListString(bccAddress)));
        email.setSubject(subject);
        email.setBody(result);
        email.setAttachment(ATTACH);
        email.setFreeze(0);

        return email;
    }

    public void combinedEmailList(Address[] fromAddress, Address[] ccAddress, Address[] bccAddress){
        Set<EmailList> set = new LinkedHashSet<>(getAddressListString(fromAddress));
        set.addAll(getAddressListString(ccAddress));
        List<EmailList> combinedList = new ArrayList<>(set);
        Set<EmailList> set2 = new LinkedHashSet<>(combinedList);
        set2.addAll(getAddressListString(bccAddress));
        List<EmailList> combinedList3 = new ArrayList<>(set2);


        emailDBHandler.insertEmailList(combinedList3);
    }

    public boolean solveResponder(EmailTickets email, int userCode,String msg) throws IOException, MessagingException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String autoReplySolvedSubject = "Ticket Number: " + email.getTicketNo() + " Resolved";
        if (msg ==null){
            msg="";
        }
        String bodySolved =msg+
                "<br><br><br>------------------- Ticket " + email.getTicketNo() + " -------------------" +
                        "<br><br>Timestamp:     <b>" + timestamp + "</b>" +
                        "<br><br>Subject:       <b>" + email.getSubject() + "</b>" +
                        "<br><br>" + email.getBody();
        EmailSent e = new EmailSent();
        e.setAttachment(email.getAttachment());
        e.setSubject(autoReplySolvedSubject);
        e.setBody(bodySolved);
        e.setToAddress(email.getFromAddress());
        e.setCcAddress(email.getCcAddress());
        e.setBccAddress(email.getBccAddress());
        e.setTimestamp(timestamp);
        e.setUserCode(userCode);
        sendEmail(e);
        return true;
    }


    public boolean autoReplyTicket(EmailTickets email) throws IOException, MessagingException {
        EmailSettings emailSettings = emailDBHandler.getSettings();
        String autoReplySubject = "Burhani Customer Support - Ticket Number: ";
        String bodyReply = "<h3>The Ticket Number Issued to you is: <b>" + email.getTicketNo() + "</b></h3> "
                + emailSettings.getAutoText() + "<br><br><br>" + "--------In Reply To--------" +
                "<br><br><h4>Subject:   <b>" + email.getSubject() + "</b></h4><br><br>" + email.getBody();

        EmailSent e = new EmailSent();
        e.setSubject(autoReplySubject + email.getTicketNo());
        e.setBody(bodyReply);
        e.setToAddress(email.getFromAddress());
        e.setCcAddress(email.getCcAddress());
        e.setBccAddress(email.getBccAddress());
        e.setTimestamp(email.getTimestamp());
        e.setAttachment(email.getAttachment());
        e.setUserCode(email.getManualEmail());
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
                String receiveFolderName = "receive";
                createDirectoryIfDoesNotExist(pathFile + "\\" + receiveFolderName + "\\" + folderName + "\\");

                String filename = pathFile + "\\" + receiveFolderName + "\\" + folderName + "\\" + part.getFileName();

                ATTACH.add(filename);

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

    public UploadFileResponse uploadFile(@RequestPart("file") MultipartFile file, String address) throws IOException, MessagingException {
        //Three Lines Store the files
        byte[] bytes = file.getBytes();
        Path path = Paths.get(getSendPath(file.getOriginalFilename(), address));
        Files.write(path, bytes);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(file.getOriginalFilename())
                .toUriString();

        return new UploadFileResponse(file.getOriginalFilename(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    public boolean setHistory(int selectedEmailId, int userCode, int status) {
        TicketHistory ticketHistory = new TicketHistory();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ticketHistory.setTime(timestamp);
        ticketHistory.setCode(selectedEmailId);
        ticketHistory.setUserId(userCode);
        if (status == 0) {
            ticketHistory.setStatus(Status.UNLOCKED);
        } else if (status == 1) {
            ticketHistory.setStatus(LOCKED);
        } else if (status == 2) {
            ticketHistory.setStatus(SOLVED);
        } else if (status == 3) {
            ticketHistory.setStatus(ALLOCATED);
        } else if (status == 4) {
            ticketHistory.setStatus(RESOLVED);
        }
        emailDBHandler.insertHistory(ticketHistory);

        return true;
    }

    public String getSendPath(String name, String address) {
        String type = "send";
        String pathFile = emailDBHandler.getSettings().getFilePath();
        String folderName = "";

        if (address.contains("<")) {
            address = address.substring(address.indexOf("<") + 1);
            address = address.substring(0, address.indexOf(">"));

        }
        try {
            folderName = address;
        } catch (Exception e) {
            folderName = "Others";
        }
        createDirectoryIfDoesNotExist(pathFile + "\\" + type + "\\" + address + "\\");

        return pathFile + "\\" + type + "\\" + folderName + "\\" + name;
    }


    public String filter(String type, String ascDescDropDown, String sortByDropDown) {
        String ascDes = "";
        if (ascDescDropDown.equals("Asc")) {
            ascDes = ascDes + " Asc";
        } else {
            ascDes = ascDes + " Desc";
        }

        String sortBy = "";
        if (sortByDropDown.equals("EMNO")) {
            sortBy = sortBy + " e.code ";
        } else if (sortByDropDown.equals("FRADD")) {
            sortBy = sortBy + " e.fromAddress ";
        } else {
            sortBy = sortBy + " e.subject ";
        }

        String input[] = type.split(",");
        String filter = " 1=1 ";
        for (String st : input) {
            if (st.equals("solved")) {
                filter = filter + " AND e.status =" + 3;
            } else if (st.equals("unsolved")) {
                filter = filter + " AND e.status !=" + 3;
            } else if (st.equals("locked")) {
                filter = filter + " AND e.status = " + 2;
            } else if (st.equals("unlocked")) {
                filter = filter + " AND e.status =" + 1;
            } else if (st.equals("lockedByMe")) {
                filter = filter + " AND e.status =" + 2 + " or e.status =" + 4;
            } else if (st.equals("subject")) {
                filter = filter + " AND e.subject NOT LIKE '%reminder%'";
            } else if (st.equals("freeze")) {
                filter = filter + " AND e.freeze = 1";
            } else if (st.equals("unfreeze")) {
                filter = filter + " AND e.freeze = 0";
            } else if (st.equals("resolve")) {
                filter = filter + " AND e.status = " + 5;
            } else {
                filter = filter + "";
            }
        }
        filter = filter + " ORDER BY " + sortBy + ascDes;
//        return emailTicketCustomQueryRepository.getByEmail(filter);
        return filter;
    }

    public String archiveEmails(int freeze, String beforeDate, String ticketFrom, String ticketTo) {
        String bf = "\'" + beforeDate + "\'";
        String filter = "";
        if (freeze == 1) {
            filter = filter + "  e.freeze!=1 ";
        } else if (!beforeDate.equals("null")) {
            filter = filter + " e.timestamp <= " + bf;
        } else if (!ticketFrom.equals("null") && !ticketTo.equals("null")) {
            filter = filter + "  e.ticketNo >=" + ticketFrom + "AND e.ticketNo <= " + ticketTo;
        }
        return filter;
    }

    public String getSingleAddress(List<String> addresses) {
        List<String> toAddress = new ArrayList<>();
        for (String address : addresses) {
            if (!address.equals("")) {
                toAddress.add(address);
            }
        }
        return toAddress.get(0);
    }

    public List<String> setAttachmentPath(MultipartFile[] files, String addresses) {
        List<String> attachment = new ArrayList<>();
//        String attachment = "";
        for (MultipartFile file : files) {
            attachment.add(getSendPath(file.getOriginalFilename(), addresses));
        }
        return attachment;
    }

    private static void createDirectoryIfDoesNotExist(String s) {
        File directory = new File(s);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }


    private static List<EmailList> getAddressListString(Address[] addresses) {
        List<EmailList> s = new ArrayList<>();
        if (addresses == null)
            return s;
        for (Address ad : addresses) {
            if (ad != null) {
                s.add(new EmailList(((InternetAddress) ad).getAddress(), ((InternetAddress) ad).getPersonal()));
            }

        }
        return s;
    }

    private List<String> getAddress(List<EmailList> addressListString) {
        List<String> s = new ArrayList<>();
        for (EmailList emailAddress : addressListString) {
            if (emailAddress != null) {
                s.add(emailAddress.getAddress());
            }
        }
        return s;
    }

    public  List<String> combinedCcList(List<String> thirdBefore, List<String> thirdAfter){
        Set<String> set = new LinkedHashSet<>(thirdBefore);
        set.addAll(thirdAfter);
        List<String> combinedList = new ArrayList<>(set);
        return combinedList;

    }
}
