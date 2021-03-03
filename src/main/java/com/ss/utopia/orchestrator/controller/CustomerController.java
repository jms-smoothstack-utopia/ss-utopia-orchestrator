package com.ss.utopia.orchestrator.controller;

import com.ss.utopia.orchestrator.client.AuthClient;
import com.ss.utopia.orchestrator.client.CustomerClient;
import com.ss.utopia.orchestrator.dto.customers.CreateCustomerAccountDto;
import com.ss.utopia.orchestrator.dto.customers.CreateCustomerRecordDto;
import com.ss.utopia.orchestrator.models.customers.Customer;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(GatewayConstants.CUSTOMERS)
public class CustomerController {

  private final AuthClient accountsClient;
  private final CustomerClient customerClient;

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Customer[]> getAll() {
    log.info("GET all");
    return customerClient.getAllCustomers();
  }

  @GetMapping(value = "/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Customer> getCustomerById(@PathVariable UUID id) {
    log.info("GET id=" + id);
    return customerClient.getCustomerById(id);
  }

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Customer> createNewCustomer(@Valid @RequestBody CreateCustomerAccountDto customerDto) {
    log.info("POST");
    var authResponse = accountsClient.createNewAccount(customerDto.getAccountDto());

    var id = authResponse.getBody();
    var createCustomerRecord = customerDto.getRecordDto();
    createCustomerRecord.setId(id);

    return customerClient.createNewCustomer(createCustomerRecord);
  }

  @PutMapping(value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> updateExisting(@PathVariable UUID id,
                                          @Valid @RequestBody CreateCustomerRecordDto createCustomerRecordDto) {
    log.info("PUT id=" + id);
    customerClient.updateExisting(id, createCustomerRecordDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable UUID id) {
    log.info("DELETE id=" + id);
    customerClient.deleteCustomer(id);
    return ResponseEntity.noContent().build();
  }
}
