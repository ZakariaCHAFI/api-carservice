package com.devoteam.carservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "customer")
public class CustomerModel extends ItemModel {


    @Column(name = "drivingLicenseCode")
    private String drivingLicenseCode;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<LeasingModel> leasing;
}
