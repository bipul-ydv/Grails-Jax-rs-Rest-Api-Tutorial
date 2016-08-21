package rest.demo

import com.CustomException.DomainNotFoundException
import com.helper.ApiStatusResult
import grails.converters.JSON

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path('/api/contact')
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class ContactRSResource {
    def contactService

    // create contact
    @Path('/create')
    @POST
    JSON create(@QueryParam("firstName") String firstName,@QueryParam("phoneNumber") String phoneNumber,
                      @QueryParam("lastName") String lastName , @QueryParam("email") String email ) {
        Contact contact = new Contact(firstName: firstName,lastName:lastName,phoneNumber:phoneNumber,email:email);
        // validate contact domain
        if ( !contact.validate()) {
            return  new ApiStatusResult(false, 'Bad Request') as JSON
        }
        contact = contactService.create(contact)
        return [id:contact.getId() ] as JSON
    }

    // read contact by id
    @Path('/read/{id}')
    @POST
    JSON read(@PathParam('id') long id) {
        try {
          Contact contact =  contactService.readContact(id)
            return contact as JSON
        } catch (DomainNotFoundException e) {
            return new ApiStatusResult(false, e.getMessage()) as JSON
        }

    }

    // read all contact
    @Path('/readAll')
    @POST
    JSON readAll() {
            List<Contact> contacts =  contactService.readAllContact()
            return contacts as JSON
    }


    // update contact
    @Path('/update')
    @POST
    JSON update(@QueryParam("id") Long id,@QueryParam("firstName") String firstName,@QueryParam("phoneNumber") String phoneNumber,
                       @QueryParam("lastName") String lastName , @QueryParam("email") String email ) {
      try {
          Contact contact = contactService.updateContact(id,firstName,lastName,phoneNumber,email)
          return contact as JSON
      } catch (DomainNotFoundException e)  {
          return  new ApiStatusResult(false, e.getMessage()) as JSON
        } catch (Exception e) {
          return  new ApiStatusResult(false, e.getMessage()) as JSON
      }
    }

    // delete contact
    @Path('/delete/{id}')
    @DELETE
    JSON delete(@PathParam('id') Long id) {
        try {
           contactService.deleteContact(id)
            return new ApiStatusResult(true, 'successfully delete contact with id'+id) as JSON
        } catch (DomainNotFoundException e) {
            return new ApiStatusResult(false, e.getMessage()) as JSON
        }
    }

}
