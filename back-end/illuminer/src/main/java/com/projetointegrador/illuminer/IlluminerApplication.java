package com.projetointegrador.illuminer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class IlluminerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IlluminerApplication.class, args);
	}
	
	@GetMapping
	public ModelAndView swaggerui() {
		return new ModelAndView("redirect:/swagger-ui/");
	}

}
