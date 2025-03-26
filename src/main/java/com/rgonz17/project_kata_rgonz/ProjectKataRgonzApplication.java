package com.rgonz17.project_kata_rgonz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjectKataRgonzApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectKataRgonzApplication.class, args);
	}

}
