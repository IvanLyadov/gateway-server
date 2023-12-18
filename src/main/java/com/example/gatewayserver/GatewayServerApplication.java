package com.example.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServerApplication {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("service1", r -> r.path("/user/**")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8080"))
				.route("service2", r -> r.path("/post/**")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8081"))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

}
