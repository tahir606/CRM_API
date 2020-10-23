package com.example.CRM.Email.EmailTicket;

import com.example.CRM.Email.Email;
import com.example.CRM.Email.EmailSent.EmailSent;
import com.example.CRM.attachmentProperty.*;
import com.example.CRM.JCode.EmailDBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/ticket")
@Component
public class EmailTicketController {

    private final EmailTicketsRepository emailTicketsRepository;
    private final EmailModelAssembler emailModelAssembler;
    private final EmailSystem emailSystem;
    private final EmailDBHandler emailDBHandler;
    private static final Logger log = LoggerFactory.getLogger(EmailTicketController.class);

    private static final Logger logger = LoggerFactory.getLogger(EmailTicketController.class);

    @Autowired
    private FileStorageService fileStorageService;

    public EmailTicketController(EmailTicketsRepository emailTicketsRepository, EmailModelAssembler emailModelAssembler, EmailSystem emailSystem, EmailDBHandler emailDBHandler) {
        this.emailTicketsRepository = emailTicketsRepository;
        this.emailModelAssembler = emailModelAssembler;
        this.emailSystem = emailSystem;
        this.emailDBHandler = emailDBHandler;
    }


    @GetMapping("/{id}")
    public EntityModel<EmailTickets> one(@PathVariable int id) {
        EmailTickets emailTickets = emailTicketsRepository.findById(id)//
                .orElseThrow(() -> new EmailNotFoundException(id));

        return emailModelAssembler.toModel(emailTickets);
    }

    @GetMapping
    public CollectionModel<EntityModel<EmailTickets>> all() {

        List<EntityModel<EmailTickets>> email = emailTicketsRepository.findAll().stream() //
                .map(emailModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(email, linkTo(methodOn(EmailTicketController.class).all()).withSelfRel());
    }

    //    @PostMapping
//    public ResponseEntity<?> insertEmail(@RequestBody Email emailTickets) {
//        EntityModel<EmailTickets> entityModel = emailModelAssembler.toModel((EmailTickets) emailDBHandler.insertEmail(emailTickets));
//        return ResponseEntity //
//                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
//                .body(entityModel);
//    }
    @RequestMapping("/create")
    public boolean sendEmail(@RequestBody Email email) throws AddressException, MessagingException, IOException {

        EmailTickets emailTickets = (EmailTickets) email;
        emailTickets.setFromAddress(emailTickets.getToAddress());
        emailTickets.setManualEmail(1);
        emailDBHandler.insertEmail(emailTickets);
        emailSystem.autoReplyTicket(emailTickets);

        return true;
    }

    @RequestMapping("/upload")
    public String upload() throws AddressException, MessagingException, IOException {
        System.out.println("accept");
        return "upload";
    }

    @RequestMapping("/download")
    public String download() throws AddressException, MessagingException, IOException {
        System.out.println("accept");
        return "download";
    }

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        String fileName = fileStorageService.storeFile(file);

        // Get the file and save it somewhere
        byte[] bytes = file.getBytes();
        Path path = Paths.get(emailSystem.getPath("send", file.getOriginalFilename()));
        Files.write(path, bytes);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public void uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        Arrays.asList(files)
                .stream()
                .map(file -> {
                    try {
                        return uploadFile(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return null;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public void downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
    }

    @RequestMapping("/solve/{code}")
    public boolean solveEmail(@PathVariable int code) throws IOException, MessagingException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        EmailTickets emailTickets = emailDBHandler.findSelectedEmail(code);

        emailTickets.setSolveTime(timestamp);
        emailTickets.setSolved('Y');
        if (emailSystem.solveResponder(emailTickets)) {
            emailDBHandler.insertEmail(emailTickets);
        }
        return true;
    }

    @RequestMapping("/lock/{code}/{userCode}")
    public boolean lockEmail(@PathVariable int code, @PathVariable int userCode) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        EmailTickets emailTickets = emailDBHandler.findSelectedEmail(code);

        emailTickets.setLockTime(timestamp);
        emailTickets.setLocked(userCode);

        emailTicketsRepository.save(emailTickets);
        return true;
    }

    @RequestMapping("/forward/{code}")
    public boolean forwardEmail(@RequestBody Email email, @PathVariable int code) throws IOException, MessagingException {
        Email emailTicket = emailDBHandler.findSelectedEmail(code);

        emailTicket.setToAddress(email.getToAddress());
        emailTicket.setCcAddress(email.getCcAddress());
        emailTicket.setSubject("FW: " + emailTicket.getSubject());

        emailSystem.sendEmail(convertToEmailSent(emailTicket));
        return true;
    }

    @RequestMapping("/reply/{code}")
    public boolean replyEmail(@RequestBody Email email, @PathVariable int code) throws IOException, MessagingException {
        Email emailTicket = emailDBHandler.findSelectedEmail(code);

        emailTicket.setToAddress(emailTicket.getFromAddress());
        emailTicket.setCcAddress(emailTicket.getCcAddress());
        emailTicket.setSubject("RE: " + emailTicket.getSubject());
        emailTicket.setBody("\n\n\n" + "On " + email.getTimestamp() + ", " + email.getFromAddress() + " wrote:\n" + email.getBody());

        emailSystem.sendEmail(convertToEmailSent(emailTicket));
        return true;
    }

    @RequestMapping("/archive/{code}/{userCode}")
    public boolean moveToArchive(@PathVariable int code, @PathVariable int userCode) {
        EmailTickets emailTicket = emailDBHandler.findSelectedEmail(code);
        if (emailTicket.getLocked() != 0) {
            if (emailTicket.getLocked() == emailDBHandler.getUser(userCode).getUserCode()) {
                emailTicket.setFreeze(1);
                emailDBHandler.insertEmail(emailTicket);
            } else {
                log.warn("You are not allowed to archive this email.");
            }
        } else {
            emailTicket.setFreeze(1);
            emailDBHandler.insertEmail(emailTicket);
        }
        return true;
    }

    public EmailSent convertToEmailSent(Email emailTicket) {
        EmailSent emailSent = new EmailSent();

        emailSent.setToAddress(emailTicket.getToAddress());
        emailSent.setCcAddress(emailTicket.getCcAddress());
        emailSent.setSubject(emailTicket.getSubject());
        emailSent.setBody(emailTicket.getBody());

        return emailSent;
    }


    @PutMapping("/{id}")
    ResponseEntity<?> updateEmails(@RequestBody EmailTickets emailTickets, @PathVariable int id) {
        EmailTickets tickets = emailTicketsRepository.findById(id)//
                .map(email -> {
                    email.setMessageNo(emailTickets.getMessageNo());
                    email.setSubject(emailTickets.getSubject());
                    email.setToAddress(emailTickets.getToAddress());
                    email.setFromAddress(emailTickets.getFromAddress());
                    email.setCcAddress(emailTickets.getCcAddress());
                    email.setBody(emailTickets.getBody());
                    email.setAttachment(emailTickets.getAttachment());
                    email.setSolved(emailTickets.getSolved());
                    email.setSolvedBy(emailTickets.getSolvedBy());
                    email.setSolveTime(emailTickets.getSolveTime());
                    email.setLocked(emailTickets.getLocked());
                    email.setTimestamp(emailTickets.getTimestamp());
                    email.setManualEmail(emailTickets.getManualEmail());
                    email.setLockTime(emailTickets.getLockTime());
                    email.setFreeze(emailTickets.getFreeze());
                    return emailTicketsRepository.save(email);
                })//
                .orElseGet(() -> {
                    emailTickets.setCode(id);
                    return emailTicketsRepository.save(emailTickets);
                });
        EntityModel<EmailTickets> entityModel = emailModelAssembler.toModel(tickets);
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(entityModel);
    }

}
