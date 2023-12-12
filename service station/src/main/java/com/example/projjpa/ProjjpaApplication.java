package com.example.projjpa;

import com.example.projjpa.repos.CustomRepositoryImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImp.class)
public class ProjjpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjjpaApplication.class, args);
	}

}
