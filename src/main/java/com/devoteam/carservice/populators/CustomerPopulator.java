package com.devoteam.carservice.populators;

import com.devoteam.carservice.dto.CustomerDto;
import com.devoteam.carservice.models.CustomerModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerPopulator {

    public CustomerModel toModel(CustomerDto customer) {
        if (customer == null) {
            return null;
        }

        CustomerModel.CustomerModelBuilder customerModel = CustomerModel.builder();
        customerModel.drivingLicenseCode(customer.getDrivingLicenseCode());
        customerModel.email(customer.getEmail());
        customerModel.phone(customer.getPhone());
        customerModel.firstName(customer.getFirstName());
        customerModel.lastName(customer.getLastName());
        return customerModel.build();
    }

    public CustomerDto toDto(CustomerModel customerModel) {
        if (customerModel == null) {
            return null;
        }
        CustomerDto.CustomerDtoBuilder customerDto = CustomerDto.builder();
        customerDto.drivingLicenseCode(customerModel.getDrivingLicenseCode());
        customerDto.email(customerModel.getEmail());
        customerDto.phone(customerModel.getPhone());
        customerDto.firstName(customerModel.getFirstName());
        customerDto.lastName(customerModel.getLastName());
        return customerDto.build();
    }
}
