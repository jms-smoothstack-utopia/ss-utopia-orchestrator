package com.ss.utopia.orchestrator.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketsDto {

  @NotNull(message = "Flight Number is required")
  private Long flightId;

  @NotNull(message = "Customer ID is required")
  private Long customerId;

  @NotBlank(message = "Passenger name is required")
  private String passenger;

  @NotNull(message = "Is the passenger check-in True or False?")
  private boolean isCheckedIn;

}
