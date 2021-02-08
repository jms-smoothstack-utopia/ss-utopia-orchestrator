package com.ss.utopia.orchestrator.controller;

import com.ss.utopia.orchestrator.client.AirplaneClient;
import com.ss.utopia.orchestrator.dto.AirplaneDto;
import com.ss.utopia.orchestrator.model.Airplane;
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
@RequestMapping("/airplane")
public class AirplaneController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AirplaneController.class);

  private final AirplaneClient client;

  public AirplaneController(AirplaneClient client) {
    this.client = client;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Airplane> getAirplaneById(@PathVariable Long id) {
    LOGGER.info("GET id=" + id);
    return client.getAirplaneById(id);
  }

  @GetMapping
  public ResponseEntity<Airplane[]> getAllAirplanes() {
    LOGGER.info("GET all");
    return client.getAllAirplanes();
  }

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Long> createAirplane(@Valid @RequestBody AirplaneDto airplaneDto) {
    LOGGER.info("POST");
    return client.createAirplane(airplaneDto);
  }

  @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> updateAirplane(@PathVariable Long id,
                                          @Valid @RequestBody AirplaneDto airplaneDto) {
    LOGGER.info("PUT id=" + id);
    client.updateAirplane(id, airplaneDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteAirplane(@PathVariable Long id) {
    LOGGER.info("DELETE id=" + id);
    client.deleteAirplane(id);
    return ResponseEntity.noContent().build();
  }
}
