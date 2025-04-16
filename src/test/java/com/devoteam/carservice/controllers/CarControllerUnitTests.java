package com.devoteam.carservice.controllers;

import com.devoteam.carservice.models.CustomerModel;
import com.devoteam.carservice.services.CarService;
import com.devoteam.carservice.services.LeasingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarControllerUnitTests {

    CustomerModel customerModel;


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LeasingService leasingService;
    @MockitoBean
    private CarService carService;


    @BeforeEach
    public void setup() {
        customerModel = CustomerModel.builder().drivingLicenseCode("25-7896").firstName("Zakaria").lastName("CHAFI").email("zchafi@gmail.com").build();
    }

    /* TODO FINISH IMPL
    @Test
    @DisplayName("Test 1: show available Car Test")
    @Order(1)
    public void shouldGetCarsTest() {
        // TODO precondition

        // TODO action

        // TODO verify

    }*/
}
