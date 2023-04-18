package com.example.spring_assignment_lv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class SpringAssignmentLv1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringAssignmentLv1Application.class, args);
	}

}
