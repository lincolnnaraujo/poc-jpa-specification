package br.com.springspec.demospecification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class DemoSpecificationApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoSpecificationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoSpecificationApplication.class, args);
		log.info(" ---------- APP UP ---------- ");
	}

}
