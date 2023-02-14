package com.example.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservices.currencyexchangeservice.bean.CurrencyExchange;

public interface CurrencyExchangeRepository
			extends JpaRepository<CurrencyExchange, Long>{

	CurrencyExchange findByFromAndTo(String from, String to);
}
