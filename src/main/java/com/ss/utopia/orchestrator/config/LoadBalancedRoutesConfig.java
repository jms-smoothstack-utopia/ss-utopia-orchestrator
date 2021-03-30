package com.ss.utopia.orchestrator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "com.ss.utopia.orchestrator.service-url", ignoreUnknownFields = false)
public class LoadBalancedRoutesConfig {

  private String version;
  private String auth;
  private String customers;
  private String flights;
  private String tickets;

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route(r -> r.path(GatewayConstants.AUTHENTICATE)
            .uri(auth))

        .route(r -> r.path(GatewayConstants.ACCOUNTS + "/**")
            .filters(f -> f.prefixPath(version))
            .uri(auth))

        .route(r -> r.path(GatewayConstants.AIRPLANES + "/**")
            .filters(f -> f.prefixPath(version))
            .uri(flights))

        .route(r -> r.path(GatewayConstants.AIRPORTS + "/**")
            .filters(f -> f.prefixPath(version))
            .uri(flights))

        .route(r -> r.path(GatewayConstants.CUSTOMERS, GatewayConstants.CUSTOMERS + "/**")
            .filters(f -> f.prefixPath(version))
            .uri(customers))

        .route(r -> r.path(GatewayConstants.FLIGHTS + "/**")
            .filters(f -> f.prefixPath(version))
            .uri(flights))

        .route(r -> r.path(GatewayConstants.TICKETS + "/**")
            .filters(f -> f.prefixPath(version))
            .uri(tickets))

        .route(r -> r.path(GatewayConstants.SERVICING_AREA + "/**")
            .filters(f -> f.prefixPath(version))
            .uri(flights))

        .build();
  }
}
