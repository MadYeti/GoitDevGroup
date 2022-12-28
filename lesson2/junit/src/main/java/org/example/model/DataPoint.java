package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class DataPoint {

  private OffsetDateTime date;
  private int value;

}
