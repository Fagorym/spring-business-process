package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.nsu.fit.spring_business_process.configuration.EnableBusinessProcess;

@SpringBootApplication
@EnableBusinessProcess
public class ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}

}
