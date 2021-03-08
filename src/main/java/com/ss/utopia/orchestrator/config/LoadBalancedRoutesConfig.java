package com.ss.utopia.orchestrator.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancedRoutesConfig {

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route(r -> r.path(GatewayConstants.AUTHENTICATE)
            .uri("lb://utopia-auth-service/authenticate"))
        .route(r -> r.path(GatewayConstants.ACCOUNTS + "/**")
            .uri("lb://utopia-auth-service/" + EndpointConstants.API_V_0_1_ACCOUNTS))
        .route(r -> r.path(GatewayConstants.AIRPLANES + "/**")
            .uri("lb://utopia-flights-service/" + EndpointConstants.API_V_0_1_AIRPLANES))
        .route(r -> r.path(GatewayConstants.AIRPORTS + "/**")
            .uri("lb://utopia-flights-service/" + EndpointConstants.API_V_0_1_AIRPORTS))
        .route(r -> r.path(GatewayConstants.CUSTOMERS + "/**")
            .uri("lb://utopia-customers-service/" + EndpointConstants.API_V_0_1_CUSTOMERS))
        .route(r -> r.path(GatewayConstants.FLIGHTS + "/**")
            .uri("lb://utopia-flights-service" + EndpointConstants.API_V_0_1_FLIGHTS))
        .route(r -> r.path(GatewayConstants.TICKETS + "/**")
            .uri("lb://utopia-tickets-service/" + EndpointConstants.API_V_0_1_TICKETS))
        .build();
  }
}
