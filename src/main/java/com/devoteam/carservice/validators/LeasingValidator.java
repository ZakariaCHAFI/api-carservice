package com.devoteam.carservice.validators;

import com.devoteam.carservice.dto.CustomerDto;
import com.devoteam.carservice.dto.LeasingDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LeasingValidator {

    public static final String PATTERB_REGEX_MAIL = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

    public static List<String> validate(LeasingDto leasingDto) {

        // TODO Impl  : internalization message after..

        List<String> errors = new ArrayList<>();
        if (leasingDto == null) {
            errors.add("request body cannot be null");
            return errors;
        }
        
        CustomerDto customerDto = leasingDto.getCustomerDto();
        if (customerDto == null) {
            errors.add("customer data info cannot be null");
            return errors;
        }
        if (StringUtils.isBlank(leasingDto.getMatricule())) {
            errors.add("matricule param cannot be null");
        }
        if (StringUtils.isBlank(customerDto.getDrivingLicenseCode())) {
            errors.add("Permis param cannot be null");
        }
        if (StringUtils.isBlank(customerDto.getPhone())) {
            errors.add("phone param cannot be null");
        }
        if (StringUtils.isBlank(customerDto.getEmail())) {
            errors.add("mail param cannot be null");
        } else if (!Pattern.compile(PATTERB_REGEX_MAIL).matcher(customerDto.getEmail()).matches()) {
            errors.add("mail param  not valid, exemple username@domaine.com");
        }
        if (StringUtils.isBlank(customerDto.getFirstName())) {
            errors.add("firstName param cannot be null");
        }
        if (StringUtils.isBlank(customerDto.getLastName())) {
            errors.add("lastName param cannot be null");
        }
        
        
        if (leasingDto.getQuantity() <= 0) {
            errors.add("quantity param cannot be null or less than a day");
        }
        if (leasingDto.getEndDate() == null) {
            errors.add("endDate param cannot be null");
        }

        if (leasingDto.getStartDate() == null) {
            errors.add("startDate param cannot be null");
        }

        return errors;
    }
}
