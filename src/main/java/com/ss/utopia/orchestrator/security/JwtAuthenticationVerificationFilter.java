package com.ss.utopia.orchestrator.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtAuthenticationVerificationFilter extends BasicAuthenticationFilter {

  private  final SecurityConstants securityConstants;

  public JwtAuthenticationVerificationFilter(AuthenticationManager authenticationManager,
                                             SecurityConstants securityConstants) {
    super(authenticationManager);
    this.securityConstants = securityConstants;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain chain) throws IOException, ServletException {
    try {
      var header = request.getHeader(securityConstants.getJwtHeaderName());

      if (header != null && header.startsWith(securityConstants.getJwtHeaderPrefix())) {
        var authToken = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(authToken);
        log.debug("Auth success.");
      }
      chain.doFilter(request, response);
    } catch (TokenExpiredException ex) {
      log.debug("Expired token");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("{\"error\":\"token expired\"}");
    }
  }

  private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
    var token = request.getHeader(securityConstants.getJwtHeaderName());
    if (token != null) {
      //todo get roles from jwt?
      var subject = JWT.require(Algorithm.HMAC512(securityConstants.getJwtSecret()))
          .build()
          .verify(token.replace(securityConstants.getJwtHeaderPrefix(), ""))
          .getSubject();
      if (subject != null) {
        return new UsernamePasswordAuthenticationToken(subject, null, Collections.emptySet());
      }
    }
    return null;
  }
}
