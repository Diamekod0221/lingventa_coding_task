package com.example.lingventa_weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDate;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/weather-endpoint-rest")
public class ApiCallController {

    @Autowired
    private final ApiCallRepository apiCallRepository;

    @GetMapping(path = "/get-weather/{longitude}/{latitude}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiCallResponse> processApiCall(@PathVariable String longitude, @PathVariable String latitude) throws IOException {
        NewApiCall configuredInput = configureInput(longitude, latitude);
        ApiCallEntity callEntity = createApiCallEntity(configuredInput);
        saveCall(callEntity);
        String openMeteoPayload = callOpenMeteoAPI(callEntity);
        ApiCallResponse processedApiResponse = processApiResponse(openMeteoPayload);

        return ResponseEntity.ok(processedApiResponse);
    }

    private NewApiCall configureInput(String latitude, String longitude){
        try {
            return processInput(latitude,longitude);
        }
            catch(Exception e) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Invalid input format, can't parse. Latitude: "
                        + latitude + " Longitude: " + longitude, e);
        }
    }

    private NewApiCall processInput(String latitude, String longitude){
        NewApiCall newApiCall = new NewApiCall(Double.parseDouble(latitude), Double.parseDouble(longitude));
        NewApiCallValidator.validate(newApiCall);
        return newApiCall;

    }

    static public ApiCallEntity createApiCallEntity(NewApiCall newApiCall){
        return ApiCallEntity
                .builder()
                .latitude(newApiCall.getLatitude())
                .longitude(newApiCall.getLongitude())
                .requestTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();

    }

    private void saveCall(ApiCallEntity callEntity){
        apiCallRepository.save(callEntity);
    }


    public String callOpenMeteoAPI(ApiCallEntity callEntity) {
        String apiUrl = generateOpenMeteoUrl(callEntity);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, String.class);
        return response.getBody();
    }

    private String generateOpenMeteoUrl(ApiCallEntity callEntity){
        String today = LocalDate.now(Clock.systemDefaultZone()).toString();
        String weekBefore = LocalDate.now(Clock.systemDefaultZone()).minusWeeks(1).toString();

        return "https://archive-api.open-meteo.com/v1/archive?latitude=" + callEntity.getLatitude()
                + "&longitude=" + callEntity.getLongitude()
                        +"&start_date=" + weekBefore + "&end_date=" + today
                //this last part to get sunrise, sunset, rain sums
                + "&hourly=temperature_2m&daily=sunrise,sunset,rain_sum&timezone=auto";
    }

    public ApiCallResponse processApiResponse(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonResponse, ApiCallResponse.class);
    }


}
