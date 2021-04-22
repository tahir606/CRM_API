package com.example.CRM.Email.EmailTicket;

import com.example.CRM.Client.ClientSystem;
import com.example.CRM.Email.Email;
import com.example.CRM.Email.Setiings.EmailSettings;
import com.example.CRM.JCode.EmailDBHandler;
import org.hibernate.engine.jdbc.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
    private final EmailTicketCustomQueryRepository queryRepository;
    private final ClientSystem clientSystem;

    private static final Logger log = LoggerFactory.getLogger(EmailTicketController.class);

    public EmailTicketController(EmailTicketsRepository emailTicketsRepository, EmailModelAssembler emailModelAssembler, EmailSystem emailSystem, EmailDBHandler emailDBHandler, EmailTicketCustomQueryRepository queryRepository, ClientSystem clientSystem) {
        this.emailTicketsRepository = emailTicketsRepository;
        this.emailModelAssembler = emailModelAssembler;
        this.emailSystem = emailSystem;
        this.emailDBHandler = emailDBHandler;
        this.queryRepository = queryRepository;
        this.clientSystem = clientSystem;
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


    @RequestMapping("/emails")
    public CollectionModel<EntityModel<EmailTickets>> readAllFilterEmails(@RequestParam String filter, @RequestParam String order, @RequestParam String orderBy) {
        String filterString = emailSystem.filter(filter, order, orderBy);
        List<EntityModel<EmailTickets>> email = emailDBHandler.filteredEmails(filterString).stream() //
                .map(emailModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(email, linkTo(methodOn(EmailTicketController.class).all()).withSelfRel());
    }

    @RequestMapping("/getNewEmails/{ticketNo}")
    public CollectionModel<EntityModel<EmailTickets>> readAllFilterEmails(@PathVariable int ticketNo) {
        List<EntityModel<EmailTickets>> email = queryRepository.getNewEmails(ticketNo).stream()//
                .map(emailModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(email, linkTo(methodOn(EmailTicketController.class).all()).withSelfRel());
    }

    @RequestMapping("/email/{code}")
    public EmailTickets searchEmail(@PathVariable int code) {
        EmailTickets email = emailDBHandler.searchEmail(code, 0);
        return email;
    }

    @RequestMapping("/ticketsSolvedByUserDetails/{userCode}")
    public List<EmailTickets> ticketsSolvedByUserDetails(@PathVariable int userCode, @RequestParam String filter) {
        String filterTicket = clientSystem.filterTime(filter);
        List<EmailTickets> email = emailDBHandler.getTicketsSolvedByUserDetails(userCode, filterTicket);
        return email;
    }
    @RequestMapping("/clientReportWithDomain/{clientId}")
    public List<EmailTickets> clientReportWithDomain(@PathVariable int clientId, @RequestParam String filter) {

        String filterTicket = clientSystem.filterTime(filter);
        List<EmailTickets> email = emailDBHandler.clientReportWithDomain(clientId, filterTicket);

        return email;
    }
    @RequestMapping("/create/{userCode}")
    public boolean sendEmail(@RequestPart("email") Email email, @RequestParam("files") MultipartFile[] files, @PathVariable int userCode) throws AddressException, MessagingException, IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        EmailSettings emailSettings = emailDBHandler.getSettings();

        String address = emailSystem.getSingleAddress(email.getToAddress());
        Arrays.asList(files)
                .stream()
                .map(file -> {
                    try {
                        return emailSystem.uploadFile(file, address);
                    } catch (IOException | MessagingException e) {
                        e.printStackTrace();
                    }

                    return null;
                })
                .collect(Collectors.toList());
        EmailTickets emailTickets = (EmailTickets) email;

        emailTickets.setTimestamp(timestamp);
        emailTickets.setTicketNo(emailDBHandler.maxTicketNo());

        emailTickets.setFromAddress(email.getToAddress());
        emailTickets.setAttachment(emailSystem.setAttachmentPath(files, address));
        emailTickets.setStatus(Status.UNLOCKED);
        emailTickets.setManualEmail(userCode);

        emailDBHandler.insertEmail(emailTickets);

        if (emailSettings.getAutoCheck() != 0) {
            if (!emailTickets.isSendAsEmail()) {
                emailSystem.autoReplyTicket(emailTickets);
            }

        }
        return true;
    }

    @GetMapping(value = "/download/{fileNames:.+}", produces = "application/zip")
    public void zipDownload(HttpServletResponse response, @PathVariable String[] fileNames, @RequestParam String path) throws IOException {
        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        for (String fileName : fileNames) {
            FileSystemResource resource = new FileSystemResource(path + fileName);
            ZipEntry zipEntry = new ZipEntry(resource.getFilename());
            zipEntry.setSize(resource.contentLength());
            zipOut.putNextEntry(zipEntry);
            StreamUtils.copy(resource.getInputStream(), zipOut);
            zipOut.closeEntry();

        }
        zipOut.finish();
        zipOut.close();
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"temp.zip\"");

    }

    @RequestMapping("/statusCheck")
    public boolean checkForStatus(@RequestParam int code, @RequestParam Status status) {

        Status checkStatus = emailTicketsRepository.findByCode(code).getStatus();
        if (!checkStatus.equals(status)) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/check/{ticketNo}")
    public boolean checkUpdate(@PathVariable int ticketNo) {
        if (emailDBHandler.getMaxTicketNo().getTicketNo() > ticketNo) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/getMaxTicketNo")
    public EntityModel<EmailTickets> getMaxTicketNo() {
        EmailTickets emailTickets = emailDBHandler.getMaxTicketNo();

        return emailModelAssembler.toModel(emailTickets);
    }

    @RequestMapping("/updatedStatus/{ticketNo}")
    public EntityModel<EmailTickets> getUpdatedEmail(@PathVariable int ticketNo) {
        EmailTickets emailTickets = emailTicketsRepository.findById(ticketNo)//
                .orElseThrow(() -> new EmailNotFoundException(ticketNo));

        return emailModelAssembler.toModel(emailTickets);
    }

    @RequestMapping("/updateEmails/{ticketNo}")
    public CollectionModel<EntityModel<EmailTickets>> getUpdateEmails(@PathVariable int ticketNo) {

        List<EntityModel<EmailTickets>> email = emailTicketsRepository.findByTicketNoGreaterThan(ticketNo).stream() //
                .map(emailModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(email, linkTo(methodOn(EmailTicketController.class).all()).withSelfRel());
    }


    @RequestMapping("/solve")
    public boolean solveEmail(@RequestParam int code, @RequestParam int userCode, @RequestParam int status, @RequestParam boolean checkSend, @RequestParam String solvedBody) throws IOException, MessagingException {
        EmailSettings emailSettings = emailDBHandler.getSettings();
        EmailTickets emailTickets = emailDBHandler.findSelectedEmail(code);
        if (emailSystem.setHistory(code, userCode, status)) {
            emailTickets.setStatus(Status.SOLVED);

            if (emailSettings.getSolveCheck() == 0) {
                emailDBHandler.insertEmail(emailTickets);
            } else {
                if (!checkSend) {
                    if (emailSystem.solveResponder(emailTickets, userCode, solvedBody)) {
                        emailDBHandler.insertEmail(emailTickets);
                    }
                } else {
                    emailDBHandler.insertEmail(emailTickets);
                }

            }

        }
        return true;
    }

    @RequestMapping("/CountOfSolvedOrLockedEmails")
    public int getCountOfSolvedOrLockedEmails(@RequestParam int status, @RequestParam int userCode) {
        return emailDBHandler.getSolvedOrLockedEmails(status, userCode);
    }

    @RequestMapping("/CountOfUnLockedEmails")
    public int getOfUnLockedEmails() {
        return emailDBHandler.getOfUnLockedEmails();
    }

    @RequestMapping("/CountOfUnSolvedEmails")
    public int getOfUnSolvedEmails() {
        return emailDBHandler.getOfUnSolvedEmails();
    }


    @RequestMapping("/lock")
    public boolean lockEmail(@RequestParam int code, @RequestParam int userCode, @RequestParam int status) throws IOException, MessagingException {
        EmailTickets emailTickets = emailDBHandler.findSelectedEmail(code);
        if (emailSystem.setHistory(code, userCode, status)) {
            emailTickets.setStatus(Status.LOCKED);
            emailDBHandler.insertEmail(emailTickets);
        }
        return true;
    }

    @RequestMapping("/allocate")
    public boolean allocateEmail(@RequestParam int code, @RequestParam int allocatorCode, @RequestParam int userCode, @RequestParam int status) throws IOException, MessagingException {
        EmailTickets emailTickets = emailDBHandler.findSelectedEmail(code);
        if (emailSystem.setHistory(code, userCode, status)) {
            emailTickets.setStatus(Status.ALLOCATED);
            emailTickets.setIsAllocatedBy(allocatorCode);
            emailDBHandler.insertEmail(emailTickets);
        }
        return true;
    }

    @RequestMapping("/unAllocate")
    public boolean unAllocateEmail(@RequestParam int code, @RequestParam int userCode, @RequestParam int status) throws IOException, MessagingException {
        EmailTickets emailTickets = emailDBHandler.findSelectedEmail(code);
        if (emailSystem.setHistory(code, userCode, status)) {
            emailTickets.setStatus(Status.UNLOCKED);
            emailTickets.setIsAllocatedBy(0);
            emailDBHandler.insertEmail(emailTickets);
        }
        return true;
    }

    @RequestMapping("/resolve")
    public boolean resolveEmail(@RequestParam int code, @RequestParam int userCode, @RequestParam int status) throws IOException, MessagingException {
        EmailTickets emailTickets = emailDBHandler.findSelectedEmail(code);
        if (emailSystem.setHistory(code, userCode, status)) {
            emailTickets.setStatus(Status.RESOLVED);
            emailDBHandler.insertEmail(emailTickets);
        }
        return true;
    }

    @RequestMapping("/unlock")
    public boolean unlockEmail(@RequestParam int code, @RequestParam int userCode, @RequestParam int status) throws IOException, MessagingException {
        EmailTickets emailTickets = emailDBHandler.findSelectedEmail(code);
        if (emailSystem.setHistory(code, userCode, status)) {
            emailTickets.setStatus(Status.UNLOCKED);
            emailDBHandler.insertEmail(emailTickets);
        }
        return true;
    }


    @RequestMapping("/archive/{code}")
    public boolean moveToArchive(@PathVariable int code) {
        EmailTickets emailTicket = emailDBHandler.findSelectedEmail(code);
        if (emailTicket != null) {
            emailTicket.setFreeze(1);
        }

        emailDBHandler.insertEmail(emailTicket);
        return true;
    }

    @RequestMapping("/archiveAll")
    public boolean moveToArchiveAllEmails(@RequestParam int freeze, @RequestParam String beforeDate, @RequestParam String ticketFrom, @RequestParam String ticketTo) {
        String filter = emailSystem.archiveEmails(freeze, beforeDate, ticketFrom, ticketTo);
        List<EmailTickets> emailTickets = queryRepository.archiveEmails(filter);
        for (EmailTickets emailTickets1 : emailTickets) {
            emailTickets1.setFreeze(1);
            emailDBHandler.insertEmail(emailTickets1);

        }

        return true;
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

                    email.setTimestamp(emailTickets.getTimestamp());
                    email.setManualEmail(emailTickets.getManualEmail());
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
