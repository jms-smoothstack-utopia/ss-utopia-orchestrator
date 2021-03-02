package com.ss.utopia.orchestrator.dto.customers;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomerRecordDto {

  private UUID id;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String addrLine1;
  private String addrLine2;
  private String city;
  private String state;
  private String zipcode;
  private Boolean ticketEmails;
  private Boolean flightEmails;
}

