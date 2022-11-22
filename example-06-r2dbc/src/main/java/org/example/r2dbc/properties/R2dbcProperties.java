package org.example.r2dbc.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "data.r2dbc")
public class R2dbcProperties {
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
}
