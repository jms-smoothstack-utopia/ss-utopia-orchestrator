package com.ss.utopia.orchestrator.dto.flights.airplane;


import com.ss.utopia.orchestrator.models.flights.airplane.SeatConfiguration;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirplaneDto {

  @NotBlank
  private String name;

  @NotNull
  private List<SeatConfiguration> seatConfigurations;


}
