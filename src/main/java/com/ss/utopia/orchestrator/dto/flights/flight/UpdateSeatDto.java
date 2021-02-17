package com.ss.utopia.orchestrator.dto.flights.flight;

import com.ss.utopia.orchestrator.models.flights.flight.SeatStatus;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateSeatDto {

  @NotNull
  private SeatStatus seatStatus;

  @NotNull
  @Min(value = 0)
  private BigDecimal price;
}
