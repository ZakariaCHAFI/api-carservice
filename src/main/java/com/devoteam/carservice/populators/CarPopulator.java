package com.devoteam.carservice.populators;

import com.devoteam.carservice.dto.CarResponseDto;
import com.devoteam.carservice.models.CarModel;
import org.springframework.stereotype.Component;

@Component
public class CarPopulator {

    public CarModel toModel(CarResponseDto car) {
        if (car == null) {
            return null;
        }

        CarModel.CarModelBuilder carModel = CarModel.builder();
        carModel.matricule(car.getMatricule());
        carModel.shortDescription(car.getShortDescription());
        carModel.description(car.getShortDescription());
        carModel.shortDescription(car.getShortDescription());
        carModel.energy(car.getEnergy());
        carModel.gear(car.getGear());
        carModel.place(car.getPlace());
        carModel.bagage(car.getBagage());
        carModel.type(car.getType());
        carModel.price(car.getPrice());
        carModel.available(car.isAvailable());
        carModel.currentKM(car.getCurrentKM());
        return carModel.build();
    }

    public CarResponseDto toDto(CarModel carModel) {
        if (carModel == null) {
            return null;
        }

        CarResponseDto.CarResponseDtoBuilder car = CarResponseDto.builder();
        car.id(carModel.getId());
        car.matricule(carModel.getMatricule());
        car.shortDescription(carModel.getShortDescription());
        car.description(carModel.getShortDescription());
        car.shortDescription(carModel.getShortDescription());
        car.energy(carModel.getEnergy());
        car.gear(carModel.getGear());
        car.place(carModel.getPlace());
        car.bagage(carModel.getBagage());
        car.type(carModel.getType());
        car.price(carModel.getPrice());
        car.available(carModel.isAvailable());
        car.currentKM(carModel.getCurrentKM());
        return car.build();
    }

    public void copy(CarResponseDto car, CarModel carModel) {
        // TODO impl
    }
}
