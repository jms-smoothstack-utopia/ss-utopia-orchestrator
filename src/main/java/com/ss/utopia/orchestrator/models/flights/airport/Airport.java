package com.ss.utopia.orchestrator.models.flights.airport;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Airport {

  @NotNull
  private String iataId;

  @NotBlank
  private String name;

  @NotBlank
  private String streetAddress;

  @NotBlank
  private String city;

  @NotBlank
  private String state;

  @NotBlank
  private String zipcode;

}
