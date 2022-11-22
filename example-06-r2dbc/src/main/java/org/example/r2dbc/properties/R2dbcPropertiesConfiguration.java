package org.example.r2dbc.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(R2dbcProperties.class)
public class R2dbcPropertiesConfiguration {
}
