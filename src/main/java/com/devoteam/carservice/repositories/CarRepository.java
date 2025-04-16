package com.devoteam.carservice.repositories;

import com.devoteam.carservice.models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<CarModel, UUID> {

    List<CarModel> findByAvailableTrue();
    List<CarModel> findByAvailableFalse();
    Optional<CarModel> findByMatricule(String matricule);

}
