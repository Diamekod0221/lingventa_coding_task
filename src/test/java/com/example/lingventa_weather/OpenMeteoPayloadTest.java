package com.example.lingventa_weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenMeteoPayloadTest {

    @Mock
    private ApiCallRepository apiCallRepository;
    private ApiCallController controller;
    private ApiCallEntity testEntity;

    private String testPayload;
    private String testPayloadReduced;



    @BeforeEach
    public void setup() throws IOException {
        MockitoAnnotations.openMocks(this);
        NewApiCall testCall = new NewApiCall(51.1079, 17.0385);

        testEntity = ApiCallController.createApiCallEntity(testCall);
        controller = new ApiCallController(apiCallRepository);

        Path  testPayloadPath = Path.of("src/test/testResources/testPayload.txt");
        testPayload = Files.readString(testPayloadPath);

        Path testPayloadReducedPath = Path.of("src/test/testResources/testPayloadReduced.txt");
        testPayloadReduced = Files.readString(testPayloadReducedPath).replaceAll("\\r\\n", "");
    }


    //Will show difference for 11.07. For other days, get the payload from open meteo and paste into testPayload.txt
    /*TODO: omit time_generated in this test*/
    @Test
    public void getPayloadTest(){
        assertEquals(testPayload, controller.callOpenMeteoAPI(testEntity));
    }

    @Test
    public void processPayloadTest () throws IOException {
        String openMeteoPayload = testPayloadReduced;
        System.out.println(openMeteoPayload);
        ApiCallResponse apiCallResponse = controller.processApiResponse(openMeteoPayload);
        System.out.println(apiCallResponse.toString());
    }

}
