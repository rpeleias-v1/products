package com.rodrigopeleias.products.configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.rodrigopeleias.products.resource.ProductResource;
import com.rodrigopeleias.products.resource.TestApi;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig{

    public JerseyConfig() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
        register(ProductResource.class);
        packages(true, "com.rodrigopeleias.products");
    }

    @Bean
    public Module datatypeHibernateModule() {
        return new Hibernate5Module();
    }
}
