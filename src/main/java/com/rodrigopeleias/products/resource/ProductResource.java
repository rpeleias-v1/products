package com.rodrigopeleias.products.resource;

import com.rodrigopeleias.products.domain.Image;
import com.rodrigopeleias.products.domain.Product;
import com.rodrigopeleias.products.service.ImageService;
import com.rodrigopeleias.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Component
@Path("/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid Product product) {
        product = productService.save(product);
        URI build = UriBuilder.fromPath("products/{productId}").build(product.getId());
        return Response.created(build).entity(product).build();
    }

    @PUT
    @Path("/{productId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("productId") Long productId, @Valid Product product) {
        return Response.ok(productService.update(productId, product)).build();
    }

    @DELETE
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("productId") Long productId) {
        productService.delete(productId);
        return Response.noContent().build();
    }

    @POST
    @Path("/{productId}/images")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createImage(@PathParam("productId") Long productId, @Valid Image image) {
        image = imageService.save(productId, image);
        URI build = UriBuilder.fromPath("products/{productId}/image/{imageId}").build(productId, image.getId());
        return Response.created(build).entity(image).build();
    }

    @PUT
    @Path("/{productId}/images/{imageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateImage(@PathParam("productId") Long productId, @PathParam("imageId") Long imageId, @Valid Image image) {
        return Response.ok(imageService.update(productId, imageId, image)).build();
    }

    @DELETE
    @Path("/{productId}/images/{imageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteImage(@PathParam("productId") Long productId, @PathParam("imageId") Long imageId) {
        imageService.delete(productId, imageId);
        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProductsExcludingRelationships() {
        return Response.ok(productService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByIdExcludingRelationships(@PathParam("id") Long productId) {
        return Response.ok(productService.findById(productId)).build();
    }

    @GET
    @Path("/images")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProductsWithImages() {
        return Response.ok(productService.findAllWithImages()).build();
    }

    @GET
    @Path("/{id}/images")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByIdWithImages(@PathParam("id") Long productId) {
        return Response.ok(productService.findWithImagesByProductId(productId)).build();
    }

    @GET
    @Path("/childProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProductsWithChildProducts() {
        return Response.ok(productService.findAllWithChildProducts()).build();
    }

    @GET
    @Path("/{id}/images/childProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByIdWithImagesAndChildProducts(@PathParam("id") Long productId) {
        return Response.ok(productService.findWithImagesAndChildProductsByProductId(productId)).build();
    }

    @GET
    @Path("/images/childProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProductsWithImagesAndChildProducts() {
        return Response.ok(productService.findAllWithImagesAndChildProducts()).build();
    }

    @GET
    @Path("/{id}/childProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByIdWithChildProducts(@PathParam("id") Long productId) {
        return Response.ok(productService.findWithChildProductsByProductId(productId)).build();
    }

    @GET
    @Path("/{id}/childProducts/set")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSetOfChildProductsByProductId(@PathParam("id") Long productId) {
        return Response.ok(productService.findChildProductsByProductId(productId)).build();
    }

    @GET
    @Path("/{id}/images/set")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSetOfImagesByProductId(@PathParam("id") Long productId) {
        return Response.ok(imageService.findByProduct(productId)).build();
    }
}
