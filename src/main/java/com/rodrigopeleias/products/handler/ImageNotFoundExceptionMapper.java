package com.rodrigopeleias.products.handler;

import com.rodrigopeleias.products.exception.ImageNotFoundException;
import com.rodrigopeleias.products.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class ImageNotFoundExceptionMapper implements ExceptionMapper<ImageNotFoundException> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(ImageNotFoundException ex) {
        List<ApiError> errors = new ArrayList<>();
        errors.add(new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage()));
        return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
    }
}
