package com.ss.utopia.orchestrator.controller;

import com.ss.utopia.orchestrator.client.TicketsClient;
import com.ss.utopia.orchestrator.dto.TicketsDto;
import com.ss.utopia.orchestrator.model.Ticket;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketsController {

  private static final Logger LOGGER = LoggerFactory.getLogger(TicketsController.class);

  private final TicketsClient client;

  public TicketsController(TicketsClient client) {
    this.client = client;
  }

  @GetMapping
  public ResponseEntity<Ticket[]> getAllTickets() {
    LOGGER.info("GET all");
    return client.getAllTickets();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
    LOGGER.info("GET id=" + id);
    return client.getTicketById(id);
  }

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Ticket> createTicket(@Valid @RequestBody TicketsDto ticketsDto) {
    LOGGER.info("POST");
    return client.createTicket(ticketsDto);
  }

  @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> checkIn(@PathVariable Long id,
                                   @Valid @RequestBody TicketsDto ticketsDto) {
    LOGGER.info("PUT id=" + id);
    client.checkIn(id, ticketsDto);
    return ResponseEntity.ok().build();
  }
}
