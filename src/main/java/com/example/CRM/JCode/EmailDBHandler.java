package com.example.CRM.JCode;

import com.example.CRM.Email.EmailStore.EmailRepository;
import com.example.CRM.Email.EmailStore.EmailTickets;
import org.springframework.stereotype.Service;

@Service
public class EmailDBHandler {
    private final EmailRepository emailRepository;

    public EmailDBHandler(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    //             =new EmailRepository() {
//         @Override
//         public List<EmailTickets> findAll() {
//             return null;
//         }
//
//         @Override
//         public List<EmailTickets> findAll(Sort sort) {
//             return null;
//         }
//
//         @Override
//         public List<EmailTickets> findAllById(Iterable<Integer> iterable) {
//             return null;
//         }
//
//         @Override
//         public <S extends EmailTickets> List<S> saveAll(Iterable<S> iterable) {
//             return null;
//         }
//
//         @Override
//         public void flush() {
//
//         }
//
//         @Override
//         public <S extends EmailTickets> S saveAndFlush(S s) {
//             return null;
//         }
//
//         @Override
//         public void deleteInBatch(Iterable<EmailTickets> iterable) {
//
//         }
//
//         @Override
//         public void deleteAllInBatch() {
//
//         }
//
//         @Override
//         public EmailTickets getOne(Integer integer) {
//             return null;
//         }
//
//         @Override
//         public <S extends EmailTickets> List<S> findAll(Example<S> example) {
//             return null;
//         }
//
//         @Override
//         public <S extends EmailTickets> List<S> findAll(Example<S> example, Sort sort) {
//             return null;
//         }
//
//         @Override
//         public Page<EmailTickets> findAll(Pageable pageable) {
//             return null;
//         }
//
//         @Override
//         public <S extends EmailTickets> S save(S s) {
//             return null;
//         }
//
//         @Override
//         public Optional<EmailTickets> findById(Integer integer) {
//             return Optional.empty();
//         }
//
//         @Override
//         public boolean existsById(Integer integer) {
//             return false;
//         }
//
//         @Override
//         public long count() {
//             return 0;
//         }
//
//         @Override
//         public void deleteById(Integer integer) {
//
//         }
//
//         @Override
//         public void delete(EmailTickets emailTickets) {
//
//         }
//
//         @Override
//         public void deleteAll(Iterable<? extends EmailTickets> iterable) {
//
//         }
//
//         @Override
//         public void deleteAll() {
//
//         }
//
//         @Override
//         public <S extends EmailTickets> Optional<S> findOne(Example<S> example) {
//             return Optional.empty();
//         }
//
//         @Override
//         public <S extends EmailTickets> Page<S> findAll(Example<S> example, Pageable pageable) {
//             return null;
//         }
//
//         @Override
//         public <S extends EmailTickets> long count(Example<S> example) {
//             return 0;
//         }
//
//         @Override
//         public <S extends EmailTickets> boolean exists(Example<S> example) {
//             return false;
//         }
//     };
    public EmailTickets emailDataInsert(EmailTickets emailTickets) {
        return emailRepository.save(emailTickets);
    }
}
