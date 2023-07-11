package com.example.lingventa_weather;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiCallEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Double longitude;
    private Double latitude;
    private Timestamp requestTimestamp;


}
