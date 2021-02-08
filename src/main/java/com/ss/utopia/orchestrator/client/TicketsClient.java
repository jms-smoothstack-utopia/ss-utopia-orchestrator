package com.ss.utopia.orchestrator.client;

import com.ss.utopia.orchestrator.dto.TicketsDto;
import com.ss.utopia.orchestrator.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties(prefix = "ss.utopia.ticket", ignoreUnknownFields = false)
public class TicketsClient {

  private static final Logger LOGGER = LoggerFactory.getLogger( TicketsClient.class );
  private final RestTemplate restTemplate;
  private final String endpoint = "/tickets/";
  private String apiHost;

  public TicketsClient(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public void setApiHost(String apiHost) {
    this.apiHost = apiHost;
  }


  public ResponseEntity<Ticket[]> getAllTickets() {
    var url = apiHost + endpoint;
    LOGGER.info("GET " + url);
    return restTemplate.getForEntity(url, Ticket[].class);
  }

  public ResponseEntity<Ticket> getTicketById(Long id) {
    var url = apiHost + endpoint + id;
    LOGGER.info("GET " + url);
    return restTemplate.getForEntity(url, Ticket.class);
  }

  public ResponseEntity<Ticket> createTicket(TicketsDto ticketsDto) {
    var url = apiHost + endpoint;
    LOGGER.info("POST " + url);
    return restTemplate.postForEntity(url, ticketsDto, Ticket.class);
  }

  public void checkIn(Long id, TicketsDto ticketsDto) {
    var url = apiHost + endpoint + id;
    LOGGER.info("PUT " + url);
    restTemplate.put(url, ticketsDto);
  }
}
