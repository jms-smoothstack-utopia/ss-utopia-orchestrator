package com.ss.utopia.orchestrator.client;

import com.ss.utopia.lib.dto.CustomerDto;
import com.ss.utopia.lib.model.customers.Customer;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties(prefix = "ss.utopia.customer", ignoreUnknownFields = false)
public class CustomerClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerClient.class);
  private final RestTemplate restTemplate;
  private final String endpoint = "/customers/";
  private String apiHost;

  public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public void setApiHost(String apiHost) {
    this.apiHost = apiHost;
  }

  public ResponseEntity<Customer[]> getAllCustomers() {
    var url = apiHost + endpoint;
    LOGGER.info("GET " + url);
    return restTemplate.getForEntity(url, Customer[].class);
  }

  public ResponseEntity<Customer> getCustomerById(Long id) {
    var url = apiHost + endpoint + id;
    LOGGER.info("GET " + url);
    return restTemplate.getForEntity(url, Customer.class);
  }

  public ResponseEntity<URI> createNewCustomer(CustomerDto customerDto) {
    var url = apiHost + endpoint;
    LOGGER.info("POST " + url);
    return restTemplate.postForEntity(url, customerDto, URI.class);
  }

  public void updateExisting(Long id, CustomerDto customerDto) {
    var url = apiHost + endpoint + id;
    LOGGER.info("PUT " + url);
    restTemplate.put(url, customerDto);
  }

  public void deleteCustomer(Long id) {
    var url = apiHost + endpoint + id;
    LOGGER.info("DELETE " + url);
    restTemplate.delete(url);
  }
}
