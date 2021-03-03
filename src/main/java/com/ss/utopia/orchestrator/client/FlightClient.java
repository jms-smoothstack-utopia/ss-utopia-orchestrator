package com.ss.utopia.orchestrator.client;

import com.ss.utopia.orchestrator.dto.flights.airplane.AirplaneDto;
import com.ss.utopia.orchestrator.dto.flights.airport.CreateAirportDto;
import com.ss.utopia.orchestrator.dto.flights.airport.UpdateAirportDto;
import com.ss.utopia.orchestrator.dto.flights.flight.CreateFlightDto;
import com.ss.utopia.orchestrator.dto.flights.flight.UpdateFlightDto;
import com.ss.utopia.orchestrator.models.flights.airplane.Airplane;
import com.ss.utopia.orchestrator.models.flights.airport.Airport;
import com.ss.utopia.orchestrator.models.flights.flight.Flight;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("utopia-flights-service")
public interface FlightClient {

  @GetMapping(EndpointConstants.API_V_0_1_FLIGHTS)
  ResponseEntity<Flight[]> getAllFlights();

  @GetMapping(EndpointConstants.API_V_0_1_FLIGHTS + "/{id}")
  ResponseEntity<Flight> getFlightById(@PathVariable Long id);

  @PostMapping(EndpointConstants.API_V_0_1_FLIGHTS)
  ResponseEntity<Long> createFlight(@RequestBody CreateFlightDto flightDto);

  @PutMapping(EndpointConstants.API_V_0_1_FLIGHTS + "/{id}")
  void updateFlight(@PathVariable Long id, @RequestBody UpdateFlightDto flightDto);

  @DeleteMapping(EndpointConstants.API_V_0_1_FLIGHTS + "/{id}")
  void deleteFlight(@PathVariable Long id);

  @GetMapping(EndpointConstants.API_V_0_1_AIRPORTS)
  ResponseEntity<Airport[]> getAllAirports();

  @GetMapping(EndpointConstants.API_V_0_1_AIRPORTS + "/{id}")
  ResponseEntity<Airport> getAirportById(@PathVariable Long id);

  @PostMapping(EndpointConstants.API_V_0_1_AIRPORTS)
  ResponseEntity<Long> createAirport(@RequestBody CreateAirportDto airportDto);

  @PutMapping(EndpointConstants.API_V_0_1_AIRPORTS + "/{id}")
  void updateAirport(@PathVariable Long id, @RequestBody UpdateAirportDto airportDto);

  @DeleteMapping(EndpointConstants.API_V_0_1_AIRPORTS + "/{id}")
  void deleteAirport(@PathVariable Long id);

  @GetMapping(EndpointConstants.API_V_0_1_AIRPLANES)
  ResponseEntity<Airplane[]> getAllAirplanes();

  @GetMapping(EndpointConstants.API_V_0_1_AIRPLANES + "/{id}")
  ResponseEntity<Airplane> getAirplaneById(@PathVariable Long id);

  @PostMapping(EndpointConstants.API_V_0_1_AIRPLANES)
  ResponseEntity<Long> createAirplane(@RequestBody AirplaneDto airplaneDto);

  @PutMapping(EndpointConstants.API_V_0_1_AIRPLANES + "/{id}")
  void updateAirplane(@PathVariable Long id, @RequestBody AirplaneDto airplaneDto);

  @DeleteMapping(EndpointConstants.API_V_0_1_AIRPLANES + "/{id}")
  void deleteAirplane(@PathVariable Long id);
}
