package com.devoteam.carservice;

import com.devoteam.carservice.enums.CarTypeEnum;
import com.devoteam.carservice.enums.EngineEnum;
import com.devoteam.carservice.enums.GearBoxEnum;
import com.devoteam.carservice.models.CarModel;
import com.devoteam.carservice.populators.CarPopulator;
import com.devoteam.carservice.repositories.CarRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.devoteam.carservice"})
public class ApiCarService {

    public static void main(String[] args) {
        SpringApplication.run(ApiCarService.class, args);
    }

    private static CarModel initSampleData(String matricule, String shortDescription, String description, EngineEnum energy, GearBoxEnum gear, int place, int bagage, CarTypeEnum type, double price, boolean isAvailable, int currentKm) {
        CarModel.CarModelBuilder carModel = CarModel.builder();
        carModel.matricule(matricule);
        carModel.shortDescription(shortDescription);
        carModel.description(description);
        carModel.energy(energy);
        carModel.gear(gear);
        carModel.place(place);
        carModel.bagage(bagage);
        carModel.type(type);
        carModel.price(price);
        carModel.available(isAvailable);
        carModel.currentKM(currentKm);
        return carModel.build();
    }

    @Bean
    CommandLineRunner start(CarRepository carRepository, CarPopulator carPopulator) {
        return args -> {
            carRepository.saveAll(List.of(
                    initSampleData("AB-1214-C", "Volkswagen 1.0 TSI 95 LIFE PLUS", StringUtils.EMPTY, EngineEnum.ESSENCE, GearBoxEnum.MANUAL, 5, 3, CarTypeEnum.SEDAN, 42.00, true, 1214587),
                    initSampleData("XC-7896-CV", "Nissan DIG-T 114 DCT Business Edition", StringUtils.EMPTY, EngineEnum.ESSENCE, GearBoxEnum.MANUAL, 5, 4, CarTypeEnum.SUV, 42.00, true, 147852),
                    initSampleData("Xl-0236-CF", "Fiat 500 édition Dolce Vita 70ch", StringUtils.EMPTY, EngineEnum.ESSENCE, GearBoxEnum.MANUAL, 4, 2, CarTypeEnum.COMPACT, 29.00, true, 47852),
                    initSampleData("ER-1458-AE", "Renault Clio 5 édition Evolution 65ch", StringUtils.EMPTY, EngineEnum.ESSENCE, GearBoxEnum.MANUAL, 5, 3, CarTypeEnum.COMPACT, 37.00, true, 12369),
                    initSampleData("PL-9569-RF", "Renault Zoé édition Business", StringUtils.EMPTY, EngineEnum.ELECTRIC, GearBoxEnum.AUTOMATIC, 5, 3, CarTypeEnum.COMPACT, 37.00, true, 12547),
                    initSampleData("MP-9875-RE", "Renault Arkana hybride 140ch", StringUtils.EMPTY, EngineEnum.HYBRID, GearBoxEnum.AUTOMATIC, 5, 4, CarTypeEnum.SUV, 58.00, true, 78952),
                    initSampleData("BV-7954-TR", "Tesla Model 3 Propulsion", StringUtils.EMPTY, EngineEnum.ELECTRIC, GearBoxEnum.AUTOMATIC, 5, 3, CarTypeEnum.COMPACT, 70.00, true, 123658),
                    initSampleData("DF-2584-EZ", "Lynk & Co 01 Hybride Rechargeable 261ch", StringUtils.EMPTY, EngineEnum.HYBRID, GearBoxEnum.AUTOMATIC, 5, 4, CarTypeEnum.COMPACT, 58.00, true, 7896542),
                    initSampleData("QS-47851-WX", "Skoda Karoq 110ch", StringUtils.EMPTY, EngineEnum.DIESEL, GearBoxEnum.MANUAL, 5, 4, CarTypeEnum.SUV, 52.00, true, 48569),
                    initSampleData("SD-7895-RE", "Citroën C3 édition Shine 83ch", StringUtils.EMPTY, EngineEnum.ESSENCE, GearBoxEnum.MANUAL, 5, 3, CarTypeEnum.COMPACT, 37.00, true, 25001),
                    initSampleData("KL-4587-XV", "Fiat 500 Cabriolet édition Dolce Vita 70ch", StringUtils.EMPTY, EngineEnum.HYBRID, GearBoxEnum.MANUAL, 5, 2, CarTypeEnum.COMPACT, 34.00, true, 96584)
            ));
        };
    }
}