package com.example.lingventa_weather;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiCallRepository extends CrudRepository<ApiCallEntity, Long> {

    @Override
    Optional<ApiCallEntity> findById(Long id);
}
