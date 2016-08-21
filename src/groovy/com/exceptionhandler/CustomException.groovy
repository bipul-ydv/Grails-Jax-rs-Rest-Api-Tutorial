package com.exceptionhandler

import com.apidomainhelper.DomainErrorProvider
import org.springframework.validation.FieldError

import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response

/**
 * Created by bhushan on 16/12/16.
 */
class CustomException extends WebApplicationException {

    public CustomException(def content, Response.Status status) {
        super(notFound(content, status));
    }

    public CustomException(List<FieldError> errors, Response.Status status) {
        super(notFound(DomainErrorProvider.handerError(errors), status));
    }

    private static Response notFound(def result, Response.Status status) {
        return Response.status(status).entity(result).build()
    }

}
