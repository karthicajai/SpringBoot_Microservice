package com.example.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.example.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to) {
		
		//get right currency exchange entity from DB by from and to currency
		CurrencyExchange currencyExhange = repository.findByFromAndTo(from, to);
		if(currencyExhange == null) {
			throw new RuntimeException("Unable to find data for "+from+" to "+to);
		}
		
		String port = environment.getProperty("local.server.port");
		currencyExhange.setEnvironment(port);
		
		return currencyExhange;
	}
}
