package com.ss.utopia.orchestrator.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancedRoutesConfig {

  public static final String API_LATEST = "/api/v0.1";

  public static final String AUTH_SERVICE = "lb://utopia-auth-service";
  public static final String FLIGHTS_SERVICE = "lb://utopia-flights-service";
  public static final String CUSTOMERS_SERVICE = "lb://utopia-customers-service";
  public static final String TICKETS_SERVICE = "lb://utopia-tickets-service";

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route(r -> r.path(GatewayConstants.AUTHENTICATE)
            .uri(AUTH_SERVICE))

        .route(r -> r.path(GatewayConstants.ACCOUNTS + "/**")
            .filters(f -> f.prefixPath(API_LATEST))
            .uri(AUTH_SERVICE))

        .route(r -> r.path(GatewayConstants.AIRPLANES + "/**")
            .filters(f -> f.prefixPath(API_LATEST))
            .uri(FLIGHTS_SERVICE))

        .route(r -> r.path(GatewayConstants.AIRPORTS + "/**")
            .filters(f -> f.prefixPath(API_LATEST))
            .uri(FLIGHTS_SERVICE))

        .route(r -> r.path(GatewayConstants.CUSTOMERS, GatewayConstants.CUSTOMERS + "/**")
            .filters(f -> f.prefixPath(API_LATEST))
            .uri(CUSTOMERS_SERVICE))

        .route(r -> r.path(GatewayConstants.FLIGHTS + "/**")
            .filters(f -> f.prefixPath(API_LATEST))
            .uri(FLIGHTS_SERVICE))

        .route(r -> r.path(GatewayConstants.TICKETS + "/**")
            .filters(f -> f.prefixPath(API_LATEST))
            .uri(TICKETS_SERVICE))

        .route(r -> r.path(GatewayConstants.SERVICING_AREA + "/**")
            .filters(f -> f.prefixPath(API_LATEST))
            .uri(FLIGHTS_SERVICE))

        .build();
  }
}
