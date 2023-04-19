package com.sparta.spring_assignment_lv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringAssignmentLv2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringAssignmentLv2Application.class, args);
	}

}
