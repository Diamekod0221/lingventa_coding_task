package com.example.lingventa_weather;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LingventaWeatherApplicationTests {



    @Autowired
    private ApiCallController controller;

    @Test
    public void simpleInputReturnCheck() throws Exception {
        assert(controller.processApiCall("50", "50").getBody().getLongitude() == 50.0);
    }

    @Test
    public void contextLoads(){
        assertThat(controller).isNotNull();
    }
    @Test
    public void processApiCallValidRequestTest() {
        get("http://localhost:8080/weather-endpoint-rest/get-weather/50/50").then().assertThat().statusCode(200);
    }


    @Test
    public void processApiCallInvalidRequestTest(){
        get("http://localhost:8080/weather-endpoint-rest/get-weather/-1000/1000")
                    .then()
                    .assertThat()
                    .statusCode(400);
        get("http://localhost:8080/weather-endpoint-rest/get-weather/lalal/567")
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    public void checkResponseBodyTest(){
        System.out.println(get("http://localhost:8080/weather-endpoint-rest/get-weather/50/50").getBody().toString());
    }

}
