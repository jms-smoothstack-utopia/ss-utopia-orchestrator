package com.ss.utopia.orchestrator.controller;

import com.ss.utopia.orchestrator.client.FlightClient;
import com.ss.utopia.orchestrator.dto.flights.flight.CreateFlightDto;
import com.ss.utopia.orchestrator.dto.flights.flight.UpdateFlightDto;
import com.ss.utopia.orchestrator.models.flights.flight.Flight;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping(EndpointConstants.FLIGHTS_ENDPOINT)
public class FlightController {

  private final FlightClient client;

  @GetMapping(value = "/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
    log.info("GET id=" + id);
    return client.getFlightById(id);
  }

  @GetMapping
  public ResponseEntity<Flight[]> getAllFlights() {
    log.info("GET all");
    return client.getAllFlights();
  }

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Long> createFlight(@Valid @RequestBody CreateFlightDto flightDto) {
    log.info("POST");
    return client.createFlight(flightDto);
  }

  @PutMapping(value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> updateFlight(@PathVariable Long id,
                                        @Valid @RequestBody UpdateFlightDto flightDto) {
    log.info("PUT id=" + id);
    client.updateFlight(id, flightDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
    log.info("DELETE id=" + id);
    client.deleteFlight(id);
    return ResponseEntity.noContent().build();
  }
}
