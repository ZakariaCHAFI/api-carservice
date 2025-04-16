package com.devoteam.carservice.dto;

import com.devoteam.carservice.enums.CarTypeEnum;
import com.devoteam.carservice.enums.EngineEnum;
import com.devoteam.carservice.enums.GearBoxEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarResponseDto {

    private UUID id;
    private String matricule;
    private String shortDescription;
    private String description;
    private int place;
    private int bagage;
    private double price;
    private boolean available;
    private EngineEnum energy;
    private GearBoxEnum gear;
    private CarTypeEnum type;
    private int currentKM;

}
