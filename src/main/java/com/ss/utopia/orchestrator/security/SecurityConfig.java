package com.ss.utopia.orchestrator.security;

import com.ss.utopia.orchestrator.controller.GatewayConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsUtils;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final SecurityConstants securityConstants;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    var authEndpoint = securityConstants.getEndpoint();
    if (authEndpoint == null || authEndpoint.isEmpty() || authEndpoint.isBlank()) {
      authEndpoint = "/authenticate";
      log.warn("Authentication endpoint is null. Setting default endpoint of '/authenticate'");
    }

    http
        .cors().and().csrf().disable()
        .authorizeRequests()
        .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
        .requestMatchers(CorsUtils::isCorsRequest).permitAll()
        .antMatchers(authEndpoint).permitAll()
        .antMatchers(HttpMethod.PUT, GatewayConstants.ACCOUNTS + "/confirm/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilter(new JwtAuthenticationVerificationFilter(authenticationManagerBean(),
                                                           securityConstants))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    ;
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}

