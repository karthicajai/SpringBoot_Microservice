package com.example.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.limitsservice.bean.Limit;
import com.example.microservices.limitsservice.configuration.Configuration;

@RestController
public class LimitController {
	
	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public Limit retriveLimits()
	{
		return new Limit(configuration.getMinimum(), configuration.getMaximum());
	}
}
