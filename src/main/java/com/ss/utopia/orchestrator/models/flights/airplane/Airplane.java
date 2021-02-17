package com.ss.utopia.orchestrator.models.flights.airplane;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airplane {

  private Long id;

  @NotBlank
  private String name;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private List<SeatConfiguration> seatConfigurations;

}
