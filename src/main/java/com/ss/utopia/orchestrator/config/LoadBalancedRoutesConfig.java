package com.ss.utopia.orchestrator.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancedRoutesConfig {

  public static final String API_LATEST = "/api/v0.1";

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route(r -> r.path(GatewayConstants.AUTHENTICATE)
            .uri("lb://utopia-auth-service"))

        .route(r -> r.path(GatewayConstants.ACCOUNTS + "/**")
            .filters(f -> f.prefixPath(API_LATEST))
            .uri("lb://utopia-auth-service"))

        .route(r -> r.path(GatewayConstants.AIRPLANES + "/**")
            .filters(f -> f.prefixPath(API_LATEST))
            .uri("lb://utopia-flights-service"))

        .route(r -> r.path(GatewayConstants.AIRPORTS + "/**")
            .filters(f -> f.prefixPath(API_LATEST))
            .uri("lb://utopia-flights-service"))

        .route(r -> r.path(GatewayConstants.CUSTOMERS, GatewayConstants.CUSTOMERS + "/**")
            .filters(f -> f.prefixPath(API_LATEST))
            .uri("lb://utopia-customers-service"))

        .route(r -> r.path(GatewayConstants.FLIGHTS + "/**")
            .filters(f -> f.prefixPath(API_LATEST))
            .uri("lb://utopia-flights-service"))

        .route(r -> r.path(GatewayConstants.TICKETS + "/**")
            .filters(f -> f.prefixPath(API_LATEST))
            .uri("lb://utopia-tickets-service"))

        .build();
  }
}
