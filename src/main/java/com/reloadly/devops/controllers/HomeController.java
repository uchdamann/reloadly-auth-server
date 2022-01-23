package com.reloadly.devops.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reloadly.devops.utilities.EncryptUtil;
import com.reloadly.devops.utilities.AuthProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("auth-serve/messenger/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HomeController {
	@Autowired
	private AuthProperties prop;
	
	@Autowired
	@Qualifier("encryptUtil")
	private EncryptUtil encryptUtil;
	
	@GetMapping("home1")
	private String home1() {
		
		log.info("Properties: {}", prop);
		return "Hello world";
	}
}
