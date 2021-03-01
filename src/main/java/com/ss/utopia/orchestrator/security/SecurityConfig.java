package com.ss.utopia.orchestrator.security;

import com.ss.utopia.orchestrator.controller.EndpointConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
        .requestMatchers(CorsUtils::isCorsRequest).permitAll()
        .antMatchers(HttpMethod.POST, EndpointConstants.AUTHENTICATE_ENDPOINT).permitAll()
        .antMatchers(HttpMethod.POST, EndpointConstants.ACCOUNTS_ENDPOINT).permitAll()
        .antMatchers(HttpMethod.PUT, EndpointConstants.ACCOUNTS_ENDPOINT + "/confirm/**").permitAll()
        .antMatchers("/api-docs/**").permitAll()
        //todo these need role lockdowns, but for now permit all
        .antMatchers(
            EndpointConstants.AIRPLANES_ENDPOINT,
            EndpointConstants.AIRPORTS_ENDPOINT,
            EndpointConstants.CUSTOMERS_ENDPOINT,
            EndpointConstants.FLIGHTS_ENDPOINT,
            EndpointConstants.TICKETS_ENDPOINT
        ).permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilter(new JwtAuthenticationVerificationFilter(authenticationManager()))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    ;

  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
