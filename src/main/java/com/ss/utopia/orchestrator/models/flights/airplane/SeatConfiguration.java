package com.ss.utopia.orchestrator.models.flights.airplane;


import com.ss.utopia.orchestrator.models.flights.shared.SeatClass;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatConfiguration {

  private Long id;
  private Airplane airplane;
  private SeatClass seatClass;

  @NotNull
  private Integer numRows;

  @NotNull
  private Integer numSeatsPerRow;
}
