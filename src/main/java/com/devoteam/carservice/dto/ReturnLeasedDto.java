package com.devoteam.carservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnLeasedDto {

    private String drivingLicenseCode;
    private String matricule;
    private String startDate;
    private String endDate;
    private int currentKM;
}
