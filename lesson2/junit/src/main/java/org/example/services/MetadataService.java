package org.example.services;

import org.example.model.Config;
import org.example.model.Metadata;

public interface MetadataService {

  Metadata receiveMetadata(Config config);

}
