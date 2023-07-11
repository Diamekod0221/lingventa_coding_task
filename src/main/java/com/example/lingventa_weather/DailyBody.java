package com.example.lingventa_weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@AllArgsConstructor
@Getter
@Jacksonized@Builder
public class DailyBody {
    @JsonProperty("time")
    private List<String> time;

    @JsonProperty("sunrise")
    private List<String> sunrise;

    @JsonProperty("sunset")
    private List<String> sunset;

    @JsonProperty("rain_sum")
    private List<Double> rainSum;
}
