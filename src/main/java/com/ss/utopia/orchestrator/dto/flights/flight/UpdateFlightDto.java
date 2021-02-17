package com.ss.utopia.orchestrator.dto.flights.flight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//todo design UpdateFlightDto
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateFlightDto {

  private String originId;
  private String destinationId;
  private Long airplaneId;
}
