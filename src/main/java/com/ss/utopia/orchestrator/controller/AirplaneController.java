package com.ss.utopia.orchestrator.controller;

import com.ss.utopia.orchestrator.client.AirplaneClient;
import com.ss.utopia.orchestrator.dto.flights.airplane.AirplaneDto;
import com.ss.utopia.orchestrator.models.flights.airplane.Airplane;
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
@RequestMapping(EndpointConstants.AIRPLANES_ENDPOINT)
public class AirplaneController {

  private final AirplaneClient client;

  @GetMapping(value = "/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Airplane> getAirplaneById(@PathVariable Long id) {
    log.info("GET id=" + id);
    return client.getAirplaneById(id);
  }

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Airplane[]> getAllAirplanes() {
    log.info("GET all");
    return client.getAllAirplanes();
  }

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Long> createAirplane(@Valid @RequestBody AirplaneDto airplaneDto) {
    log.info("POST");
    return client.createAirplane(airplaneDto);
  }

  @PutMapping(value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> updateAirplane(@PathVariable Long id,
                                          @Valid @RequestBody AirplaneDto airplaneDto) {
    log.info("PUT id=" + id);
    client.updateAirplane(id, airplaneDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteAirplane(@PathVariable Long id) {
    log.info("DELETE id=" + id);
    client.deleteAirplane(id);
    return ResponseEntity.noContent().build();
  }
}
