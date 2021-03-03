package com.ss.utopia.orchestrator.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ss.utopia.orchestrator.dto.customers.CreateCustomerRecordDto;
import com.ss.utopia.orchestrator.models.customers.Address;
import com.ss.utopia.orchestrator.models.customers.Customer;
import java.net.URI;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class CustomerClientTest {

  RestTemplateBuilder mockBuilder = Mockito.mock(RestTemplateBuilder.class);
  RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);
  CustomerClient clientToTest;
  String mockApiHost = "/api/testing";

  @BeforeEach
  void beforeEach() {
    when(mockBuilder.build())
        .thenReturn(mockRestTemplate);
    clientToTest = new CustomerClient();
    clientToTest.setBuilder(mockBuilder);
    clientToTest.init();
    verify(mockBuilder).build();

    clientToTest.setApiHost(mockApiHost);
  }

  /**
   * Very simple happy path test.
   * <p>
   * Validates the URL used for the rest template is composed of the injected apiHost and endpoint
   * and that we receive the Customer returned by the rest template as the return result from the
   * method.
   */
  @Test
  void test_createNewCustomer_ReturnsCreatedCustomer() {
    var uuid = UUID.randomUUID();
    var createdUri = URI.create("/customers/" + uuid);

    var dto = CreateCustomerRecordDto.builder()
        .id(uuid)
        .firstName("John")
        .lastName("Smith")
        .email("test@test.com")
        .phoneNumber("999-999-9999")
        .addrLine1("123 Main Street")
        .addrLine2("Apt #5")
        .city("Las Vegas")
        .state("NV")
        .zipcode("12345-6789")
        .build();

    var customerResponse = Customer.builder()
        .id(uuid)
        .firstName(dto.getFirstName())
        .lastName(dto.getLastName())
        .email(dto.getEmail())
        .phoneNumber(dto.getPhoneNumber())
        .loyaltyPoints(0)						//zero by default
        .addresses(Set.of(Address.builder()
                              .id(1L)
                              .cardinality(1)
                              .line1(dto.getAddrLine1())
                              .line2(dto.getAddrLine2())
                              .city(dto.getCity())
                              .state(dto.getState())
                              .zipcode(dto.getZipcode())
                              .build()))
        .paymentMethods(Collections.emptySet())
        .ticketEmails(dto.getTicketEmails())	//true by default
        .flightEmails(dto.getFlightEmails())	//true by default
        .build();

    var mockResponse = ResponseEntity
        .created(createdUri)
        .body(customerResponse);

    when(mockRestTemplate.postForEntity(mockApiHost + clientToTest.getEndpoint(),
                                        dto,
                                        Customer.class))
        .thenReturn(mockResponse);

    var result = clientToTest.createNewCustomer(dto);

    assertEquals(result.getBody(), customerResponse);
    assertTrue(result.getHeaders().containsKey("Location"));
    assertEquals(result.getHeaders().getLocation(), createdUri);
  }
}