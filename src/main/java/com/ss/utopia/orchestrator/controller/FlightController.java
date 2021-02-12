package com.ss.utopia.orchestrator.controller;

import com.ss.utopia.lib.dto.FlightDto;
import com.ss.utopia.lib.model.flights.Flight;
import com.ss.utopia.orchestrator.client.FlightClient;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight")
public class FlightController {

  private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

  private final FlightClient client;

  public FlightController(FlightClient client) {
    this.client = client;
  }

  @GetMapping(value = "/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
    LOGGER.info("GET id=" + id);
    return client.getFlightById(id);
  }

  @GetMapping
  public ResponseEntity<Flight[]> getAllFlights() {
    LOGGER.info("GET all");
    return client.getAllFlights();
  }

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Long> createFlight(@Valid @RequestBody FlightDto flightDto) {
    LOGGER.info("POST");
    return client.createFlight(flightDto);
  }

  @PutMapping(value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> updateFlight(@PathVariable Long id,
                                        @Valid @RequestBody FlightDto flightDto) {
    LOGGER.info("PUT id=" + id);
    client.updateFlight(id, flightDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
    LOGGER.info("DELETE id=" + id);
    client.deleteFlight(id);
    return ResponseEntity.noContent().build();
  }
}
