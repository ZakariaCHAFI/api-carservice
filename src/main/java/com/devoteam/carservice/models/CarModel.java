package com.devoteam.carservice.models;


import com.devoteam.carservice.enums.CarTypeEnum;
import com.devoteam.carservice.enums.EngineEnum;
import com.devoteam.carservice.enums.GearBoxEnum;
import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "car")
public class CarModel extends ItemModel {

    @Column(unique=true)
    private String matricule;
    private String shortDescription;
    private String description;
    private int place;
    private int bagage;
    private double price;
    private boolean available;
    @Enumerated(EnumType.STRING)
    private EngineEnum energy;
    @Enumerated(EnumType.STRING)
    private GearBoxEnum gear;
    @Enumerated(EnumType.STRING)
    private CarTypeEnum type;
    private int currentKM;

}
