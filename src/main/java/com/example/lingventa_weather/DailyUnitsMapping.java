package com.example.lingventa_weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class DailyUnitsMapping {
    private String time;
    private String sunrise;
    private String sunset;
    private String rain_sum;
}
