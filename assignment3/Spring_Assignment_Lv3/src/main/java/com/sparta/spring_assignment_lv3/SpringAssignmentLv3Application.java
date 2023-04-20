package com.sparta.spring_assignment_lv3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringAssignmentLv3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringAssignmentLv3Application.class, args);
	}

}
