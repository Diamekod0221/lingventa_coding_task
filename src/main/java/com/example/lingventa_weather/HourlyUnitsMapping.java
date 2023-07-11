package com.example.lingventa_weather;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class HourlyUnitsMapping {
    private String time;
    private String temperature_2m;
}
