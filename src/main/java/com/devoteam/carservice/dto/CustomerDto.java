package com.devoteam.carservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private String drivingLicenseCode;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    //TODO add another attribute...
}
