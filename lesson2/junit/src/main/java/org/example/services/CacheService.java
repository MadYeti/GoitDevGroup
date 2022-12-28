package org.example.services;

import org.example.model.Config;
import org.example.model.DataPoint;

import java.util.List;
import java.util.Optional;

/**
 * Performs request to cache, to find out is data fresh or not
 */
public interface CacheService {

  Optional<List<DataPoint>> receiveFreshData(Config config);

}
