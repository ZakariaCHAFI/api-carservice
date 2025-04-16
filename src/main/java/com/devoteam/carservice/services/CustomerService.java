package com.devoteam.carservice.services;

import com.devoteam.carservice.dto.LeasingDto;
import com.devoteam.carservice.models.CustomerModel;
import com.devoteam.carservice.populators.CustomerPopulator;
import com.devoteam.carservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerPopulator customerPopulator;

    public CustomerService(CustomerRepository customerRepository, CustomerPopulator customerPopulator) {
        this.customerRepository = customerRepository;
        this.customerPopulator = customerPopulator;
    }

    public CustomerModel findCustomerIfExiste(String code) {
        Assert.notNull(code, "driver licence code, mustn't be null");
        return customerRepository.findByDrivingLicenseCode(code);
    }

    public CustomerModel createNewCustomer(LeasingDto leasingDto) {
        return customerRepository.save(customerPopulator.toModel(leasingDto.getCustomerDto()));
    }
}
