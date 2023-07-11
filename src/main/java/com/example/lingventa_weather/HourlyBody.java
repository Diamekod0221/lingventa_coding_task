package com.example.lingventa_weather;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;


@Getter
@Jacksonized
@Builder
public class HourlyBody {
    @JsonProperty("time")
    private List<String> time;

    @JsonProperty("temperature_2m")
    private List<Double> temperature2m;

    @JsonCreator
    public HourlyBody(@JsonProperty(value = "time") List<String> time,
                      @JsonProperty(value = "temperature_2m") List<Double> temperature2m) {
        this.time = time;
        this.temperature2m = temperature2m;
    }
}
