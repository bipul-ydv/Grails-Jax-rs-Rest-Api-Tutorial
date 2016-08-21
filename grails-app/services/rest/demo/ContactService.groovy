package rest.demo

import com.CustomException.DomainNotFoundException
import grails.transaction.Transactional

@Transactional
class ContactService {

    public Contact readContact(long id) {
       Contact contact = Contact.findById(id);
        if(!contact) {
            throw new DomainNotFoundException("Contact Not Find By ID:"+id);
        }
        return contact
    }

    public List<Contact> readAllContact() {
        return Contact.getAll()
    }

    public Contact create(Contact contact) {
        contact.save(flush: true)
        return contact
    }

    public Contact updateContact(Long id, String firstName, String lastName, String email, String phoneNumber) {
        Contact contact = Contact.findById(id);
        if(!contact) {
            throw new DomainNotFoundException("Contact Not Find By ID:"+id);
        }
        contact.firstName =firstName
        contact.lastName =lastName
        contact.email = email
        contact.phoneNumber = phoneNumber
        if(!contact.validate()) {
            throw new Exception("Bad request")
        }
        contact.save(false:true)
        return contact
    }

    public void deleteContact(long id) {
        Contact contact = Contact.findById(id);
        if(!contact) {
            throw new DomainNotFoundException("Invalid Contact ID:"+id);
        }
        contact.delete(flush: true)
    }
}
