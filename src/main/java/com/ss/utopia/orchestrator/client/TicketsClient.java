package com.ss.utopia.orchestrator.client;

import com.ss.utopia.orchestrator.dto.tickets.PurchaseTicketDto;
import com.ss.utopia.orchestrator.models.tickets.Ticket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("utopia-tickets-service")
public interface TicketsClient {

  @GetMapping(EndpointConstants.API_V_0_1_TICKETS)
  ResponseEntity<Ticket[]> getAllTickets();

  @GetMapping(EndpointConstants.API_V_0_1_TICKETS + "/{id}")
  ResponseEntity<Ticket> getTicketById(@PathVariable Long id);

  @PostMapping(EndpointConstants.API_V_0_1_TICKETS)
  ResponseEntity<Ticket> createTicket(@RequestBody PurchaseTicketDto ticketsDto);

  @PutMapping(EndpointConstants.API_V_0_1_TICKETS + "/{id}")
  void checkIn(@PathVariable Long id);
}
