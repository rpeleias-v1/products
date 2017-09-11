package com.rodrigopeleias.products.resource;

import com.rodrigopeleias.products.domain.Product;
import com.rodrigopeleias.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Path("/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid Product product) {
        product = productService.save(product);
        URI build = UriBuilder.fromPath("products/{productId}").build(product.getId());
        return Response.created(build).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(productService.findAll()).build();
    }

    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("productId") Long productId) {
        return Response.ok(productService.findById(productId)).build();
    }

    @PUT
    @Path("{productId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("productId") Long productId, @Valid Product product) {
        return Response.ok(productService.save(product)).build();
    }

    @DELETE
    @Path("{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("productId") Long productId) {
        productService.delete(productId);
        return Response.noContent().build();
    }
}
