package com.devoteam.carservice.services;

import com.devoteam.carservice.dto.CarResponseDto;
import com.devoteam.carservice.models.CarModel;
import com.devoteam.carservice.populators.CarPopulator;
import com.devoteam.carservice.repositories.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarPopulator carPopulator;

    /**
     * @param carRepository
     * @param carPopulator
     */
    public CarService(CarRepository carRepository, CarPopulator carPopulator) {
        this.carRepository = carRepository;
        this.carPopulator = carPopulator;
    }

    /**
     * @param pageable
     * @return Page<CarResponseDto>
     */
    public Page<CarResponseDto> findAllCars(Pageable pageable) {
        return carRepository.findAll(pageable).map(carPopulator::toDto);
    }

    /**
     * @return List<CarResponseDto>
     */
    public List<CarResponseDto> findAvailableCars() {
        List<CarModel> carModels = carRepository.findByAvailableTrue();
        return carModels.stream().map(carPopulator::toDto).collect(Collectors.toList());
    }

    /**
     * @return List<CarResponseDto>
     */
    public List<CarResponseDto> findNotAvailableCars() {
        List<CarModel> carModels = carRepository.findByAvailableFalse();
        return carModels.stream().map(carPopulator::toDto).collect(Collectors.toList());
    }
}