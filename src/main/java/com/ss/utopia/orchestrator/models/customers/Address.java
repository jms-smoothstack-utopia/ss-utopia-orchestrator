package com.ss.utopia.orchestrator.models.customers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

  private Long id;
  private Integer cardinality;
  private String line1;
  private String line2;
  private String city;
  private String state;
  private String zipcode;
}
