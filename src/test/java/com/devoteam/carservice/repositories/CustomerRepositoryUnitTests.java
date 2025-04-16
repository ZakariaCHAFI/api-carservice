package com.devoteam.carservice.repositories;

import com.devoteam.carservice.models.CustomerModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerRepositoryUnitTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Test 1 : save Customer Test")
    @Order(1)
    public void shouldfsaveCustomer() {
        CustomerModel customerModel = CustomerModel.builder().drivingLicenseCode("AD2-4587").build();
        customerRepository.save(customerModel);
        System.out.print("customer id " + customerModel.getId());
        Assertions.assertThat(customerModel).isNotNull();
    }

    @Test
    @DisplayName("Test 2 : find Customer by driving license car Test")
    @Order(2)
    public void shouldFindByDrivingLicenseCode() {
        Optional<CustomerModel> customerModel = Optional.ofNullable(customerRepository.findByDrivingLicenseCode("AD2-4587"));
        Assertions.assertThat(customerModel).isNotNull();
    }
}
