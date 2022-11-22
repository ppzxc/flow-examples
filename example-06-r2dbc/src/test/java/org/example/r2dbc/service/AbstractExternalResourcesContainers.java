package org.example.r2dbc.service;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractExternalResourcesContainers {

    public static final PostgreSQLContainer postgreSQLContainer;

    static {
        postgreSQLContainer = new PostgreSQLContainer("postgres:15.1")
                .withUsername("test")
                .withPassword("test")
                .withDatabaseName("test");
        postgreSQLContainer.withReuse(true);
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
//        String r2dbcUrl = String.format("r2dbc:postgresql://%s:%d/%s", postgreSQLContainer.getHost(), postgreSQLContainer.getFirstMappedPort(), postgreSQLContainer.getDatabaseName());
//        registry.add("spring.r2dbc.url", () -> r2dbcUrl);
//        registry.add("spring.r2dbc.username", postgreSQLContainer::getUsername);
//        registry.add("spring.r2dbc.password", postgreSQLContainer::getPassword);
//        registry.add("spring.r2dbc.pool.enabled", () -> true);

        registry.add("data.r2dbc.host", postgreSQLContainer::getHost);
        registry.add("data.r2dbc.port", postgreSQLContainer::getFirstMappedPort);
        registry.add("data.r2dbc.database", postgreSQLContainer::getDatabaseName);
        registry.add("data.r2dbc.username", postgreSQLContainer::getUsername);
        registry.add("data.r2dbc.password", postgreSQLContainer::getPassword);

    }
}

