package com.example.lingventa_weather;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NewApiCallValidator {

    public static void validate(final NewApiCall newApiCall){
        validateCoordinates(newApiCall);
    }

    public static void validateCoordinates(NewApiCall newApiCall){
        validateLatitude(newApiCall.getLatitude());
        validateLongitude(newApiCall.getLongitude());
    }

    public static void validateLatitude(double latitude){
        if(Math.abs(latitude) > 90){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid latitude: " + latitude);
        }
    }

    public static void validateLongitude(double longitude){
        if(Math.abs(longitude) > 180){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid longitude: " + longitude);
        }
    }
}
