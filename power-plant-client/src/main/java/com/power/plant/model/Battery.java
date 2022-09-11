package com.power.plant.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Battery {
  @NotBlank
String name;

  @NotBlank
Integer postalCode;
  @NotBlank
Integer wattCapacity;

}
