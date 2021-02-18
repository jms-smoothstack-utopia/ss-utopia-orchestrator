package com.ss.utopia.orchestrator.controller;

import com.ss.utopia.orchestrator.client.AuthClient;
import com.ss.utopia.orchestrator.client.CustomerClient;
import com.ss.utopia.orchestrator.dto.customers.CreateCustomerAccountDto;
import com.ss.utopia.orchestrator.dto.customers.CreateCustomerRecordDto;
import com.ss.utopia.orchestrator.models.customers.Customer;
import java.net.URI;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

  private final CustomerClient customerClient;
  private final AuthClient authClient;

  public CustomerController(CustomerClient customerClient,
                            AuthClient authClient) {
    this.customerClient = customerClient;
    this.authClient = authClient;
  }

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Customer[]> getAll() {
    LOGGER.info("GET all");
    return customerClient.getAllCustomers();
  }

  @GetMapping(value = "/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
    LOGGER.info("GET id=" + id);
    return customerClient.getCustomerById(id);
  }

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Customer> createNewCustomer(@Valid @RequestBody CreateCustomerAccountDto customerDto) {
    LOGGER.info("POST");
    var authResponse = authClient.createNewAccount(customerDto.getAccountDto());

    var id = authResponse.getBody();
    var createCustomerRecord = customerDto.getRecordDto();
    createCustomerRecord.setId(id);

    return customerClient.createNewCustomer(createCustomerRecord);
  }

  @PutMapping(value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> updateExisting(@PathVariable Long id,
                                          @Valid @RequestBody CreateCustomerRecordDto createCustomerRecordDto) {
    LOGGER.info("PUT id=" + id);
    customerClient.updateExisting(id, createCustomerRecordDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    LOGGER.info("DELETE id=" + id);
    customerClient.deleteCustomer(id);
    return ResponseEntity.noContent().build();
  }
}
