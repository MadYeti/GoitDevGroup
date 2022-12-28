package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Config {

  private String id;
  private Strategy strategy;
  private int threshold;
  private int dataPointsAmount;

}
