package com.example.lingventa_weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class NewCallEntityTest {

    @Mock
    private ApiCallRepository apiCallRepository;
    @Mock
    private NewApiCall newApiCall;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveEntityTest(){
        ApiCallEntity entity = ApiCallController.createApiCallEntity(this.newApiCall);
        apiCallRepository.save(entity);
    }


}
