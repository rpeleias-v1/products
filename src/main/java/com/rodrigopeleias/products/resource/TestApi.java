package com.rodrigopeleias.products.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/teste")
public class TestApi {

    @GET
    @Path("/metodo")
    public String teste() {
        return "teste de JAX-RS";
    }
}
