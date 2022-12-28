package org.example.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Metadata {

  private String country;
  private int workingDaysAmount;
  private String index;
  private OffsetDateTime lastUpdateTime;
  private int holidaysAmount;

}
