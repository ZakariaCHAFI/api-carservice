package com.devoteam.carservice.utils;

import com.devoteam.carservice.exceptions.ErrorCodes;
import com.devoteam.carservice.exceptions.InvalidRequestException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DateUtils {

    private static final String PATTERN_DATE = "dd-MM-yyyy HH:mm:ss";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATE);

    public static Date convertDate(String strDate) {
        try {
            return formatter.parse(strDate);
        } catch (ParseException e) {
            throw new InvalidRequestException("Leasing is not valid ", ErrorCodes.LEASING_NOT_VALID, List.of("Invalide date " + strDate + " with pattern : " + PATTERN_DATE));

        }
    }
}
