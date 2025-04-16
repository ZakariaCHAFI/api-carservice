package com.devoteam.carservice.services;

import com.devoteam.carservice.dto.LeasingDto;
import com.devoteam.carservice.dto.ReturnLeasedDto;
import com.devoteam.carservice.exceptions.EntityNotFoundException;
import com.devoteam.carservice.exceptions.ErrorCodes;
import com.devoteam.carservice.exceptions.InvalidRequestException;
import com.devoteam.carservice.models.CarModel;
import com.devoteam.carservice.models.CustomerModel;
import com.devoteam.carservice.models.LeasingModel;
import com.devoteam.carservice.populators.LeasingPopulator;
import com.devoteam.carservice.repositories.CarRepository;
import com.devoteam.carservice.repositories.LeasingRepositroy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeasingService {

    private final LeasingRepositroy leasingRepositroy;
    private final CarRepository carRepository;
    private final LeasingPopulator leasingPopulator;
    private final CustomerService customerService;

    public LeasingService(LeasingRepositroy leasingRepositroy, CarRepository carRepository, LeasingPopulator leasingPopulator, CustomerService customerService) {
        this.leasingRepositroy = leasingRepositroy;
        this.carRepository = carRepository;
        this.leasingPopulator = leasingPopulator;
        this.customerService = customerService;
    }

    public List<LeasingDto> findAllLeasingHisto() {
        return leasingRepositroy.findAll().stream().map(leasingPopulator::toDto).collect(Collectors.toList());
    }

    public LeasingDto saveLeasing(LeasingDto leasingDto) {
        Assert.notNull(leasingDto.getMatricule(), "Code matricule car, mustn't be null");

        // check if car is available
        CarModel carModel = carRepository.findByMatricule(leasingDto.getMatricule()).orElseThrow(() -> new EntityNotFoundException("Car with matricule code  = " + leasingDto.getMatricule() + " not found, ", ErrorCodes.CAR_NOT_FOUND));
        if (!carModel.isAvailable()) {
            throw new InvalidRequestException("Leasing is not valid, already in use by another customer ", ErrorCodes.LEASING_ALREADY_IN_USE, List.of(""));
        }
        carModel.setAvailable(false);
        carRepository.save(carModel);

        // check if customer existe by driveing licence, or new customer created
        CustomerModel customerModel = customerService.findCustomerIfExiste(leasingDto.getCustomerDto().getDrivingLicenseCode());
        if (customerModel == null) {
            customerModel = customerService.createNewCustomer(leasingDto);
        }
        LeasingModel leasingModel = leasingPopulator.toModel(leasingDto);
        leasingModel.setCustomer(customerModel);
        leasingModel.setPrice(carModel.getPrice());
        leasingModel.setTotal(carModel.getPrice() * leasingModel.getQuantity());
        leasingModel.setInitialKM(carModel.getCurrentKM());
        leasingModel = leasingRepositroy.save(leasingModel);

        return leasingPopulator.toDto(leasingModel);
    }

    public LeasingDto returnLeasedCar(ReturnLeasedDto returnLeasedDto) {
        CarModel carModel = carRepository.findByMatricule(returnLeasedDto.getMatricule()).orElseThrow(() -> new EntityNotFoundException("Car with matricule code  = " + returnLeasedDto.getMatricule() + " not found, ", ErrorCodes.CAR_NOT_FOUND));

        // TODO IMPL , recalcule price leasing car

        LeasingModel leasingModel = leasingRepositroy.findByMatricule(returnLeasedDto.getMatricule());
        leasingModel.setInitialKM(carModel.getCurrentKM());
        leasingModel.setLastKM(returnLeasedDto.getCurrentKM());

        carModel.setAvailable(true);
        carModel.setCurrentKM(returnLeasedDto.getCurrentKM());
        carRepository.save(carModel);
        return leasingPopulator.toDto(leasingRepositroy.save(leasingModel));
    }
}
