package rest.demo

import com.exceptionhandler.CustomException
import com.helper.ApiStatusResult
import grails.transaction.Transactional

import javax.ws.rs.core.Response

@Transactional
class ContactService {

    def readContact(long id) {
        Contact contact = Contact.findById(id);
        if(!contact) {
            notFound("Invalid Contact ID: ${id}", Response.Status.BAD_REQUEST);
        }
        return contact
    }

    def readAllContact() {
        return Contact.getAll()
    }

    def create(Contact contact) {
        contact.save(flush: true)
        return contact
    }

    def updateContact(Long id, String firstName, String lastName, String email, String phoneNumber) {
        Contact contact = Contact.findById(id);
        if(!contact) {
            notFound("Invalid Contact ID: ${id}", Response.Status.BAD_REQUEST);
        }
        contact.firstName =firstName
        contact.lastName =lastName
        contact.email = email
        contact.phoneNumber = phoneNumber
        if(!contact.validate()) {
            throw new CustomException(contact.errors, Response.Status.BAD_REQUEST);
        }
        contact.save(false:true)
        return contact
    }

    def deleteContact(long id) {
        Contact contact = Contact.findById(id);
        if(!contact) {
            notFound("Invalid Contact ID: ${id}", Response.Status.BAD_REQUEST);
        }
        contact.delete(flush: true)
    }

    private void notFound(String message, Response.Status status) {
        throw new CustomException(new ApiStatusResult(false, message), Response.Status.BAD_REQUEST);
    }
}
