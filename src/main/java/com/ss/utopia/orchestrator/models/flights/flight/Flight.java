package com.ss.utopia.orchestrator.models.flights.flight;


import com.ss.utopia.orchestrator.models.flights.airplane.Airplane;
import com.ss.utopia.orchestrator.models.flights.airport.Airport;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {

  private Long id;
  private Airport origin;
  private Airport destination;
  private Airplane airplane;
  private List<Seat> seats;
}
