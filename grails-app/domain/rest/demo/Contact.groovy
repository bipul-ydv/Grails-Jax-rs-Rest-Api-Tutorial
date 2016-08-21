package rest.demo

class Contact {

    String firstName
    String lastName
    String phoneNumber
    String email
    static constraints = {
        firstName nullable: false
        lastName nullable: true
        email nullable: true
        phoneNumber nullable:false
    }
}
