package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootApplication
@RestController 
public class HelloTechsummitApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloTechsummitApplication.class, args);
	}
	
	@Autowired
	HelloRepository repo;
	
	@GetMapping("/")
	public String sayHello(HttpServletRequest request) {
		repo.save(new HelloData(null, request.getRemoteAddr()));

		return "Hello Tech Summit " +  request.getRemoteAddr() + "!   Here are the recent visits: " + repo.findAll();
	}
	
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
class HelloData {
	@Id
	@GeneratedValue
	Long id;
	String remoteAddress;
}

interface HelloRepository extends JpaRepository<HelloData, Long> {
}


