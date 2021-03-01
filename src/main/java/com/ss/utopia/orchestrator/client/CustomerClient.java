package com.ss.utopia.orchestrator.client;

import com.ss.utopia.orchestrator.controller.EndpointConstants;
import com.ss.utopia.orchestrator.dto.customers.CreateCustomerRecordDto;
import com.ss.utopia.orchestrator.models.customers.Customer;
import java.util.UUID;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@ConfigurationProperties(prefix = "ss.utopia.customer", ignoreUnknownFields = false)
public class CustomerClient {

  @Getter
  private final String endpoint = EndpointConstants.CUSTOMERS_ENDPOINT;
  @Setter
  private String apiHost;
  private RestTemplateBuilder builder;
  private RestTemplate restTemplate;

  @Autowired
  public void setBuilder(RestTemplateBuilder builder) {
    this.builder = builder;
  }

  @PostConstruct
  public void init() {
    restTemplate = builder.build();
  }

  public ResponseEntity<Customer[]> getAllCustomers() {
    var url = apiHost + endpoint;
    log.info("GET " + url);
    return restTemplate.getForEntity(url, Customer[].class);
  }

  public ResponseEntity<Customer> getCustomerById(UUID id) {
    var url = apiHost + endpoint + "/" + id;
    log.info("GET " + url);
    return restTemplate.getForEntity(url, Customer.class);
  }

  public ResponseEntity<Customer> createNewCustomer(CreateCustomerRecordDto createCustomerRecordDto) {
    var url = apiHost + endpoint;
    log.info("POST " + url);
    return restTemplate.postForEntity(url, createCustomerRecordDto, Customer.class);
  }

  public void updateExisting(UUID id, CreateCustomerRecordDto createCustomerRecordDto) {
    var url = apiHost + endpoint + "/" + id;
    log.info("PUT " + url);
    restTemplate.put(url, createCustomerRecordDto);
  }

  public void deleteCustomer(UUID id) {
    var url = apiHost + endpoint + "/" + id;
    log.info("DELETE " + url);
    restTemplate.delete(url);
  }
}
