package com.ss.utopia.orchestrator.client;

import com.ss.utopia.orchestrator.controller.EndpointConstants;
import com.ss.utopia.orchestrator.dto.tickets.PurchaseTicketDto;
import com.ss.utopia.orchestrator.models.tickets.Ticket;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@ConfigurationProperties(prefix = "ss.utopia.ticket", ignoreUnknownFields = false)
public class TicketsClient {

  @Getter
  private final String endpoint = EndpointConstants.TICKETS_ENDPOINT;
  @Setter
  private String apiHost;
  private RestTemplateBuilder builder;
  private RestTemplate restTemplate;

  @Autowired
  public void setBuilder(RestTemplateBuilder builder) {
    this.builder = builder;
  }

  @PostConstruct
  public void init() {
    restTemplate = builder.build();
  }

  public ResponseEntity<Ticket[]> getAllTickets() {
    var url = apiHost + endpoint;
    log.info("GET " + url);
    return restTemplate.getForEntity(url, Ticket[].class);
  }

  public ResponseEntity<Ticket> getTicketById(Long id) {
    var url = apiHost + endpoint + id;
    log.info("GET " + url);
    return restTemplate.getForEntity(url, Ticket.class);
  }

  public ResponseEntity<Ticket> createTicket(PurchaseTicketDto ticketsDto) {
    var url = apiHost + endpoint;
    log.info("POST " + url);
    return restTemplate.postForEntity(url, ticketsDto, Ticket.class);
  }

  public void checkIn(Long id) {
    var url = apiHost + endpoint + id;
    log.info("PUT " + url);
    restTemplate.put(url, null);
  }
}
