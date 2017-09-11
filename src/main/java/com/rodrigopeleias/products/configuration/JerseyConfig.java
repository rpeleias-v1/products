package com.rodrigopeleias.products.configuration;

import com.rodrigopeleias.products.endpoint.TestApi;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig{

    public JerseyConfig() {
        register(TestApi.class);
    }
}
