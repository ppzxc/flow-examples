package org.example.r2dbc.configuration;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import java.util.List;
import kr.nanoit.mo.domain.member.MemberReadingConverter;
import kr.nanoit.mo.domain.member.MemberWritingConverter;
import kr.nanoit.mo.domain.role.RoleReadingConverter;
import kr.nanoit.mo.domain.role.RoleTypeWritingConverter;
import kr.nanoit.mo.domain.role.RoleWritingConverter;
import kr.nanoit.mo.properties.R2dbcProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RequiredArgsConstructor
@Configuration
@EnableTransactionManagement
@EnableR2dbcRepositories
@EnableR2dbcAuditing
public class R2dbcConfiguration extends AbstractR2dbcConfiguration {

    private final R2dbcProperties r2dbcProperties;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .host(r2dbcProperties.getHost())
                .port(r2dbcProperties.getPort())
                .database(r2dbcProperties.getDatabase())
                .username(r2dbcProperties.getUsername())
                .password(r2dbcProperties.getPassword())
//              .codecRegistrar(EnumCodec.builder().withEnum("post_status", Post.Status.class).build())
                .build());
    }

    @Override
    protected List<Object> getCustomConverters() {
        return List.of(new MemberReadingConverter(), new MemberWritingConverter(),
                new RoleReadingConverter(), new RoleWritingConverter(), new RoleTypeWritingConverter());
    }

    @Bean
    ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
        initializer.setDatabasePopulator(populator);

        return initializer;
    }
}
