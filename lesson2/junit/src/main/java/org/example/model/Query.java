package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Query {

  private String id;
  private int historyPointsAmount;
  private ProcessETA processETA;
  private Strategy strategy;

}
