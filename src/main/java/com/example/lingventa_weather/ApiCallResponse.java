package com.example.lingventa_weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Jacksonized
@Builder
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiCallResponse {
    private double latitude;
    private double longitude;
    @JsonProperty("generationtime_ms")
    private double generationtimeMs;

    @JsonProperty("utc_offset_seconds")
    private int utcOffsetSeconds;

    private String timezone;

    @JsonProperty("timezone_abbreviation")
    private String timezoneAbbreviation;

    private double elevation;

    @JsonProperty("hourly_units")
    private HourlyUnitsMapping hourlyUnits;

    @JsonProperty("hourly")
    private HourlyBody hourlyBody;

    @JsonProperty("daily_units")
    private DailyUnitsMapping dailyUnitsMapping;

    @JsonProperty("daily")
    private DailyBody dailyBody;

    //forTesting
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
