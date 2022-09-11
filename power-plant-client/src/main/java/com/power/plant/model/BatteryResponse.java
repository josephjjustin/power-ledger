package com.power.plant.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BatteryResponse {

  @JsonProperty(access = Access.READ_ONLY)
private List<String> names;

private Statistics statistics;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public static class Statistics {
  @JsonProperty(access = Access.READ_ONLY)
  private double averageWatts;
  @JsonProperty(access = Access.READ_ONLY)
  private long total;
}

}
