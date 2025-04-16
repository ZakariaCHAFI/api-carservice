package com.devoteam.carservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeasingDto {

    private UUID id;
    private String matricule;
    private CustomerDto customerDto;
    private int quantity; // nbr jours
    private double price;
    private double total;
    private int initialKM;
    private int lastKM;
    private String startDate;
    private String endDate;
}
