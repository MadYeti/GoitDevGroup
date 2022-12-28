package org.example.services.impl;

import org.example.model.Config;
import org.example.model.Query;
import org.example.services.ConfigService;

public class ConfigServiceImpl implements ConfigService {

  @Override
  public Config receiveConfig(Query query) {
    //REST request
    return Config.builder()
        .id(query.getId())
        .dataPointsAmount(query.getHistoryPointsAmount())
        .threshold(10)
        .strategy(query.getStrategy())
        .build();
  }

}
