package ro.rasel.spring.microservices.contactservice.service;

import org.springframework.stereotype.Service;
import ro.rasel.spring.microservices.contactservice.dao.ContactRepository;
import ro.rasel.spring.microservices.contactservice.domain.Contact;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> getContacts(String userId) {
        return contactRepository.findByUserId(userId);
    }

    @Override
    public Optional<Contact> getContact(String userId, long contactId) {
        return contactRepository.findByIdAndUserId(contactId, userId);
    }

    @Override
    public Contact createContact(String userId, Contact contact) {
        return contactRepository.save(contact);
    }

    @Transactional
    public Optional<Contact> updateContact(Contact contact) {
        final Optional<Contact> currentContact =
                contactRepository.findByIdAndUserId(contact.getId(), contact.getUserId());

        return currentContact.map(c -> {
            c.setRelationship(contact.getRelationship());
            c.setFirstName(contact.getFirstName());
            c.setLastName(contact.getLastName());
            c.setEmail(contact.getEmail());
            c.setPhoneNumbers(contact.getPhoneNumbers());
            c.setAddresses(contact.getAddresses());
            return contactRepository.save(c);
        });
    }

    @Override
    @Transactional
    public boolean deleteContact(String userId, long contactId) {
        final Optional<Contact> contact = contactRepository.findByIdAndUserId(contactId, userId);
        contact.ifPresent(contactRepository::delete);
        return contact.isPresent();
    }
}
