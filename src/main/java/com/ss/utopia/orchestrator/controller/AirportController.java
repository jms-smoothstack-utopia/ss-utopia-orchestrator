package com.ss.utopia.orchestrator.controller;

import com.ss.utopia.orchestrator.client.FlightClient;
import com.ss.utopia.orchestrator.dto.flights.airport.CreateAirportDto;
import com.ss.utopia.orchestrator.dto.flights.airport.UpdateAirportDto;
import com.ss.utopia.orchestrator.models.flights.airport.Airport;
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
@RequestMapping(GatewayConstants.AIRPORTS)
public class AirportController {

  private final FlightClient client;

  @GetMapping(value = "/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
    log.info("GET id=" + id);
    return client.getAirportById(id);
  }

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Airport[]> getAllAirports() {
    log.info("GET all");
    return client.getAllAirports();
  }

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Long> createAirport(@Valid @RequestBody CreateAirportDto airportDto) {
    log.info("POST");
    return client.createAirport(airportDto);
  }

  @PutMapping(value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> updateAirport(@PathVariable Long id,
                                         @Valid @RequestBody UpdateAirportDto airportDto) {
    log.info("PUT id=" + id);
    client.updateAirport(id, airportDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteAirport(@PathVariable Long id) {
    log.info("DELETE id=" + id);
    client.deleteAirport(id);
    return ResponseEntity.noContent().build();
  }
}
