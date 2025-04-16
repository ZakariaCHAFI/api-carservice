package com.devoteam.carservice.validators;

import com.devoteam.carservice.dto.ReturnLeasedDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ReturnLeasedValidator {


    public static List<String> validate(ReturnLeasedDto returnLeasedDto) {

        List<String> errors = new ArrayList<>();
        if (returnLeasedDto == null) {
            errors.add("request body cannot be null");
            return errors;
        }

        if (StringUtils.isBlank(returnLeasedDto.getMatricule())) {
            errors.add("matricule param cannot be null");
        }
        if (StringUtils.isBlank(returnLeasedDto.getDrivingLicenseCode())) {
            errors.add("DrivingLicenseCode param cannot be null");
        }

        if (returnLeasedDto.getEndDate() == null) {
            errors.add("endDate param cannot be null");
        }

        if (returnLeasedDto.getStartDate() == null) {
            errors.add("startDate param cannot be null");
        }

        return errors;
    }
}
