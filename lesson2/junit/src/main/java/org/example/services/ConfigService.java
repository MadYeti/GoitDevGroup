package org.example.services;

import org.example.model.Config;
import org.example.model.Query;

/**
 * Performs REST request to receive "config"
 */
public interface ConfigService {

  Config receiveConfig(Query query);

}
