package org.example.services;

import org.example.model.DataPoint;
import org.example.model.Query;

import java.util.List;

public interface PredictionService {

  List<DataPoint> receivePrediction(Query query);

}
