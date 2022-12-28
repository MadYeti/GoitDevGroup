package org.example.services.impl;

import org.example.model.Config;
import org.example.model.DataPoint;
import org.example.model.ProcessETA;
import org.example.model.Query;
import org.example.model.Strategy;
import org.example.services.CacheService;
import org.example.services.ConfigService;
import org.example.services.MetadataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PredictionServiceImplTest {

  @Mock
  ConfigService configService;
  @Mock
  CacheService cacheService;
  @Mock
  MetadataService metadataService;
  @InjectMocks
  PredictionServiceImpl sut;


  @Test
  void shouldReturnEmptyListWhenReceivingConfigThrowsException(){
    //given
    Query query = query();

    //when
    when(configService.receiveConfig(query)).thenThrow(new RuntimeException());

    //then
    List<DataPoint> resultDataPoints = sut.receivePrediction(query);

    assertThat(resultDataPoints)
        .isNotNull()
        .isEmpty();
  }

  @Test
  void shouldReturnEmptyListWhenFreshnessDataIsAbsent(){
    //given
    Query query = query();
    Config config = config();

    //when
    when(configService.receiveConfig(query)).thenReturn(config);
    when(cacheService.receiveFreshData(config)).thenReturn(Optional.empty());

    //then
    List<DataPoint> resultDataPoints = sut.receivePrediction(query);

    assertThat(resultDataPoints)
        .isNotNull()
        .isEmpty();
  }

  private Query query(){
    return Query.builder()
        .id("id")
        .historyPointsAmount(20)
        .processETA(ProcessETA.LOW)
        .strategy(Strategy.ARITHMETICAL)
        .build();
  }

  private Config config(){
    return Config.builder()
        .strategy(Strategy.ARITHMETICAL)
        .threshold(5)
        .dataPointsAmount(10)
        .id("id")
        .build();
  }

}
