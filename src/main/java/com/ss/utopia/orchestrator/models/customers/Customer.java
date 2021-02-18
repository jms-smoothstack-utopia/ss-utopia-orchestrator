package com.ss.utopia.orchestrator.models.customers;

import java.util.Set;
import java.util.UUID;
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
public class Customer {

  private UUID id;

  private String firstName;

  private String lastName;

  private String email;

  private Set<Address> addresses;

  private String phoneNumber;

  private Integer loyaltyPoints;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<PaymentMethod> paymentMethods;
}
