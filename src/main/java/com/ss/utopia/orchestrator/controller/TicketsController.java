package com.ss.utopia.orchestrator.controller;

import com.ss.utopia.orchestrator.client.TicketsClient;
import com.ss.utopia.orchestrator.dto.tickets.PurchaseTicketDto;
import com.ss.utopia.orchestrator.models.tickets.Ticket;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(EndpointConstants.TICKETS_ENDPOINT)
public class TicketsController {

  private final TicketsClient client;

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Ticket[]> getAllTickets() {
    log.info("GET all");
    return client.getAllTickets();
  }

  @GetMapping(value = "/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
    log.info("GET id=" + id);
    return client.getTicketById(id);
  }

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Ticket> createTicket(@Valid @RequestBody PurchaseTicketDto ticketsDto) {
    log.info("POST");
    return client.createTicket(ticketsDto);
  }

  @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> checkIn(@PathVariable Long id) {
    log.info("PUT id=" + id);
    client.checkIn(id);
    return ResponseEntity.ok().build();
  }
}
