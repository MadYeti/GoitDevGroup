package org.example.services.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Config;
import org.example.model.DataPoint;
import org.example.model.Metadata;
import org.example.model.Query;
import org.example.services.CacheService;
import org.example.services.ConfigService;
import org.example.services.MetadataService;
import org.example.services.PredictionService;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredictionServiceImpl implements PredictionService {

  private ConfigService configService;
  private CacheService cacheService;
  private MetadataService metadataService;

  @Override
  public List<DataPoint> receivePrediction(Query query) {
    Config config;
    try {
      config = configService.receiveConfig(query);
    }catch (Exception exception){
      System.out.println("Error occurred during received config for id: " + query.getId());
      return Collections.emptyList();
    }
    return processConfig(config);
  }

  private List<DataPoint> processConfig(Config config){
    Optional<List<DataPoint>> freshDataPoints = cacheService.receiveFreshData(config);
    if(freshDataPoints.isEmpty()){
      return Collections.emptyList();
    }
    Metadata metadata = metadataService.receiveMetadata(config);
    return makePredictions(metadata, freshDataPoints.get(), config);
  }

  private List<DataPoint> makePredictions(Metadata metadata, List<DataPoint> dataPoints, Config config){
    if(metadata.getLastUpdateTime().isAfter(OffsetDateTime.now()) || metadata.getWorkingDaysAmount() <= 250){
      return dataPoints;
    }
    return switch (config.getStrategy()) {
      case ARITHMETICAL -> processArithmeticalStrategyPrediction(dataPoints, config);
      case GEOMETRICAL -> processGeometricalStrategyPrediction(dataPoints, config);
    };
  }

  private List<DataPoint> processArithmeticalStrategyPrediction(List<DataPoint> dataPoints, Config config){
    List<DataPoint> predictionDataPoints = new ArrayList<>();
    for (int i = 0; i < config.getDataPointsAmount(); i++){
      DataPoint oldDataPoint = dataPoints.get(i);
      DataPoint dataPoint = new DataPoint(oldDataPoint.getDate().plusDays(i), oldDataPoint.getValue() + config.getThreshold());
      predictionDataPoints.add(dataPoint);
    }
    return predictionDataPoints;
  }

  private List<DataPoint> processGeometricalStrategyPrediction(List<DataPoint> dataPoints, Config config){
    List<DataPoint> predictionDataPoints = new ArrayList<>();
    for (int i = 0; i < config.getDataPointsAmount(); i++){
      DataPoint oldDataPoint = dataPoints.get(i);
      DataPoint dataPoint = new DataPoint(oldDataPoint.getDate().plusDays(i), oldDataPoint.getValue() / config.getThreshold());
      predictionDataPoints.add(dataPoint);
    }
    return predictionDataPoints;
  }

}
