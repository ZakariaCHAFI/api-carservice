package com.devoteam.carservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "leasing")
public class LeasingModel extends ItemModel {

    private String matricule;
    private int quantity; // nbr jours
    private double price;
    private double total;
    private Date startDate;
    private Date endDate;
    private int initialKM;
    private int lastKM;
    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinColumn(name = "drivingLicenseCode")
    private CustomerModel customer;
}
