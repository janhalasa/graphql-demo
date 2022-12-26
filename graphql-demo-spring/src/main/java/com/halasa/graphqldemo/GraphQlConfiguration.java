package com.halasa.graphqldemo;

import com.halasa.graphqldemo.graphql.DateScalar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQlConfiguration {

    @Bean
    public RuntimeWiringConfigurer configurer() {
        return builder -> builder.scalar(DateScalar.DATE).build();
    }

}
