package com.projetointegrador.illuminer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetointegrador.illuminer.repository.CurtidaRepository;

@RestController
@RequestMapping ("/curtida")
@CrossOrigin ("*")
public class CurtidaController {
	
	@Autowired
	private CurtidaRepository repository;

}
