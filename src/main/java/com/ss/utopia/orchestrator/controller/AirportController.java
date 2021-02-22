package com.ss.utopia.orchestrator.controller;


import com.ss.utopia.orchestrator.client.AirportClient;
import com.ss.utopia.orchestrator.dto.flights.airport.CreateAirportDto;
import com.ss.utopia.orchestrator.dto.flights.airport.UpdateAirportDto;
import com.ss.utopia.orchestrator.models.flights.airport.Airport;
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
@RequestMapping(EndpointConstants.AIRPORTS_ENDPOINT)
public class AirportController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AirportController.class);

  private final AirportClient client;

  public AirportController(AirportClient client) {
    this.client = client;
  }

  @GetMapping(value = "/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
    LOGGER.info("GET id=" + id);
    return client.getAirportById(id);
  }

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Airport[]> getAllAirports() {
    LOGGER.info("GET all");
    return client.getAllAirports();
  }

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Long> createAirport(@Valid @RequestBody CreateAirportDto airportDto) {
    LOGGER.info("POST");
    return client.createAirport(airportDto);
  }

  @PutMapping(value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> updateAirport(@PathVariable Long id,
                                         @Valid @RequestBody UpdateAirportDto airportDto) {
    LOGGER.info("PUT id=" + id);
    client.updateAirport(id, airportDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteAirport(@PathVariable Long id) {
    LOGGER.info("DELETE id=" + id);
    client.deleteAirport(id);
    return ResponseEntity.noContent().build();
  }
}
