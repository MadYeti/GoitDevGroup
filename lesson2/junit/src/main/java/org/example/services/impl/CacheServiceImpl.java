package org.example.services.impl;

import org.example.model.Config;
import org.example.model.DataPoint;
import org.example.services.CacheService;

import java.util.List;
import java.util.Optional;

public class CacheServiceImpl implements CacheService {

  @Override
  public Optional<List<DataPoint>> receiveFreshData(Config config) {
    return Optional.empty();
  }

}
