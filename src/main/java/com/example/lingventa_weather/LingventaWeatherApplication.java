package com.example.lingventa_weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.lingventa_weather")
public class LingventaWeatherApplication {

    public static void main(String[] args) {

        SpringApplication.run(LingventaWeatherApplication.class, args);
    }

}
