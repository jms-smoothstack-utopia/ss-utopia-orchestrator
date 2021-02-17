package com.ss.utopia.orchestrator.models.flights.flight;

import com.ss.utopia.orchestrator.models.flights.shared.SeatClass;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

  private String id;  // Flight ID and Seat Row/Col, ie "1234-2A"
  private Flight flight;
  @NotNull
  private Integer seatRow;
  @NotNull
  private Character seatColumn;
  private SeatClass seatClass;
  private SeatStatus seatStatus;
  @NotNull
  private BigDecimal price;
}
