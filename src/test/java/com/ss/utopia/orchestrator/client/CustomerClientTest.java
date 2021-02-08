package com.ss.utopia.orchestrator.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerClientTest {

  @Autowired
  CustomerClient client;

  @Test
  void test_getCustomerById_IsNotNull() {
    
  }
}