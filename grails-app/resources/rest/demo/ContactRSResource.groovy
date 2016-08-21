package rest.demo

import com.exceptionhandler.CustomException

import javax.ws.rs.core.Response

import static org.grails.jaxrs.response.Responses.*

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam

@Path('/api/contact')
@Consumes(['application/xml', 'application/json'])
@Produces(['application/xml', 'application/json'])
class ContactRSResource {
    def contactService

    // create contact
    @Path('/create')
    @POST
    Response create1(Contact contact) {
        if (!contact.validate()) {
            throw new CustomException(contact.errors.fieldErrors, Response.Status.BAD_REQUEST)
        }
        ok contactService.create(contact)
    }

    // read contact by id
    @Path('/read/{id}')
    @POST
    Response read(@PathParam('id') long id) {
        ok contactService.readContact(id)

    }

    // read all contact
    @Path('/readAll')
    @POST
    Response readAll() {
        ok contactService.readAllContact()
    }


    // update contact using ******QueryParam*******
    @Path('/update')
    @POST
    Response update(@QueryParam("id") Long id,@QueryParam("firstName") String firstName,@QueryParam("phoneNumber") String phoneNumber,
                    @QueryParam("lastName") String lastName , @QueryParam("email") String email ) {
        ok contactService.updateContact(id,firstName,lastName,phoneNumber,email)
    }

    // delete contact
    @Path('/delete/{id}')
    @DELETE
    Response delete(@PathParam('id') Long id) {
        contactService.deleteContact(id)
        Response.status(Response.Status.OK)
    }

}
