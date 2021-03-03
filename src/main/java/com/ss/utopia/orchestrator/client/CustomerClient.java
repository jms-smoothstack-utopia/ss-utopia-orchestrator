package com.ss.utopia.orchestrator.client;

import com.ss.utopia.orchestrator.dto.customers.CreateCustomerRecordDto;
import com.ss.utopia.orchestrator.models.customers.Customer;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "utopia-customers-service")
public interface CustomerClient {

  @GetMapping(EndpointConstants.API_V_0_1_CUSTOMERS)
  ResponseEntity<Customer[]> getAllCustomers();

  @GetMapping(EndpointConstants.API_V_0_1_CUSTOMERS + "/{id}")
  ResponseEntity<Customer> getCustomerById(@PathVariable UUID id);

  @PostMapping(EndpointConstants.API_V_0_1_CUSTOMERS)
  ResponseEntity<Customer> createNewCustomer(@RequestBody CreateCustomerRecordDto createCustomerRecordDto);

  @PutMapping(EndpointConstants.API_V_0_1_CUSTOMERS + "/{id}")
  void updateExisting(@PathVariable UUID id,
                      @RequestBody CreateCustomerRecordDto createCustomerRecordDto);

  @DeleteMapping(EndpointConstants.API_V_0_1_CUSTOMERS + "/{id}")
  void deleteCustomer(@PathVariable UUID id);
}
