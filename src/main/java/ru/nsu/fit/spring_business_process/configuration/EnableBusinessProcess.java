package ru.nsu.fit.spring_business_process.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ComponentScan(basePackages = "ru.nsu.fit.spring_business_process")
@EnableJpaRepositories(basePackages = "ru.nsu.fit.spring_business_process.repository")
@EntityScan(basePackages = "ru.nsu.fit.spring_business_process.entity")
@Import({QueueConfiguration.class})
public @interface EnableBusinessProcess {
}
