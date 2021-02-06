package com.ss.utopia.orchestrator.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AirplaneDto {

  @NotNull(message = "Max capacity required")
  private Integer maxCapacity;
}
