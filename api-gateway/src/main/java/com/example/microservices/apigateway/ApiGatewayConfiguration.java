package com.example.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		
		 return builder.routes()
				//example
				 .route(p -> p.path("/get")
						 .filters(f -> f
								 .addRequestHeader("MyHeader", "MyURI")
								 .addRequestParameter("MyParam", "MyValue"))
						 .uri("http://httpbin.org:80")
						 )
				//routes to currency exchange service
				 .route(p -> p.path("/currency-exchange/**")
						 	.uri("lb://currency-exchange")//lb = load balancer
						)
				//routes to currency conversion service
				 .route(p -> p.path("/currency-conversion/**")
						 	.uri("lb://currency-conversion")
						)
				 .route(p -> p.path("/currency-conversion-feign/**")
						 	.uri("lb://currency-conversion")
						)
				 //custom url route for currency-conversion-feign
				 .route(p -> p.path("/currency-conversion-new/**")
						 	.filters(f-> f.rewritePath(
						 			"/currency-conversion-new/(?<segment>.*)", 
						 			"/currency-conversion-feign/${segment}"))
						 	.uri("lb://currency-conversion")
						)
				 .build();
	}
}