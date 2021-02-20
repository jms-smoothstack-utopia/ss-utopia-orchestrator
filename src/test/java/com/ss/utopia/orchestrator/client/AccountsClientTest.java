package com.ss.utopia.orchestrator.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ss.utopia.orchestrator.dto.accounts.CreateUserAccountDto;
import java.net.URI;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class AccountsClientTest {

  RestTemplateBuilder mockBuilder = Mockito.mock(RestTemplateBuilder.class);
  RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);
  AccountsClient clientToTest;
  String mockApiHost = "/api/testing";

  @BeforeEach
  void beforeEach() {
    when(mockBuilder.build())
        .thenReturn(mockRestTemplate);
    clientToTest = new AccountsClient();
    clientToTest.setBuilder(mockBuilder);
    clientToTest.init();
    verify(mockBuilder).build();

    clientToTest.setApiHost(mockApiHost);
  }

  /**
   * Very simple happy path test.
   * <p>
   * Validates the URL used for the rest template is composed of the injected apiHost and endpoint
   * and that we receive the UUID returned by the rest template as the return result from the
   * method.
   */
  @Test
  void test_createNewAccount_ReturnsUUIDOfCreatedAccount() {
    var uuid = UUID.randomUUID();
    var createdUri = URI.create("/accounts/" + uuid);

    var mockResponse = ResponseEntity
        .created(createdUri)
        .body(uuid);

    var dto = CreateUserAccountDto.builder()
        .email("test@test.com")
        .password("abCD1234!@")
        .build();

    when(mockRestTemplate.postForEntity(mockApiHost + clientToTest.getEndpoint(),
                                        dto,
                                        UUID.class))
        .thenReturn(mockResponse);

    var result = clientToTest.createNewAccount(dto);

    assertEquals(result.getBody(), uuid);
    assertTrue(result.getHeaders().containsKey("Location"));
    assertEquals(result.getHeaders().getLocation(), createdUri);
  }
}