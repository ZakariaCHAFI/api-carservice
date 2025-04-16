package com.devoteam.carservice.repositories;

import com.devoteam.carservice.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerModel, UUID> {
    CustomerModel findByDrivingLicenseCode(String drivingLicenseCode);
}
