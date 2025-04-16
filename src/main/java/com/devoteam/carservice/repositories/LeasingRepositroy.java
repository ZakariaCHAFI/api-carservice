package com.devoteam.carservice.repositories;

import com.devoteam.carservice.models.CustomerModel;
import com.devoteam.carservice.models.LeasingModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LeasingRepositroy extends JpaRepository<LeasingModel, UUID> {
    LeasingModel findByMatricule(String matricule);

}
