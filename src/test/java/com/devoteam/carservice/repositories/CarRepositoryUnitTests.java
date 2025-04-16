package com.devoteam.carservice.repositories;

import com.devoteam.carservice.enums.CarTypeEnum;
import com.devoteam.carservice.enums.EngineEnum;
import com.devoteam.carservice.enums.GearBoxEnum;
import com.devoteam.carservice.models.CarModel;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarRepositoryUnitTests {

    @Autowired
    private CarRepository carRepository;

    @Test
    @DisplayName("Test 1:Save Car Test")
    @Order(1)
    @Rollback(value = false)
    public void shouldSaveCarTest() {
        CarModel carModel = CarModel.builder()
                .matricule("PM-1214-C")
                .shortDescription("Volkswagen 1.0 TSI 95 LIFE PLUS")
                .description(StringUtils.EMPTY)
                .energy(EngineEnum.ESSENCE)
                .gear(GearBoxEnum.MANUAL)
                .place(5)
                .bagage(3)
                .type(CarTypeEnum.SEDAN)
                .price(42.00)
                .available(true)
                .currentKM(1214587)
                .build();
        carRepository.save(carModel);
        Assertions.assertThat(carModel).isNotNull();
    }

    @Test
    @DisplayName("Test 2 : find available car Test")
    @Order(2)
    public void shouldFindAvailibleCarest() {
        List<CarModel> carModel = carRepository.findByAvailableTrue();
        Assertions.assertThat(carModel.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test 2 : find car  by matricule Test")
    @Order(3)
    public void shouldFindCarByMatricule() {
        Optional<CarModel> carModel = carRepository.findByMatricule("PM-1214-C");
        Assertions.assertThat(carModel).isNotNull();
    }
}
