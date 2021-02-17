package com.ss.utopia.orchestrator.models.customers;

import java.util.Set;
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

  private Long id;

  private String firstName;

  private String lastName;

  private String email;

  private Set<Address> addresses;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<PaymentMethod> paymentMethods;
}
