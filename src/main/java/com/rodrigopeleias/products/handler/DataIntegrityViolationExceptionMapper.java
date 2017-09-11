package com.rodrigopeleias.products.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class DataIntegrityViolationExceptionMapper implements ExceptionMapper<DataIntegrityViolationException> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(DataIntegrityViolationException ex) {
        List<ApiError> errors = new ArrayList<>();
        errors.add(new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage()));
        return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
    }
}
