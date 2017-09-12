package com.rodrigopeleias.products.handler;

import com.rodrigopeleias.products.exception.ProductNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class ProductNotFoundExceptionMapper implements ExceptionMapper<ProductNotFoundException> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(ProductNotFoundException ex) {
        List<ApiError> errors = new ArrayList<>();
        errors.add(new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage()));
        return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
    }
}
